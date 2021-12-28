package com.joseamaro.breeddog.di.module

import com.joseamaro.breeddog.data.remote.RaceApi
import com.joseamaro.breeddog.data.repository.DogListRepository
import com.joseamaro.breeddog.data.repository.DogListRepositoryImp
import com.joseamaro.breeddog.data.repository.mapper.ImageDogEntityToDomainMapper
import com.joseamaro.breeddog.data.repository.mapper.RaceEntityToDomainMapper
import com.joseamaro.breeddog.utils.ApiServiceFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class DogListRepositoryModule {

    @Provides
    fun provideRepository(
        api: RaceApi, mapper: RaceEntityToDomainMapper,
        mapperImageDog: ImageDogEntityToDomainMapper
    ): DogListRepository {
        return DogListRepositoryImp(api, mapper, mapperImageDog)
    }

    @Provides
    fun provideApiService(): RaceApi {
        val okHttpClient = OkHttpClient()
        return ApiServiceFactory.build(
            okHttpClient,
            RaceApi::class.java,
            "https://dog.ceo/api/"
        )
    }

}