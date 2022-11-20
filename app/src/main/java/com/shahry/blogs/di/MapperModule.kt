package com.shahry.blogs.di

import com.shahry.common.Mapper
import com.shahry.data.mapper.AuthorDataDomainMapper
import com.shahry.data.mapper.PostDataDomainMapper
import com.shahry.data.model.AuthorDTO
import com.shahry.data.model.PostDTO
import com.shahry.domain.entity.AuthorEntity
import com.shahry.domain.entity.PostEntity
import com.shahry.feature.mapper.AuthorDomainUiMapper
import com.shahry.feature.mapper.PostDomainUiMapper
import com.shahry.feature.model.AuthorUiModel
import com.shahry.feature.model.PostUiModel
import com.shahry.local.mapper.AuthorLocalDataMapper
import com.shahry.local.mapper.PostLocalDataMapper
import com.shahry.local.model.AuthorLocalModel
import com.shahry.local.model.PostLocalModel
import com.shahry.remote.mapper.AuthorNetworkDataMapper
import com.shahry.remote.mapper.PostNetworkDataMapper
import com.shahry.remote.model.AuthorResponseNetwork
import com.shahry.remote.model.PostResponseNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Module that holds Mappers
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    //region Locale Mappers
    @Binds
    abstract fun bindsAuthorLocalDataMapper(mapper : AuthorLocalDataMapper) : Mapper<AuthorLocalModel, AuthorDTO>

    @Binds
    abstract fun bindsPostLocalDataMapper(mapper : PostLocalDataMapper) : Mapper<PostLocalModel, PostDTO>
    //endregion


    //region Data Mappers
    @Binds
    abstract fun bindsAuthorDataDomainMapper(mapper : AuthorDataDomainMapper) : Mapper<AuthorDTO, AuthorEntity>

    @Binds
    abstract fun bindsPostDataDomainMapper(mapper : PostDataDomainMapper) : Mapper<PostDTO, PostEntity>
    //endregion

    //region Presentation Mappers
    @Binds
    abstract fun bindsAuthorDomainUiMapper(mapper : AuthorDomainUiMapper) : Mapper<AuthorEntity, AuthorUiModel>

    @Binds
    abstract fun bindsPostDomainUiMapper(mapper : PostDomainUiMapper) : Mapper<PostEntity, PostUiModel>
    //endregion


    //region Remote Mappers

    @Binds
    abstract fun bindsAuthorNetworkDataMapper(mapper: AuthorNetworkDataMapper): Mapper<AuthorResponseNetwork, AuthorDTO>

    @Binds
    abstract fun bindsPostNetworkDataMapper(mapper: PostNetworkDataMapper): Mapper<PostResponseNetwork, PostDTO>
    //endregion

}