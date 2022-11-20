package com.shahry.feature.model

data class PostUiModel(
    val id: Int,
    val date: String,
    val title: String,
    val body: String,
    val imageUrl: String,
    val authorId: Int
)
