package com.shahry.local

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.shahry.data.repository.LocalDataSource
import com.shahry.local.database.AuthorDAO
import com.shahry.local.database.PostDAO
import com.shahry.local.mapper.AuthorLocalDataMapper
import com.shahry.local.mapper.PostLocalDataMapper
import com.shahry.local.sourc.LocalDataSourceImp
import com.shahry.local.utils.TestData
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
class LocalDataSourceImpTest {

    @MockK
    private lateinit var authorDAO: AuthorDAO

    @MockK
    private lateinit var postDAO: PostDAO

    private val authorMapper = AuthorLocalDataMapper()
    private val postMapper = PostLocalDataMapper()

    private lateinit var localDataSource : LocalDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create LocalDataSourceImp before every test
        localDataSource = LocalDataSourceImp(
            authorDAO = authorDAO,
            postDAO = postDAO,
            authorMapper=authorMapper,
            postMapper=postMapper
        )
    }

    @Test
    fun test_add_authors_items_success() = runBlockingTest {

        val authorItems = authorMapper.fromList(list = TestData.generateAuthorsItems())
        val expected = MutableList(authorItems.size) { index -> index.toLong() }

        // Given
        coEvery { authorDAO.addAllAuthors(any()) } returns expected

        // When
        val returned = localDataSource.addAllAuthor(authorItems)

        // Then
        coVerify { authorDAO.addAllAuthors(any()) }

        // Assertion
        Truth.assertThat(returned).hasSize(expected.size)
    }

    @Test
    fun test_get_author_items_success() = runBlockingTest {

        val authorItems = TestData.generateAuthorsItems()
        val expected = authorMapper.fromList(list = authorItems)

        // Given
        coEvery { authorDAO.getAllAuthors() } returns authorItems

        // When
        val returned = localDataSource.getAllAuthors()

        // Then
        coVerify { authorDAO.getAllAuthors() }

        // Assertion
        Truth.assertThat(returned).containsExactlyElementsIn(expected)
    }

    @Test
    fun test_add_author_posts_success() = runBlockingTest {

        val authorPostsItems = postMapper.fromList(list = TestData.generateAuthorPostItems())
        val expected = MutableList(authorPostsItems.size) { index -> index.toLong() }

        // Given
        coEvery { postDAO.addPosts(any()) } returns expected

        // When
        val returned = localDataSource.addPosts(authorPostsItems)

        // Then
        coVerify { postDAO.addPosts(any()) }

        // Assertion
        Truth.assertThat(returned).hasSize(expected.size)
    }

    @Test
    fun test_get_author_posts_success() = runBlockingTest {

        val authorPostsItems = TestData.generateAuthorPostItems()
        val expected = postMapper.fromList(list = authorPostsItems)

        // Given
        coEvery { postDAO.getPosts(any()) } returns authorPostsItems

        // When
        val returned = localDataSource.getPostsByAuthor(4)

        // Then
        coVerify { postDAO.getPosts(any()) }

        // Assertion
        Truth.assertThat(returned).containsExactlyElementsIn(expected)
    }
}