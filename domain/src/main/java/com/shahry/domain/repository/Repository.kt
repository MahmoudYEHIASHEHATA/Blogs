package com.shahry.domain.repository

import com.shahry.common.Resource
import com.shahry.domain.entity.AuthorEntity
import com.shahry.domain.entity.PostEntity
import kotlinx.coroutines.flow.Flow

/**
 * Methods of Repository
 */
interface Repository {

    suspend fun getAuthors(): Flow<Resource<List<AuthorEntity>>>

    suspend fun getAuthorPosts(authorId: Int): Flow<Resource<List<PostEntity>>>
}