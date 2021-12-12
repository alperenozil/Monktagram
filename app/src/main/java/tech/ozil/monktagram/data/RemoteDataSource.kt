package tech.ozil.monktagram.data

import retrofit2.Response
import tech.ozil.monktagram.data.network.TypicodeApi
import tech.ozil.monktagram.model.Album
import tech.ozil.monktagram.model.Photo
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val typicodeApi: TypicodeApi) {
    suspend fun getAlbums(queries: Map<String, Int>): Response<Album> {
        return typicodeApi.getAlbums(queries)
    }
    suspend fun getPhotos(queries: Map<String, Int>): Response<Photo> {
        return typicodeApi.getPhotos(queries)
    }
}