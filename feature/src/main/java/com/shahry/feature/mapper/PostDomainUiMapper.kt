package com.shahry.feature.mapper

import com.shahry.common.Mapper
import com.shahry.domain.entity.PostEntity
import com.shahry.feature.core.formatToEgyptianDateTimeDefaults
import com.shahry.feature.model.PostUiModel
import javax.inject.Inject

class PostDomainUiMapper @Inject constructor() : Mapper<PostEntity, PostUiModel> {
    override fun from(i: PostEntity?): PostUiModel {
        return PostUiModel(
            id = i?.id ?: -1,
            date = i?.date?.formatToEgyptianDateTimeDefaults() ?: "",
            title = i?.title ?: "",
            body = i?.body ?: "",
            imageUrl = i?.imageUrl ?: "",
            authorId = i?.authorId ?: -1
        )
    }

    override fun to(o: PostUiModel?): PostEntity {
        return PostEntity(
            id = o?.id ?: -1,
            date = o?.date ?: "",
            title = o?.title ?: "",
            body = o?.body ?: "",
            imageUrl = o?.imageUrl ?: "",
            authorId = o?.authorId ?: -1
        )
    }
}