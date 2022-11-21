package com.shahry.domain

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.google.common.truth.Truth
import com.shahry.common.Resource
import com.shahry.domain.repository.Repository
import com.shahry.domain.usecase.GetAuthorPostsUseCase
import com.shahry.domain.utils.MainCoroutineRule
import com.shahry.domain.utils.TestDataGenerator
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@SmallTest
class GetAuthorPostsUseCaseTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var repository: Repository

    private lateinit var getAuthorPostsUseCase: GetAuthorPostsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        getAuthorPostsUseCase = GetAuthorPostsUseCase(
            repository = repository,
            dispatcher = mainCoroutineRule.dispatcher
        )
    }

    @Test
    fun test_get_author_posts_success() = run {
        runBlocking {
            val postsItems = TestDataGenerator.generateAuthorPostItems()
            val postsFlow = flowOf(Resource.Success(postsItems))

            // Given
            coEvery { repository.getAuthorPosts(12) } returns postsFlow

            // When & Assertions
            val result = getAuthorPostsUseCase.execute(12)
            result.test {
                // Expect Resource.Success
                val expected = expectItem()
                val expectedData = (expected as Resource.Success).data
                Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
                Truth.assertThat(expectedData).isEqualTo(postsItems)
                expectComplete()
            }
            // Then
            coVerify { repository.getAuthorPosts(12) }
        }
    }


    @Test
    fun test_get_author_posts_fail() = run {
        runBlocking {
            val errorFlow = flowOf(Resource.Error(Exception()))

            // Given
            coEvery { repository.getAuthorPosts(12) } returns errorFlow

            // When & Assertions
            val result = getAuthorPostsUseCase.execute(12)
            result.test {
                // Expect Resource.Error
                Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
                expectComplete()
            }

            // Then
            coVerify { repository.getAuthorPosts(12) }
        }
    }


    @Test
    fun test_get_author_posts_fail_pass_paameter_with_null() = run {
        runBlocking {
            val errorFlow = flowOf(Resource.Error(Exception()))
            // Given
            coEvery { repository.getAuthorPosts(12) } returns errorFlow
            // When & Assertions
            val result = getAuthorPostsUseCase.execute(null)
            result.test {
                // Expect Resource.Error
                Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
                expectComplete()
            }
        }
    }
}