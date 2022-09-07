package id.hwaryun.core_data.repository

import id.hwaryun.core_common.wrapper.DataResource
import id.hwaryun.core_data.model.response.BoxOfficeResponse
import id.hwaryun.core_data.model.response.MovieDetailResponse
import id.hwaryun.core_data.model.response.PopularTvResponse
import id.hwaryun.core_data.remote.datasource.MovieDataSource
import id.hwaryun.core_data.remote.datasource.TvDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface TvRepository {
    fun getMostPopularTvs(): Flow<DataResource<List<PopularTvResponse>?>>
    fun getTvDetails(movieId: String): Flow<DataResource<MovieDetailResponse>>
    fun getSearchTvs(query: String): Flow<DataResource<List<PopularTvResponse>?>>
}

class TvRepositoryImpl @Inject constructor(
    private val dataSource: TvDataSource
) : Repository(), TvRepository {

    override fun getMostPopularTvs(): Flow<DataResource<List<PopularTvResponse>?>> = flow {
        emit(safeNetworkCall { dataSource.getMostPopularTvs().items })
    }

    override fun getTvDetails(movieId: String): Flow<DataResource<MovieDetailResponse>> = flow {
        emit(safeNetworkCall { dataSource.getTvDetails(movieId) })
    }

    override fun getSearchTvs(query: String): Flow<DataResource<List<PopularTvResponse>?>> = flow {
        emit(safeNetworkCall { dataSource.getSearchTvs(query).results })
    }
}