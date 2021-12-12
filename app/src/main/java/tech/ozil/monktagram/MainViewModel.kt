package tech.ozil.monktagram

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import tech.ozil.monktagram.data.Repository
import tech.ozil.monktagram.models.Album
import tech.ozil.monktagram.utils.NetworkResult
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository, application: Application) : ViewModel() {

    var albumsResponse: MutableLiveData<NetworkResult<Album>> = MutableLiveData()

    fun getAlbums(queries: Map<String, Int>) = viewModelScope.launch {
        getAlbumsSafeCall(queries)
    }

    private suspend fun getAlbumsSafeCall(queries: Map<String, Int>) {
        albumsResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.getAlbums(queries)
                albumsResponse.value = handleAlbumsResponse(response)
            } catch (e: Exception) {
                albumsResponse.value = NetworkResult.Error("Album not found.")
            }
        } else {
            albumsResponse.value = NetworkResult.Error("No internet connection.")
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

    private fun hasInternetConnection(): Boolean {
        return true
    }
}