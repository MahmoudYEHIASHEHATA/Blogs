package com.shahry.data.mapper

import com.shahry.common.Mapper
import com.shahry.data.model.PostDTO
import com.shahry.domain.entity.PostEntity
import javax.inject.Inject

class PostDataDomainMapper @Inject constructor(): Mapper<PostDTO,PostEntity> {
    override fun from(i: PostDTO?): PostEntity {
        return PostEntity(
            id = i?.id ?: -1,
            date = i?.date ?: "",
            title = i?.title ?: "",
            body = i?.body ?: "",
            imageUrl = i?.imageUrl ?: ""
        )
    }

    override fun to(o: PostEntity?): PostDTO {
        return PostDTO(
            id = o?.id ?: -1,
            date = o?.date ?: "",
            title = o?.title ?: "",
            body = o?.body ?: "",
            imageUrl = o?.imageUrl ?: ""
        )
    }
}