package com.shahry.domain.usecase

import com.shahry.common.Resource
import com.shahry.domain.entity.PostEntity
import com.shahry.domain.qualifiers.IoDispatcher
import com.shahry.domain.repository.Repository
import com.shahry.domain.usecase.core.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Use Case class for get Author posts list
 */
class GetAuthorPostsUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
): BaseUseCase<List<PostEntity>,String>() {

    override suspend fun buildRequest(params: String?): Flow<Resource<List<PostEntity>>> {

        if (params == null) {
            return flow {
                emit(Resource.Error(Exception("id can not be null")))
            }.flowOn(dispatcher)
        }
        return repository.getAuthorPosts(params).flowOn(dispatcher)
    }
}