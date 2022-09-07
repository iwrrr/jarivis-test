package id.hwaryun.feature_popular_tv.domain

import id.hwaryun.core_common.base.BaseUseCase
import id.hwaryun.core_common.wrapper.ViewResource
import id.hwaryun.core_data.di.IoDispatcher
import id.hwaryun.core_data.model.viewparam.BoxOfficeViewParam
import id.hwaryun.core_data.model.viewparam.MovieDetailViewParam
import id.hwaryun.core_data.repository.MovieRepository
import id.hwaryun.core_data.repository.TvRepository
import id.hwaryun.core_data.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTvDetailUseCase @Inject constructor(
    private val repository: TvRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<String, MovieDetailViewParam>(dispatcher) {

    override suspend fun execute(param: String?): Flow<ViewResource<MovieDetailViewParam>> =
        flow {
            param?.let {
                repository.getTvDetails(it).first().suspendSubscribe(
                    doOnSuccess = { result ->
                        emit(ViewResource.Loading())
                        if (result.payload != null) {
                            emit(ViewResource.Success(result.payload!!.toViewParam() ))
                        } else {
                            emit(ViewResource.Empty(null))
                        }
                    },
                    doOnError = { error ->
                        emit(ViewResource.Error(error.exception))
                    }
                )
            }
        }
}