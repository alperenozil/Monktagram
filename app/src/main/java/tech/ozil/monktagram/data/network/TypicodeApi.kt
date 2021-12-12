package tech.ozil.monktagram.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap
import tech.ozil.monktagram.model.Album
import tech.ozil.monktagram.model.Photo

interface TypicodeApi {

    @GET("/albums")
    suspend fun getAlbums(
        @QueryMap queries: Map<String, Int>
    ): Response<Album>

    @GET("/photos")
    suspend fun getPhotos(
        @QueryMap queries: Map<String, Int>
    ): Response<Photo>

}