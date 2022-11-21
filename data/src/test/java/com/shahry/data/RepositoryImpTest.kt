package com.shahry.data

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.google.common.truth.Truth
import com.shahry.common.Resource
import com.shahry.data.mapper.AuthorDataDomainMapper
import com.shahry.data.mapper.PostDataDomainMapper
import com.shahry.data.repository.LocalDataSource
import com.shahry.data.repository.RemoteDataSource
import com.shahry.data.repository.RepositoryImp
import com.shahry.data.utils.TestDataGenerator
import com.shahry.domain.repository.Repository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@SmallTest
class RepositoryImpTest {

    @MockK
    private lateinit var localDataSource: LocalDataSource

    @MockK
    private lateinit var remoteDataSource: RemoteDataSource

    private val authorMapper = AuthorDataDomainMapper()
    private val postMapper = PostDataDomainMapper()

    private lateinit var repository: Repository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create RepositoryImp before every test
        repository = RepositoryImp(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource,
            authorMapper = authorMapper,
            postMapper = postMapper
        )
    }

    @Test
    fun test_get_posts_remote_success() = runBlockingTest {

        val postsItems = TestDataGenerator.generateAuthorPostItems()
        val expected = MutableList(postsItems.size) { index -> index.toLong() }

        // Given
        coEvery { remoteDataSource.getPostsByAuthor(4) } returns postsItems
        coEvery { localDataSource.addPosts(postsItems) } returns expected
        coEvery { localDataSource.getPostsByAuthor(any()) } returns postsItems
        // When & Assertions
        val flow = repository.getAuthorPosts(4)
        flow.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData).isEqualTo(postMapper.fromList(postsItems))
            expectComplete()
        }

        // Then
        coVerify { remoteDataSource.getPostsByAuthor(4) }
        coVerify { localDataSource.addPosts(postsItems) }
    }

    @Test
    fun test_get_posts_remote_fail_local_success() = runBlockingTest {

        val postsItems = TestDataGenerator.generateAuthorPostItems()

        // Given
        coEvery { remoteDataSource.getPostsByAuthor(4) } throws Exception()
        coEvery { localDataSource.getPostsByAuthor(any()) } returns postsItems
        // When & Assertions
        val flow = repository.getAuthorPosts(4)
        flow.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData).isEqualTo(postMapper.fromList(postsItems))
            expectComplete()
        }

        // Then
        coVerify { remoteDataSource.getPostsByAuthor(4) }
        coVerify { localDataSource.getPostsByAuthor(4) }
    }

    @Test
    fun test_get_posts_remote_fail_local_fail() = runBlockingTest {

        // Given
        coEvery { remoteDataSource.getPostsByAuthor(4) } throws Exception()
        coEvery { localDataSource.getPostsByAuthor(any()) } throws Exception()
        // When & Assertions
        val flow = repository.getAuthorPosts(4)
        flow.test {
            // Expect Resource.Error
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }

        // Then
        coVerify { remoteDataSource.getPostsByAuthor(4) }
        coVerify { localDataSource.getPostsByAuthor(4) }
    }


    @Test
    fun test_get_author_remote_success() = runBlockingTest {

        val authorItems = TestDataGenerator.generateAuthorsItems()
        val expected = MutableList(authorItems.size) { index -> index.toLong() }

        // Given
        coEvery { remoteDataSource.getAuthors() } returns authorItems
        coEvery { localDataSource.addAllAuthor(authorItems) } returns expected
        coEvery { localDataSource.getAllAuthors() } returns authorItems
        // When & Assertions
        val flow = repository.getAuthors()
        flow.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData).isEqualTo(authorMapper.fromList(authorItems))
            expectComplete()
        }

        // Then
        coVerify { remoteDataSource.getAuthors() }
        coVerify { localDataSource.addAllAuthor(authorItems) }
    }

    @Test
    fun test_get_author_remote_fail_local_success() = runBlockingTest {

        val authorItems = TestDataGenerator.generateAuthorsItems()

        // Given
        coEvery { remoteDataSource.getAuthors() } throws Exception()
        coEvery { localDataSource.getAllAuthors() } returns authorItems
        // When & Assertions
        val flow = repository.getAuthors()
        flow.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData).isEqualTo(authorMapper.fromList(authorItems))
            expectComplete()
        }

        // Then
        coVerify { remoteDataSource.getAuthors() }
        coVerify { localDataSource.getAllAuthors() }
    }

    @Test
    fun test_get_author_remote_fail_local_fail() = runBlockingTest {


        // Given
        coEvery { remoteDataSource.getAuthors() } throws Exception()
        coEvery { localDataSource.getAllAuthors() } throws Exception()
        // When & Assertions
        val flow = repository.getAuthors()
        flow.test {
//            // Expect Resource.Error
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }

        // Then
        coVerify { remoteDataSource.getAuthors() }
        coVerify { localDataSource.getAllAuthors() }
    }
}