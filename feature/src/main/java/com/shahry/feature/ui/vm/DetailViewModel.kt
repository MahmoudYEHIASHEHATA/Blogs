package com.shahry.feature.ui.vm

import androidx.lifecycle.viewModelScope
import com.shahry.base.BaseViewModel
import com.shahry.common.Mapper
import com.shahry.common.Resource
import com.shahry.domain.entity.PostEntity
import com.shahry.domain.usecase.GetAuthorPostsUseCase
import com.shahry.feature.model.PostUiModel
import com.shahry.feature.ui.contract.DetailContract
import com.shahry.feature.ui.contract.MainContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getAuthorPostsUseCase: GetAuthorPostsUseCase,
    private val postMapper: Mapper<PostEntity, PostUiModel>
) : BaseViewModel<DetailContract.Event, DetailContract.State, DetailContract.Effect>() {
    override fun createInitialState(): DetailContract.State {
        return DetailContract.State(
            postState = DetailContract.PostState.Idle,
        )
    }

    override fun handleEvent(event: DetailContract.Event) {
        when (event) {
            is DetailContract.Event.OnFetchPosts -> {
                fetchAuthorPosts(event.authorId)
            }
        }

    }

    /**
     * Fetch author posts
     */
    private fun fetchAuthorPosts(authorId: Int) {
        viewModelScope.launch {
            getAuthorPostsUseCase.execute(authorId)
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            // Set State
                            setState { copy(postState = DetailContract.PostState.Loading) }
                        }
                        is Resource.Empty -> {
                            // Set State
                            setState { copy(postState = DetailContract.PostState.Idle) }
                        }
                        is Resource.Success -> {
                            // Set State
                            val postsList = postMapper.fromList(it.data)
                            setState {
                                copy(
                                    postState = DetailContract.PostState.Success(
                                        postsList = postsList
                                    )
                                )
                            }
                        }
                        is Resource.Error -> {
                            // Set Effect
                            setEffect { DetailContract.Effect.ShowError(message = it.exception.message) }
                        }
                    }
                }
        }
    }


}