package com.shahry.feature

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.google.common.truth.Truth
import com.shahry.common.Resource
import com.shahry.domain.usecase.GetAuthorsListUseCase
import com.shahry.feature.mapper.AuthorDomainUiMapper
import com.shahry.feature.ui.contract.MainContract
import com.shahry.feature.ui.vm.MainViewModel
import com.shahry.feature.utils.MainCoroutineRule
import com.shahry.feature.utils.TestDataGenerator
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime


@ExperimentalTime
@ExperimentalCoroutinesApi
@SmallTest
class MainViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var getAuthorsListUseCase: GetAuthorsListUseCase

    private val authorMapper = AuthorDomainUiMapper()

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create MainViewModel before every test
        mainViewModel = MainViewModel(
            getAuthorUseCase = getAuthorsListUseCase,
            authorMapper = authorMapper
        )
    }

    @Test
    fun test_fetch_authorsItems_success() = runBlockingTest {

        val authorsItems = TestDataGenerator.generateAuthorsItems()
        val authorsFlow = flowOf(Resource.Success(authorsItems))

        // Given
        coEvery { getAuthorsListUseCase.execute(null) } returns authorsFlow

        // When && Assertions
        mainViewModel.uiState.test {
            mainViewModel.setEvent(MainContract.Event.OnFetchAuthors)
            // Expect Resource.Idle from initial state
            Truth.assertThat(expectItem()).isEqualTo(
                MainContract.State(
                    authorState = MainContract.AuthorState.Idle,
                    selectedAuthor  = null
                )
            )
            // Expect Resource.Loading
            Truth.assertThat(expectItem()).isEqualTo(
                MainContract.State(
                    authorState = MainContract.AuthorState.Loading,
                    selectedAuthor = null
                )
            )
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData =
                (expected.authorState as MainContract.AuthorState.Success).authorList
            Truth.assertThat(expected).isEqualTo(
                MainContract.State(
                    authorState = MainContract.AuthorState.Success(
                            authorMapper.fromList(
                                authorsItems
                            )
                    ),
                    selectedAuthor = null
                )
            )
            Truth.assertThat(expectedData)
                .containsExactlyElementsIn(authorMapper.fromList(authorsItems))


            //Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }


        // Then
        coVerify { getAuthorsListUseCase.execute(null) }
    }

    @Test
    fun test_fetch_weather_fail() = runBlockingTest {

        val authorsErrorFlow = flowOf(Resource.Error(Exception("error string")))

        // Given
        coEvery { getAuthorsListUseCase.execute(null) } returns authorsErrorFlow

        // When && Assertions (UiState)
        mainViewModel.uiState.test {
            // Call method inside of this scope
            mainViewModel.setEvent(MainContract.Event.OnFetchAuthors)
            // Expect Resource.Idle from initial state
            Truth.assertThat(expectItem()).isEqualTo(
                MainContract.State(
                    authorState = MainContract.AuthorState.Idle,
                    selectedAuthor = null
                )
            )
            // Expect Resource.Loading
            Truth.assertThat(expectItem()).isEqualTo(
                MainContract.State(
                    authorState = MainContract.AuthorState.Loading,
                    selectedAuthor = null
                )
            )
            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }

        // When && Assertions (UiEffect)
        mainViewModel.effect.test {
            // Expect ShowError Effect
            val expected = expectItem()
            val expectedData = (expected as MainContract.Effect.ShowError).message
            Truth.assertThat(expected).isEqualTo(
                MainContract.Effect.ShowError("error string")
            )
            Truth.assertThat(expectedData).isEqualTo("error string")
            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }


        // Then
        coVerify { getAuthorsListUseCase.execute(null) }
    }


    @Test
    fun test_select_author_item() = runBlockingTest {

        val author = TestDataGenerator.generateAuthorsItems().first()

        // Given (no-op)

        // When && Assertions
        mainViewModel.uiState.test {
            // Call method inside of this scope
            // For more info, see https://github.com/cashapp/turbine/issues/19
            mainViewModel.setEvent(
                MainContract.Event.OnAuthorItemClicked(
                    author = authorMapper.from(
                        author
                    )
                )
            )
            // Expect Resource.Idle from initial state
            Truth.assertThat(expectItem()).isEqualTo(
                MainContract.State(
                    authorState = MainContract.AuthorState.Idle,
                    selectedAuthor = null
                )
            )
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = expected.selectedAuthor
            Truth.assertThat(expected).isEqualTo(
                MainContract.State(
                    authorState = MainContract.AuthorState.Idle,
                    selectedAuthor = authorMapper.from(author)
                )
            )
            Truth.assertThat(expectedData).isEqualTo(authorMapper.from(author))
            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }
        // Then (no-op)
    }
}