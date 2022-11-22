package com.shahry.data.repository

import com.shahry.data.model.AuthorDTO
import com.shahry.data.model.PostDTO

/**
 * Methods of Remote Data Source
 */
interface RemoteDataSource {

  suspend fun getAuthors() : List<AuthorDTO>

  suspend fun getPostsByAuthor(authorId :Int) : List<PostDTO>
}