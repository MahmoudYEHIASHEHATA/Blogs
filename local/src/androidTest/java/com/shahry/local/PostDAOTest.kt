package com.shahry.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.shahry.local.database.AppDatabase
import com.shahry.local.database.AuthorDAO
import com.shahry.local.database.PostDAO
import com.shahry.local.utils.TestDataGenerator
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class PostDAOTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database : AppDatabase
    private lateinit var authorDao : AuthorDAO
    private lateinit var postDao : PostDAO

    @Before
    fun setUp() {
        hiltRule.inject()
        authorDao = database.authorDao()
        postDao = database.postDao()
    }

    @After
    fun tearDown() {
        database.close()
    }


    @Test
    fun test_add_and_get_authors_items_success() = runBlockingTest {

        val item = TestDataGenerator.generateAuthorsItems()

        authorDao.addAllAuthors(item)

        val items = authorDao.getAllAuthors()

        Truth.assertThat(items).contains(item)

    }

    @Test
    fun test_add_and_get_author_posts_by_id_success() = runBlockingTest {

        val item = TestDataGenerator.generateAuthorPostItems()

        postDao.addPosts(item)

        val result = postDao.getPosts(4)

        Truth.assertThat(item).isEqualTo(result)
    }
}