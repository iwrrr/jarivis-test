package id.hwaryun.core_data.repository

import id.hwaryun.core_common.wrapper.DataResource
import id.hwaryun.core_data.model.response.BoxOfficeResponse
import id.hwaryun.core_data.model.response.MovieDetailResponse
import id.hwaryun.core_data.model.response.MovieResponse
import id.hwaryun.core_data.model.viewparam.BoxOfficeViewParam
import id.hwaryun.core_data.remote.datasource.MovieDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface MovieRepository {
    fun getBoxOfficeMovies(): Flow<DataResource<List<BoxOfficeResponse>?>>
    fun getMovieDetails(movieId: String): Flow<DataResource<MovieDetailResponse>>
    fun getSearchMovies(query: String): Flow<DataResource<List<BoxOfficeResponse>?>>
}

class MovieRepositoryImpl @Inject constructor(
    private val dataSource: MovieDataSource
) : Repository(), MovieRepository {

    override fun getBoxOfficeMovies(): Flow<DataResource<List<BoxOfficeResponse>?>> = flow {
        emit(safeNetworkCall { dataSource.getBoxOfficeMovies().items })
    }

    override fun getMovieDetails(movieId: String): Flow<DataResource<MovieDetailResponse>> = flow {
        emit(safeNetworkCall { dataSource.getMovieDetails(movieId) })
    }

    override fun getSearchMovies(query: String): Flow<DataResource<List<BoxOfficeResponse>?>> = flow {
        emit(safeNetworkCall { dataSource.getSearchMovies(query).results })
    }
}