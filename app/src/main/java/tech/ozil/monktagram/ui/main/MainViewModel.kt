package tech.ozil.monktagram.ui.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import tech.ozil.monktagram.data.Repository
import tech.ozil.monktagram.model.Album
import tech.ozil.monktagram.utils.NetworkResult
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var albumsResponse: MutableLiveData<NetworkResult<Album>> = MutableLiveData()

    fun getAlbums(queries: Map<String, Int>) = viewModelScope.launch {
        getAlbumsSafeCall(queries)
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
}