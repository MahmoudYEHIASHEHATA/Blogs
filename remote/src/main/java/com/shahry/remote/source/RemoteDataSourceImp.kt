package com.shahry.remote.source

import com.shahry.common.Mapper
import com.shahry.data.model.AuthorDTO
import com.shahry.data.model.PostDTO
import com.shahry.data.repository.RemoteDataSource
import com.shahry.remote.api.ApiService
import com.shahry.remote.model.AuthorResponseNetwork
import com.shahry.remote.model.PostResponseNetwork
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(
    private val apiService: ApiService,
    private val authorMapper: Mapper<AuthorResponseNetwork, AuthorDTO>,
    private val postMapper: Mapper<PostResponseNetwork, PostDTO>
) : RemoteDataSource {

    override suspend fun getAuthors(): List<AuthorDTO> {
        val networkData = apiService.getAuthors()
        return authorMapper.fromList(networkData)
    }

    override suspend fun getPostsByAuthor(authorId: Int): List<PostDTO> {
        val networkData = apiService.getAuthorPosts(authorId)
        return postMapper.fromList(networkData)
    }
}