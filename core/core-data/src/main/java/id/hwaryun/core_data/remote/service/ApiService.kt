package id.hwaryun.core_data.remote.service

import id.hwaryun.core_data.model.response.*
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("en/API/MostPopularTVs/{apiKey}/")
    suspend fun getMostPopularTvs(
        @Path("apiKey") apiKey: String = "k_k3cp3z6o"
    ): BaseResponse<PopularTvResponse>

    @GET("en/API/BoxOffice/{apiKey}/")
    suspend fun getBoxOfficeMovies(
        @Path("apiKey") apiKey: String = "k_k3cp3z6o"
    ): BaseResponse<BoxOfficeResponse>

    @GET("en/API/Title/{apiKey}/{id}")
    suspend fun getMovieDetails(
        @Path("apiKey") apiKey: String = "k_k3cp3z6o",
        @Path("id") movieId: String
    ): MovieDetailResponse

    @GET("/en/API/SearchMovie/{apiKey}/{query}/")
    suspend fun searchMovies(
        @Path("apiKey") apiKey: String = "k_k3cp3z6o",
        @Path("query") query: String
    ): SearchResponse<BoxOfficeResponse>

    @GET("/en/API/SearchSeries/{apiKey}/{query}/")
    suspend fun searchTvs(
        @Path("apiKey") apiKey: String = "k_k3cp3z6o",
        @Path("query") query: String
    ): SearchResponse<PopularTvResponse>
}