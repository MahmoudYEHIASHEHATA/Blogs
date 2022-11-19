package com.shahry.local.sourc

import com.shahry.common.Mapper
import com.shahry.data.model.AuthorDTO
import com.shahry.data.model.PostDTO
import com.shahry.data.repository.LocalDataSource
import com.shahry.local.database.AuthorDAO
import com.shahry.local.database.PostDAO
import com.shahry.local.model.AuthorLocalModel
import com.shahry.local.model.PostLocalModel
import javax.inject.Inject

/**
 * Implementation of [LocalDataSource] Source
 */
class LocalDataSourceImp  @Inject constructor(
    private val authorDAO: AuthorDAO,
    private val postDAO: PostDAO,
    private val authorMapper : Mapper<AuthorLocalModel, AuthorDTO>,
    private val postMapper : Mapper<PostLocalModel,PostDTO>
) : LocalDataSource {

    override suspend fun addAllAuthor(authors: List<AuthorDTO>): List<Long> {
        val authorLocalModel = authorMapper.toList(authors)
        return authorDAO.addAllAuthors(authorLocalModel)
    }

    override suspend fun getAllAuthors(): List<AuthorDTO> {
        val authorLocalModel = authorDAO.getAllAuthors()
        return authorMapper.fromList(authorLocalModel)
    }

    override suspend fun addPosts(posts: List<PostDTO>): List<Long> {
        val  postLocalModel= postMapper.toList(posts)
        return postDAO.addPosts(postLocalModel)
    }

    override suspend fun getPostsByAuthor(authorId: Int): List<PostDTO> {
        val postLocalModel = postDAO.getPosts(authorId)
        return postMapper.fromList(postLocalModel)
    }
}