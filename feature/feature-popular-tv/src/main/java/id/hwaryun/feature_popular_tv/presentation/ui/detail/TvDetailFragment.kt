package id.hwaryun.feature_popular_tv.presentation.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import id.hwaryun.core_common.base.BaseFragment
import id.hwaryun.core_data.model.viewparam.MovieDetailViewParam
import id.hwaryun.core_data.utils.ext.subscribe
import id.hwaryun.feature_popular_tv.R
import id.hwaryun.feature_popular_tv.databinding.FragmentTvDetailBinding
import id.hwaryun.feature_popular_tv.presentation.ui.tv.PopularTvViewModel

@AndroidEntryPoint
class TvDetailFragment : BaseFragment<FragmentTvDetailBinding, PopularTvViewModel>(FragmentTvDetailBinding::inflate) {

    override val viewModel: PopularTvViewModel by viewModels()

    private val args: TvDetailFragmentArgs by navArgs()

    override fun initView() {
        args.tvId?.let { viewModel.getTvDetails(it) }
    }

    override fun observeData() {
        viewModel.tvDetails.observe(viewLifecycleOwner) {
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