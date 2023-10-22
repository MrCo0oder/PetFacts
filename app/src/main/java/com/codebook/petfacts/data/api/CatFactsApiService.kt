package com.codebook.petfacts.data.api

import com.codebook.petfacts.data.datasrc.CatFact
import retrofit2.Response
import retrofit2.http.GET

interface CatFactsApiService {
    // https://catfact.ninja/fact
    @GET("fact")
    suspend fun getCatFact(): Response<CatFact>
}