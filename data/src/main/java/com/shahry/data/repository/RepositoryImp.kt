package com.shahry.data.repository

import com.shahry.common.Mapper
import com.shahry.common.Resource
import com.shahry.data.model.AuthorDTO
import com.shahry.data.model.PostDTO
import com.shahry.domain.entity.AuthorEntity
import com.shahry.domain.entity.PostEntity
import com.shahry.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val authorMapper: Mapper<AuthorDTO,AuthorEntity>,
    private val postMapper: Mapper<PostDTO,PostEntity>
) : Repository{
    override suspend fun getAuthors(): Flow<Resource<List<AuthorEntity>>> {
        return flow {
            try {
                // Get data from RemoteDataSource
                val data = remoteDataSource.getAuthors()
                // Save to local or update if exist
                localDataSource.addAuthor(data)
                // Emit data
                emit(Resource.Success(authorMapper.fromList(data)))
            } catch (ex : Exception) {
                // If remote request fails
                try {
                    // Get data from LocalDataSource
                    val local = localDataSource.getAuthors()
                    // Emit data
                    emit(Resource.Success(authorMapper.fromList(local)))
                } catch (ex1 : Exception) {
                    // Emit error
                    emit(Resource.Error(ex1))
                }
            }
        }
    }

    override suspend fun getAuthorPosts(authorId: Int): Flow<Resource<List<PostEntity>>> {
        return flow {
            try {
                // Get data from RemoteDataSource
                val data = remoteDataSource.getPostsByAuthor(authorId)
                // Save to local or update if exist
                localDataSource.addPosts(data)
                // Emit data
                emit(Resource.Success(postMapper.fromList(data)))
            } catch (ex : Exception) {
                // If remote request fails
                try {
                    // Get data from LocalDataSource
                    val local = localDataSource.getPostsByAuthor(authorId)
                    // Emit data
                    emit(Resource.Success(postMapper.fromList(local)))
                } catch (ex1 : Exception) {
                    // Emit error
                    emit(Resource.Error(ex1))
                }
            }
        }
    }
}