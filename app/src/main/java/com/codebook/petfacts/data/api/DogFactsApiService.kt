package com.codebook.petfacts.data.api

import com.codebook.petfacts.data.datasrc.DogFact
import retrofit2.Response
import retrofit2.http.GET

interface DogFactsApiService {
    //  https://dog-api.kinduff.com/api/facts
    @GET("api/facts")
    suspend fun getDogFact(): Response<DogFact>

}