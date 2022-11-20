package com.shahry.feature.ui.vm

import androidx.lifecycle.viewModelScope
import com.shahry.base.BaseViewModel
import com.shahry.common.Mapper
import com.shahry.common.Resource
import com.shahry.domain.entity.AuthorEntity
import com.shahry.domain.usecase.GetAuthorsListUseCase
import com.shahry.feature.model.AuthorUiModel
import com.shahry.feature.ui.contract.MainContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAuthorUseCase: GetAuthorsListUseCase,
    private val authorMapper: Mapper<AuthorEntity, AuthorUiModel>
) : BaseViewModel<MainContract.Event, MainContract.State, MainContract.Effect>() {

    override fun createInitialState(): MainContract.State {
        return MainContract.State(
            authorState = MainContract.AuthorState.Idle,
            selectedAuthor = null
        )
    }

    override fun handleEvent(event: MainContract.Event) {
        when (event) {
            is MainContract.Event.OnFetchAuthors -> {
                fetchAuthors()
            }
            is MainContract.Event.OnAuthorItemClicked -> {
                val item = event.author
                setSelectedPost(author = item)
            }
        }
    }

    /**
     * Fetch authors
     */
    private fun fetchAuthors(){
        viewModelScope.launch {
            getAuthorUseCase.execute(null)
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            // Set State
                            setState { copy(authorState = MainContract.AuthorState.Loading) }
                        }
                        is Resource.Empty -> {
                            // Set State
                            setState { copy(authorState = MainContract.AuthorState.Idle) }
                        }
                        is Resource.Success -> {
                            // Set State
                            val authorList = authorMapper.fromList(it.data)
                            setState {
                                copy(
                                    authorState = MainContract.AuthorState.Success(
                                        authorList = authorList
                                    )
                                )
                            }
                        }
                        is Resource.Error -> {
                            // Set Effect
                            setEffect { MainContract.Effect.ShowError(message = it.exception.message) }
                        }
                    }
                }
        }
    }

    /**
     * Set selected author item
     */
    private fun setSelectedPost(author : AuthorUiModel?) {
        // Set State
        setState { copy(selectedAuthor = author) }
    }
}