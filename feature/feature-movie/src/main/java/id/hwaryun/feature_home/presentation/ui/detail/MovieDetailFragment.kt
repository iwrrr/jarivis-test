package id.hwaryun.feature_home.presentation.ui.detail

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import id.hwaryun.core_common.base.BaseFragment
import id.hwaryun.core_data.model.viewparam.MovieDetailViewParam
import id.hwaryun.core_data.utils.ext.subscribe
import id.hwaryun.feature_home.databinding.FragmentMovieDetailBinding
import id.hwaryun.feature_home.presentation.ui.movie.BoxOfficeViewModel

@AndroidEntryPoint
class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailBinding, BoxOfficeViewModel>(FragmentMovieDetailBinding::inflate) {

    override val viewModel: BoxOfficeViewModel by viewModels()

    private val args: MovieDetailFragmentArgs by navArgs()

    override fun initView() {
        args.movieId?.let { viewModel.getMovieDetails(it) }
    }

    override fun observeData() {
        viewModel.movieDetails.observe(viewLifecycleOwner) {
            it.subscribe(
                doOnLoading = {
                    binding.progressBar.isVisible = true
                },
                doOnSuccess = { result ->
                    binding.progressBar.isVisible = false
                    initData(result.payload!!)
                },
                doOnEmpty = {
                    Toast.makeText(requireContext(), "Data kosong/Batas pemanggilan API mencapai batas", Toast.LENGTH_SHORT).show()
                },
                doOnError = { error ->
                    Toast.makeText(requireContext(), error.exception.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            )
        }
    }

    private fun initData(movie: MovieDetailViewParam) {
        binding.apply {
            tvTitle.text = movie.fullTitle
            tvGenres.text = movie.genres
            tvYear.text = movie.year
            tvRuntime.text = movie.runtimeStr
            tvVote.text = movie.imDbRatingVotes
            tvScore.text = movie.metacriticRating

            Glide.with(requireContext())
                .load(movie.image)
                .into(ivMovie)
        }
    }
}