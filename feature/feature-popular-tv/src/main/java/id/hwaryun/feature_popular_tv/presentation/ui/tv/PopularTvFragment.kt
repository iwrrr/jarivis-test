package id.hwaryun.feature_popular_tv.presentation.ui.tv

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
import id.hwaryun.core_data.model.viewparam.PopularTvViewParam
import id.hwaryun.core_data.utils.ext.subscribe
import id.hwaryun.feature_popular_tv.databinding.FragmentPopularTvBinding
import id.hwaryun.feature_popular_tv.presentation.adapter.TvAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class PopularTvFragment :
    BaseFragment<FragmentPopularTvBinding, PopularTvViewModel>(FragmentPopularTvBinding::inflate) {

    override val viewModel: PopularTvViewModel by viewModels()

    private val adapter: TvAdapter by lazy { TvAdapter(::onItemClick) }

    override fun initView() {
        viewModel.getMostPopularTvs()

        binding.etSearch.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrBlank()) {
                viewModel.getMostPopularTvs()
            } else {
                lifecycleScope.launch { viewModel.queryChannel.send(text.toString()) }
            }
        }
    }

    override fun observeData() {
        viewModel.popularTvs.observe(viewLifecycleOwner) {
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

    private fun initData(movies: List<PopularTvViewParam>) {
        binding.rvTvs.adapter = adapter
        binding.rvTvs.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter.submitList(movies)
    }

    private fun onItemClick(tv: PopularTvViewParam) {
        val directions = PopularTvFragmentDirections.actionPopularTvFragmentToTvDetailFragment(tv.id)
        findNavController().navigate(directions)
    }
}