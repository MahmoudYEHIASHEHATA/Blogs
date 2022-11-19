package com.shahry.remote.api

import com.shahry.remote.model.AuthorResponseNetwork
import com.shahry.remote.model.PostResponseNetwork
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("author")
    suspend fun getAuthors(): List<AuthorResponseNetwork>

    @GET("posts")
    suspend fun getAuthorPosts(
        @Query("authorId") authorId: Int,
    ): List<PostResponseNetwork>
}