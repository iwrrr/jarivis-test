package id.hwaryun.feature_search.presentation

import androidx.fragment.app.viewModels
import id.hwaryun.core_common.base.BaseFragment
import id.hwaryun.feature_search.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(FragmentSearchBinding::inflate) {

    override val viewModel: SearchViewModel by viewModels()

    override fun initView() {

    }

    override fun observeData() {

    }
}