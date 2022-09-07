package id.hwaryun.feature_home.presentation.ui.movie

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.hwaryun.core_common.base.BaseFragment
import id.hwaryun.core_data.model.viewparam.BoxOfficeViewParam
import id.hwaryun.core_data.utils.ext.subscribe
import id.hwaryun.feature_home.databinding.FragmentBoxOfficeBinding
import id.hwaryun.feature_home.presentation.adapter.MovieAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class BoxOfficeFragment :
    BaseFragment<FragmentBoxOfficeBinding, BoxOfficeViewModel>(FragmentBoxOfficeBinding::inflate) {

    override val viewModel: BoxOfficeViewModel by viewModels()

    private val adapter: MovieAdapter by lazy { MovieAdapter(::onItemClick) }

    override fun initView() {
        viewModel.getBoxOfficeMovies()

        binding.etSearch.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrBlank()) {
                viewModel.getBoxOfficeMovies()
            } else {
                lifecycleScope.launch { viewModel.queryChannel.send(text.toString()) }
            }
        }
    }

    override fun observeData() {
        viewModel.boxOfficeMovies.observe(viewLifecycleOwner) {
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

        viewModel.searchResult.observe(viewLifecycleOwner) {
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

    private fun initData(movies: List<BoxOfficeViewParam>) {
        binding.rvMovies.adapter = adapter
        binding.rvMovies.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter.submitList(movies)
    }

    private fun onItemClick(movie: BoxOfficeViewParam) {
        val directions = BoxOfficeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movie.id)
        findNavController().navigate(directions)
    }
}