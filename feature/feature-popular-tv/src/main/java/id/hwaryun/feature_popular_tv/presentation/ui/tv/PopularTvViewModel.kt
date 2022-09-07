package id.hwaryun.feature_popular_tv.presentation.ui.tv

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.hwaryun.core_common.wrapper.ViewResource
import id.hwaryun.core_data.model.viewparam.BoxOfficeViewParam
import id.hwaryun.core_data.model.viewparam.MovieDetailViewParam
import id.hwaryun.core_data.model.viewparam.PopularTvViewParam
import id.hwaryun.feature_popular_tv.domain.GetPopularTvUseCase
import id.hwaryun.feature_popular_tv.domain.GetSearchTvUseCase
import id.hwaryun.feature_popular_tv.domain.GetTvDetailUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@HiltViewModel
class PopularTvViewModel @Inject constructor(
    private val getPopularTvUseCase: GetPopularTvUseCase,
    private val getTvDetailUseCase: GetTvDetailUseCase,
    private val getSearchTvUseCase: GetSearchTvUseCase
) : ViewModel() {

    val popularTvs: MutableLiveData<ViewResource<List<PopularTvViewParam>>> = MutableLiveData()
    val tvDetails: MutableLiveData<ViewResource<MovieDetailViewParam>> = MutableLiveData()

    val queryChannel = Channel<String>()
    val searchResult = queryChannel.receiveAsFlow()
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest { getSearchTvUseCase(it) }
        .asLiveData()

    fun getMostPopularTvs() {
        viewModelScope.launch {
            getPopularTvUseCase().collect {
                popularTvs.value = it
            }
        }
    }

    fun getTvDetails(movieId: String) {
        viewModelScope.launch {
            getTvDetailUseCase(movieId).collect {
                tvDetails.value = it
            }
        }
    }
}