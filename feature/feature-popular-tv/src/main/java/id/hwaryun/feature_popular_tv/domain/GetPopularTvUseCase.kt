package id.hwaryun.feature_popular_tv.domain

import id.hwaryun.core_common.base.BaseUseCase
import id.hwaryun.core_common.wrapper.ViewResource
import id.hwaryun.core_data.di.IoDispatcher
import id.hwaryun.core_data.model.response.PopularTvResponse
import id.hwaryun.core_data.model.viewparam.BoxOfficeViewParam
import id.hwaryun.core_data.model.viewparam.PopularTvViewParam
import id.hwaryun.core_data.repository.MovieRepository
import id.hwaryun.core_data.repository.TvRepository
import id.hwaryun.core_data.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPopularTvUseCase @Inject constructor(
    private val repository: TvRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, List<PopularTvViewParam>>(dispatcher) {

    override suspend fun execute(param: Nothing?): Flow<ViewResource<List<PopularTvViewParam>>> =
        flow {
            repository.getMostPopularTvs().first().suspendSubscribe(
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