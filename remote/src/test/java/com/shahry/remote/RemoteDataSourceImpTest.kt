package com.shahry.remote

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.shahry.data.repository.RemoteDataSource
import com.shahry.remote.api.ApiService
import com.shahry.remote.mapper.AuthorNetworkDataMapper
import com.shahry.remote.mapper.PostNetworkDataMapper
import com.shahry.remote.source.RemoteDataSourceImp
import com.shahry.remote.utils.TestDataGenerator
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
@SmallTest
class RemoteDataSourceImpTest {

    @MockK
    private lateinit var apiService: ApiService
    private val authorMapper = AuthorNetworkDataMapper()
    private val postMapper = PostNetworkDataMapper()

    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create RemoteDataSourceImp before every test
        remoteDataSource = RemoteDataSourceImp(
            apiService = apiService,
            authorMapper = authorMapper,
            postMapper = postMapper
        )
    }


    @Test
    fun test_get_Authors_success() = runBlockingTest {

        val authorNetwork = TestDataGenerator.generateAuthor()

        // Given
        coEvery { apiService.getAuthors() } returns authorNetwork

        // When
        val result = remoteDataSource.getAuthors()

        // Then
        coVerify { apiService.getAuthors() }

        // Assertion
        val expected = authorMapper.fromList(authorNetwork)
        Truth.assertThat(result).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_get_Author_fail() = runBlockingTest {

        // Given
        coEvery { apiService.getAuthors() } throws Exception()

        // When
        remoteDataSource.getAuthors()

        // Then
        coVerify { apiService.getAuthors() }

    }


    @Test
    fun test_get_AuthorPosts_success() = runBlockingTest {

        val postsNetwork = TestDataGenerator.generatePosts()

        // Given
        coEvery { apiService.getAuthorPosts(any()) } returns postsNetwork

        // When
        val result = remoteDataSource.getPostsByAuthor(12)

        // Then
        coVerify { apiService.getAuthorPosts(any()) }

        // Assertion
        val expected = postMapper.fromList(postsNetwork)
        Truth.assertThat(result).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_get_AuthorPosts_fail() = runBlockingTest {

        // Given
        coEvery { apiService.getAuthorPosts(any()) } throws Exception()

        // When
        remoteDataSource.getPostsByAuthor(12)

        // Then
        coVerify { apiService.getAuthorPosts(any()) }

    }
}