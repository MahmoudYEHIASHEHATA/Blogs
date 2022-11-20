package com.shahry.feature.ui.contract

import com.example.base.UiState
import com.shahry.base.UiEffect
import com.shahry.base.UiEvent
import com.shahry.feature.model.AuthorUiModel

/**
 * Contract of Main Screen
 */
class MainContract {

    sealed class Event : UiEvent {
        object OnFetchAuthors : Event()
        data class OnAuthorItemClicked(val author: AuthorUiModel) : Event()
    }

    data class State(
        val authorState: AuthorState,
        val selectedAuthor: AuthorUiModel? = null
    ) : UiState

    sealed class AuthorState {
        object Idle : AuthorState()
        object Loading : AuthorState()
        data class Success(val authorList: List<AuthorUiModel>) : AuthorState()
    }

    sealed class Effect : UiEffect {

        data class ShowError(val message: String?) : Effect()

    }
}