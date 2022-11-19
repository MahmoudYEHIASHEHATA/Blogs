package com.shahry.local.mapper

import com.shahry.common.Mapper
import com.shahry.data.model.PostDTO
import com.shahry.local.model.PostLocalModel
import javax.inject.Inject

class PostLocalDataMapper @Inject constructor() : Mapper<PostLocalModel,PostDTO>{
    override fun from(i: PostLocalModel?): PostDTO {
        return PostDTO(
            id = i?.id ?: -1,
            date = i?.date ?: "",
            title = i?.title ?: "",
            body = i?.body ?: "",
            imageUrl = i?.imageUrl ?: "",
            authorId = i?.authorId ?: -1
        )
    }

    override fun to(o: PostDTO?): PostLocalModel {
        return PostLocalModel(
            id = o?.id ?: -1,
            date = o?.date ?: "",
            title = o?.title ?: "",
            body = o?.body ?: "",
            imageUrl = o?.imageUrl ?: "",
            authorId = o?.authorId ?: -1
        )
    }
}