package com.gallapillo.dogs.di

import android.app.Application
import android.content.Context
import coil.ImageLoader
import coil.request.CachePolicy
import com.gallapillo.dogs.data.net.breed.BreedApi
import com.gallapillo.dogs.data.net.breed.BreedsRemoteDataSource
import com.gallapillo.dogs.data.BreedRepository
import com.gallapillo.dogs.data.DogsRepository
import com.gallapillo.dogs.data.local.breed.BreedDao
import com.gallapillo.dogs.data.local.breed.BreedsLocalDataSource
import com.gallapillo.dogs.data.local.DogsDatabase
import com.gallapillo.dogs.data.local.dog.DogDao
import com.gallapillo.dogs.data.local.dog.DogLocalDataSource
import com.gallapillo.dogs.data.net.dog.DogApi
import com.gallapillo.dogs.data.net.dog.DogsRemoteDataSource
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DogsModule {
    const val BASE_URL = "https://dog.ceo/api/"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun providesJson() = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
    }


    @Singleton
    @Provides
    fun provideContentType() = "application/json".toMediaType()

    @Singleton
    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        json: Json,
        contentType: MediaType
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideBreedApi(retrofit: Retrofit): BreedApi = retrofit.create(BreedApi::class.java)

    @Singleton
    @Provides
    fun provideDogsApi(retrofit: Retrofit): DogApi = retrofit.create(DogApi::class.java)

    @Singleton
    @Provides
    fun provideBreedsRemoteDataSource(breedApi: BreedApi) = BreedsRemoteDataSource(breedApi)

    @Singleton
    @Provides
    fun provideDogsRemoteDataSource(dogApi: DogApi) = DogsRemoteDataSource(dogApi)

    @Singleton
    @Provides
    fun provideDogRepository(
        dogsRemoteDataSource: DogsRemoteDataSource,
        dogsLocalDataSource: DogLocalDataSource
    ) = DogsRepository(dogsRemoteDataSource, dogsLocalDataSource)

    @Provides
    fun provideApplicationContext(app: Application): Context = app.applicationContext

    @Provides
    fun provideDogsDatabase(applicationContext: Context) = DogsDatabase(applicationContext)

    @Singleton
    @Provides
    fun provideBreedDao(dogsDatabase: DogsDatabase) = dogsDatabase.breedDao()

    @Singleton
    @Provides
    fun provideDogDao(dogsDatabase: DogsDatabase) = dogsDatabase.dogDao()

    @Singleton
    @Provides
    fun provideBreedDataSource(breedDao: BreedDao) = BreedsLocalDataSource(breedDao)

    @Singleton
    @Provides
    fun provideDogLocalDataSource(dogDao: DogDao) = DogLocalDataSource(dogDao)

    @Singleton
    @Provides
    fun provideBreedsRepository(
        breedsRemoteDataSource: BreedsRemoteDataSource,
        breedsLocalDataSource: BreedsLocalDataSource
    ) = BreedRepository(breedsRemoteDataSource, breedsLocalDataSource)


    @Provides
    fun provideImageLoader(applicationContext: Context) = ImageLoader.Builder(applicationContext)
        .allowHardware(true)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .networkCachePolicy(CachePolicy.ENABLED)
        .build()
}