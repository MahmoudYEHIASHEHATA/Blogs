package com.shahry.data.repository

import com.shahry.data.model.AuthorDTO
import com.shahry.data.model.PostDTO

/**
 * Methods of Local Data Source
 */
interface LocalDataSource {

    suspend fun addAuthors(authors: List<AuthorDTO>) : List<Long>

    suspend fun getAuthors() : List<AuthorDTO>

    suspend fun addPosts(posts : List<PostDTO>) : List<Long>

    suspend fun getPostsByAuthor(authorId : Int) : List<PostDTO>
}