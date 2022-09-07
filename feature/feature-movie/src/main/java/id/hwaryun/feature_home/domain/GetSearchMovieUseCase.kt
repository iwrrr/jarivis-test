package id.hwaryun.feature_home.domain

import id.hwaryun.core_common.base.BaseUseCase
import id.hwaryun.core_common.wrapper.ViewResource
import id.hwaryun.core_data.di.IoDispatcher
import id.hwaryun.core_data.model.viewparam.BoxOfficeViewParam
import id.hwaryun.core_data.model.viewparam.MovieViewParam
import id.hwaryun.core_data.repository.MovieRepository
import id.hwaryun.core_data.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSearchMovieUseCase @Inject constructor(
    private val repository: MovieRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<String, List<BoxOfficeViewParam>>(dispatcher) {

    override suspend fun execute(param: String?): Flow<ViewResource<List<BoxOfficeViewParam>>> =
        flow {
            param?.let { query ->
                repository.getSearchMovies(query).first().suspendSubscribe(
                    doOnSuccess = { result ->
                        emit(ViewResource.Loading())
                        if (!result.payload.isNullOrEmpty()) {
                            emit(ViewResource.Success(result.payload!!.map { it.toViewParam() }))
                        } else {
                            emit(ViewResource.Empty(listOf()))
                        }
                    },
                    doOnError = { error ->
                        emit(ViewResource.Error(error.exception))
                    }
                )
            }
        }
}