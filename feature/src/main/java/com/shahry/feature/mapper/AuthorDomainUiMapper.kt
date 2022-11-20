package com.shahry.feature.mapper

import com.shahry.common.Mapper
import com.shahry.domain.entity.AuthorEntity
import com.shahry.feature.model.AuthorUiModel
import javax.inject.Inject

class AuthorDomainUiMapper @Inject constructor() : Mapper<AuthorEntity, AuthorUiModel> {
    override fun from(i: AuthorEntity?): AuthorUiModel {
        return AuthorUiModel(
            id = i?.id ?: -1,
            name = i?.name ?: "",
            userName = i?.userName ?: "",
            email = i?.email ?: "",
            avatarUrl = i?.avatarUrl ?: "",
            lat = i?.lat ?: "",
            long = i?.long ?: ""
        )
    }

    override fun to(o: AuthorUiModel?): AuthorEntity {
        return AuthorEntity(
            id = o?.id ?: -1,
            name = o?.name ?: "",
            userName = o?.userName ?: "",
            email = o?.email ?: "",
            avatarUrl = o?.avatarUrl ?: "",
            lat = o?.lat ?: "",
            long = o?.long ?: ""
        )
    }
}