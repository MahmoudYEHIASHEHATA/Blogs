package com.shahry.domain.usecase

import com.shahry.common.Resource
import com.shahry.domain.entity.AuthorEntity
import com.shahry.domain.qualifiers.IoDispatcher
import com.shahry.domain.repository.Repository
import com.shahry.domain.usecase.core.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Use Case class for get Authors list
 */
class GetAuthorsListUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<List<AuthorEntity>, Nothing>() {

    override suspend fun buildRequest(params: Nothing?): Flow<Resource<List<AuthorEntity>>> {
        return repository.getAuthors().flowOn(dispatcher)
    }
}