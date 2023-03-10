package com.choice.irepository

import com.choice.core.modules.Network
import com.choice.core.remote.performnetworkCall
import com.choice.core.util.IResult
import com.choice.core.util.RepositoryException
import com.choice.local.dao.RecipeDao
import com.choice.model.Recipe
import com.choice.remote.ApiFoodFork
import com.choice.local.mapping.toDomain
import com.choice.local.mapping.toEntity
import com.choice.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class IRecipeRepository @Inject constructor(
    @Network.Food2Fork private val webservice: ApiFoodFork,
    private val dao: RecipeDao
) : RecipeRepository {

    override suspend fun getAllRecipes(): Flow<IResult<List<Recipe>>> {
        return flow {

            dao.getAll().collect {
                if (it.isNotEmpty()) {
                    emit(IResult.OnSuccess(it.map { i -> i.toDomain() }))
                    return@collect
                }


                emit(IResult.OnLoading(true, "looking for ingredients..."))

                performnetworkCall {
                    webservice.search()
                }.catch {
                    emit(IResult.OnFailed(it))
                    return@catch
                }.collect {
                    when (it) {
                        is IResult.OnSuccess -> {
                            it.response.results?.let { recipe ->
                                dao.insert(recipe.map { i -> i.toEntity() })
                                emit(IResult.OnSuccess(recipe))
                                emit(IResult.OnLoading(false))
                                return@collect
                            }
                                ?: emit(IResult.OnFailed(RepositoryException("list is null\n\n${it.response.results}")))
                        }

                        is IResult.OnFailed -> {
                            emit(IResult.OnLoading(false))
                            emit(IResult.OnFailed(it.throwable))
                        }
                    }

                }
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getAllFavorites(): Flow<IResult<List<Recipe>>> {
        return flow{
            dao.getAllFavorites().collect{
                emit(IResult.OnSuccess(it.map { i -> i.toDomain() }))
            }
        }
    }


    override suspend fun setFavorite(recipe: Recipe) {
        dao.favorite(recipe.id ?: -1, !recipe.favorite)
    }

    override suspend fun searchRecipe(query: String): Flow<IResult<List<Recipe>>> {
        return flow {
            dao.searchRecipeList(query).apply {
                emit(IResult.OnSuccess(this.map { i -> i.toDomain() }))
            }
        }
    }

    override suspend fun getRecipeById(id: Int): Recipe? {
        return dao.getById(id)?.toDomain()
    }

}
