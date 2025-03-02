package com.codebook.petfacts.di

import com.codebook.petfacts.Cat
import com.codebook.petfacts.Dog
import com.codebook.petfacts.data.api.CatFactsApiService
import com.codebook.petfacts.data.api.DogFactsApiService
import com.codebook.petfacts.data.repo.WelcomeScreenRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    @Cat
    fun providesCatsRetroFit(): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        val httpClient = OkHttpClient().newBuilder().apply {
            addInterceptor(httpLoggingInterceptor)
        }
        httpClient.apply {
            readTimeout(30, TimeUnit.SECONDS)
        }
        return Retrofit.Builder()
            .baseUrl("https://catfact.ninja/")
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Dog
    fun providesDogsRetroFit(): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        val httpClient = OkHttpClient().newBuilder().apply {
            addInterceptor(httpLoggingInterceptor)
        }
        httpClient.apply {
            readTimeout(30, TimeUnit.SECONDS)
        }
        return Retrofit.Builder()
            .baseUrl("https://dog-api.kinduff.com/")
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesCatsApi(@Cat retrofit: Retrofit): CatFactsApiService {
        return retrofit.create(CatFactsApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesDogsApi(@Dog retrofit: Retrofit): DogFactsApiService {
        return retrofit.create(DogFactsApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesWelcomeRepo(
        catsApi: CatFactsApiService,
        dogApi: DogFactsApiService
    ): WelcomeScreenRepo {
        return WelcomeScreenRepo(catsApi, dogApi)
    }
}