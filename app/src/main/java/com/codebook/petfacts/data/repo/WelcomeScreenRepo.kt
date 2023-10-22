package com.codebook.petfacts.data.repo

import com.codebook.petfacts.NetworkState
import com.codebook.petfacts.data.api.CatFactsApiService
import com.codebook.petfacts.data.api.DogFactsApiService
import com.codebook.petfacts.data.datasrc.CatFact
import com.codebook.petfacts.data.datasrc.DogFact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class WelcomeScreenRepo @Inject constructor(
    private val catsApi: CatFactsApiService, private val dogApi: DogFactsApiService
) {
    suspend fun getCatsFacts(): Flow<NetworkState<CatFact>> {
        return flow {
            emit(NetworkState.Loading())
            val response = catsApi.getCatFact()
            if (response.isSuccessful && response.body() != null) {
                emit(NetworkState.Success(response.body()!!))
            } else {
                emit(NetworkState.Error(response.errorBody()!!))
            }
        }.catch {
            emit(NetworkState.Error("Something went error!"))
        }
    }

    suspend fun getDogsFacts(): Flow<NetworkState<DogFact>> {
        return flow {
            emit(NetworkState.Loading())
            val response = dogApi.getDogFact()
            if (response.isSuccessful && response.body() != null) {
                emit(NetworkState.Success(response.body()!!))
            } else {
                emit(NetworkState.Error(response.errorBody()!!))
            }
        }.catch {
            emit(NetworkState.Error(" Something went error!"))
        }
    }
}