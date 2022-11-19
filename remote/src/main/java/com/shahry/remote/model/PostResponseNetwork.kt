package com.shahry.remote.model

data class PostResponseNetwork(
    val id: Int = 0,
    val date: String = "",
    val title: String = "",
    val body: String = "",
    val imageUrl: String = "",
    val authorId: Int = 0
)
