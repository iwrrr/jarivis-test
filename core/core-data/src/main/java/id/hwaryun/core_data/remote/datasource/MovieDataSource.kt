package id.hwaryun.core_data.remote.datasource

import id.hwaryun.core_data.model.response.BaseResponse
import id.hwaryun.core_data.model.response.BoxOfficeResponse
import id.hwaryun.core_data.model.response.MovieDetailResponse
import id.hwaryun.core_data.model.response.SearchResponse
import id.hwaryun.core_data.remote.service.ApiService
import javax.inject.Inject

interface MovieDataSource {
    suspend fun getBoxOfficeMovies(): BaseResponse<BoxOfficeResponse>
    suspend fun getMovieDetails(movieId: String): MovieDetailResponse
    suspend fun getSearchMovies(query: String): SearchResponse<BoxOfficeResponse>
}

class MovieDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    MovieDataSource {

    override suspend fun getBoxOfficeMovies(): BaseResponse<BoxOfficeResponse> {
        return apiService.getBoxOfficeMovies()
    }

    override suspend fun getMovieDetails(movieId: String): MovieDetailResponse {
        return apiService.getMovieDetails(movieId = movieId)
    }

    override suspend fun getSearchMovies(query: String): SearchResponse<BoxOfficeResponse> {
        return apiService.searchMovies(query = query)
    }
}