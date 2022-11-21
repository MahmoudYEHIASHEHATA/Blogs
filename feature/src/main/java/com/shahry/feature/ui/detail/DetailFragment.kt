package com.shahry.feature.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.shahry.base.BaseFragment
import com.shahry.feature.core.showErrorDialog
import com.shahry.feature.databinding.FragmentDetailBinding
import com.shahry.feature.ui.contract.DetailContract
import com.shahry.feature.ui.main.PostAdapter
import com.shahry.feature.ui.vm.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private val args: DetailFragmentArgs by navArgs()
    private var authorId: Int = -1

    private val viewModel: DetailViewModel by viewModels()
    private val adapter: PostAdapter by lazy {
        PostAdapter()
    }

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailBinding
        get() = FragmentDetailBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        binding.rvPosts.adapter = adapter

        args.detail?.let { author ->
            binding.author = author
            authorId = author.id
        }

        viewModel.setEvent(DetailContract.Event.OnFetchPosts(authorId))

        initObservers()
    }


    /**
     * Initialize Observers
     */
    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (val state = it.postState) {
                        is DetailContract.PostState.Idle -> {
                            binding.loadingPb.isVisible = false
                        }
                        is DetailContract.PostState.Loading -> {
                            binding.loadingPb.isVisible = true
                        }
                        is DetailContract.PostState.Success -> {
                            val data = state.postsList
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
                        is DetailContract.Effect.ShowError -> {
                            val msg = it.message
                            msg?.let { message ->
                                context?.showErrorDialog(message) {
                                    viewModel.setEvent(
                                        DetailContract.Event.OnFetchPosts(authorId)
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