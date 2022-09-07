package id.hwaryun.feature_home.presentation.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.hwaryun.core_common.wrapper.ViewResource
import id.hwaryun.core_data.model.viewparam.BoxOfficeViewParam
import id.hwaryun.core_data.model.viewparam.MovieDetailViewParam
import id.hwaryun.feature_home.domain.GetBoxOfficeUseCase
import id.hwaryun.feature_home.domain.GetMovieDetailUseCase
import id.hwaryun.feature_home.domain.GetSearchMovieUseCase
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
class BoxOfficeViewModel @Inject constructor(
    private val getBoxOfficeUseCase: GetBoxOfficeUseCase,
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getSearchMovieUseCase: GetSearchMovieUseCase
) : ViewModel() {

    val boxOfficeMovies: MutableLiveData<ViewResource<List<BoxOfficeViewParam>>> = MutableLiveData()
    val movieDetails: MutableLiveData<ViewResource<MovieDetailViewParam>> = MutableLiveData()

    val queryChannel = Channel<String>()
    val searchResult = queryChannel.receiveAsFlow()
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest { getSearchMovieUseCase(it) }
        .asLiveData()

    fun getBoxOfficeMovies() {
        viewModelScope.launch {
            getBoxOfficeUseCase().collect {
                boxOfficeMovies.value = it
            }
        }
    }

    fun getMovieDetails(movieId: String) {
        viewModelScope.launch {
            getMovieDetailUseCase(movieId).collect {
                movieDetails.value = it
            }
        }
    }
}