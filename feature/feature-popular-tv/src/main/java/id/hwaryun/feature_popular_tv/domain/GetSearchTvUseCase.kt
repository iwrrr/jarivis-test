package id.hwaryun.feature_popular_tv.domain

import id.hwaryun.core_common.base.BaseUseCase
import id.hwaryun.core_common.wrapper.ViewResource
import id.hwaryun.core_data.di.IoDispatcher
import id.hwaryun.core_data.model.viewparam.BoxOfficeViewParam
import id.hwaryun.core_data.model.viewparam.PopularTvViewParam
import id.hwaryun.core_data.repository.TvRepository
import id.hwaryun.core_data.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSearchTvUseCase @Inject constructor(
    private val repository: TvRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<String, List<PopularTvViewParam>>(dispatcher) {

    override suspend fun execute(param: String?): Flow<ViewResource<List<PopularTvViewParam>>> =
        flow {
            param?.let { query ->
                repository.getSearchTvs(query).first().suspendSubscribe(
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