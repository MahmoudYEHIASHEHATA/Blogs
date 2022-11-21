package com.shahry.feature.ui.contract

import com.example.base.UiState
import com.shahry.base.UiEffect
import com.shahry.base.UiEvent
import com.shahry.feature.model.AuthorUiModel
import com.shahry.feature.model.PostUiModel

class DetailContract {

    sealed class Event : UiEvent {
        data class OnFetchPosts(val authorId: Int) : Event()
    }

    data class State(
        val postState: PostState
    ) : UiState

    sealed class PostState {
        object Idle : PostState()
        object Loading : PostState()
        data class Success(val postsList: List<PostUiModel>) : PostState()
    }

    sealed class Effect : UiEffect {

        data class ShowError(val message: String?) : Effect()

    }
}