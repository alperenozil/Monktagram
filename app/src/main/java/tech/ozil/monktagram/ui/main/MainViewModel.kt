package tech.ozil.monktagram.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import tech.ozil.monktagram.data.Repository
import tech.ozil.monktagram.model.Album
import tech.ozil.monktagram.model.Photo
import tech.ozil.monktagram.utils.NetworkResult
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var albumsResponse: MutableLiveData<NetworkResult<Album>> = MutableLiveData()
    var photosResponse: MutableLiveData<NetworkResult<Photo>> = MutableLiveData()

    fun getAlbums(queries: Map<String, Int>) = viewModelScope.launch {
        getAlbumsSafeCall(queries)
    }
    fun getPhotos(queries: Map<String, Int>) = viewModelScope.launch {
        getPhotosSafeCall(queries)
    }

    private suspend fun getAlbumsSafeCall(queries: Map<String, Int>) {
        albumsResponse.value = NetworkResult.Loading()
        try {
            val response = repository.remote.getAlbums(queries)
            albumsResponse.value = handleAlbumsResponse(response)
        } catch (e: Exception) {
            albumsResponse.value = NetworkResult.Error("Album not found.")
        }
    }
    private suspend fun getPhotosSafeCall(queries: Map<String, Int>) {
       photosResponse.value = NetworkResult.Loading()
        try {
            val response = repository.remote.getPhotos(queries)
            photosResponse.value = handlePhotosResponse(response)
        } catch (e: Exception) {
            photosResponse.value = NetworkResult.Error("Photo not found.")
        }
    }

    private fun handleAlbumsResponse(response: Response<Album>): NetworkResult<Album>? {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            response.isSuccessful -> {
                val albums = response.body()
                return NetworkResult.Success(albums!!)
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
    }
    private fun handlePhotosResponse(response: Response<Photo>): NetworkResult<Photo>? {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            response.isSuccessful -> {
                val photos = response.body()
                return NetworkResult.Success(photos!!)
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
    }
}