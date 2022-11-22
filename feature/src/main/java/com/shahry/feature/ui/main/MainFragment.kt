package com.shahry.feature.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.shahry.base.BaseFragment
import com.shahry.feature.core.showErrorDialog
import com.shahry.feature.databinding.FragmentMainBinding
import com.shahry.feature.ui.contract.MainContract
import com.shahry.feature.ui.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel: MainViewModel by viewModels()
    private val adapter: AuthorAdapter by lazy {
        AuthorAdapter { author->
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(author)
            findNavController().navigate(action)
        }
    }

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        binding.rvAuthor.adapter = adapter
        viewModel.setEvent(MainContract.Event.OnFetchAuthors)
        initObservers()
    }

    /**
     * Initialize Observers
     */
    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (val state = it.authorState) {
                        is MainContract.AuthorState.Idle -> {
                            binding.loadingPb.isVisible = false
                        }
                        is MainContract.AuthorState.Loading -> {
                            binding.loadingPb.isVisible = true
                        }
                        is MainContract.AuthorState.Success -> {
                            val data = state.authorList
                            adapter.submitList(data)
                            binding.loadingPb.isVisible = false
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect {
                    when (it) {
                        is MainContract.Effect.ShowError -> {
                            val msg = it.message
                            msg?.let { message ->
                                context?.showErrorDialog(message) {
                                    viewModel.setEvent(
                                        MainContract.Event.OnFetchAuthors
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}