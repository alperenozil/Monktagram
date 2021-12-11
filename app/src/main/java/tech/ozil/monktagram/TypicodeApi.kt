package tech.ozil.monktagram

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap
import tech.ozil.monktagram.models.Album

interface TypicodeApi {

    @GET("/albums")
    suspend fun getAlbums(
        @QueryMap queries: Map<String, Int>
    ): Response<Album>

}