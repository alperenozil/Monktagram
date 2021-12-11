package tech.ozil.monktagram.data

import retrofit2.Response
import tech.ozil.monktagram.data.network.TypicodeApi
import tech.ozil.monktagram.models.Album
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val typicodeApi: TypicodeApi) {
    suspend fun getAlbums(queries: Map<String, Int>): Response<Album> {
        return typicodeApi.getAlbums(queries)
    }
}