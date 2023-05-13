package api

import retrofit2.http.GET

private const val API_KEY = "5650fcf86f9e0372e8e8d1426cf69aa7"

interface FlickrApi {
/*    @GET("/")
    suspend fun fetchContents(): String*/
    @GET(
        "services/rest/?method=flickr.interestingness.getList" +
                "&api_key=$API_KEY" +
                "&format=json" +
                "&nojsoncallback=1" +
                "&extras=url_s"
    )
    suspend fun fetchPhotos(): FlickrResponse
}