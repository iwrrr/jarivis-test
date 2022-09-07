package id.hwaryun.core_data.remote.datasource

import id.hwaryun.core_data.model.response.*
import id.hwaryun.core_data.remote.service.ApiService
import javax.inject.Inject

interface TvDataSource {
    suspend fun getMostPopularTvs(): BaseResponse<PopularTvResponse>
    suspend fun getTvDetails(movieId: String): MovieDetailResponse
    suspend fun getSearchTvs(query: String): SearchResponse<PopularTvResponse>
}

class TvDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    TvDataSource {

    override suspend fun getMostPopularTvs(): BaseResponse<PopularTvResponse> {
        return apiService.getMostPopularTvs()
    }

    override suspend fun getTvDetails(movieId: String): MovieDetailResponse {
        return apiService.getMovieDetails(movieId = movieId)
    }

    override suspend fun getSearchTvs(query: String): SearchResponse<PopularTvResponse> {
        return apiService.searchTvs(query = query)
    }
}