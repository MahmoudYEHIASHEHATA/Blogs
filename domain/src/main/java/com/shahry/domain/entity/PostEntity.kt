package com.shahry.domain.entity

data class PostEntity(
    val id: Int,
    val date: String,
    val title: String,
    val body: String,
    val imageUrl: String,
    val authorId : Int
)
