package com.joseamaro.breeddog.data.repository

import com.joseamaro.breeddog.data.remote.RaceApi
import com.joseamaro.breeddog.data.repository.mapper.ImageDogEntityToDomainMapper
import com.joseamaro.breeddog.data.repository.mapper.RaceEntityToDomainMapper
import com.joseamaro.breeddog.domain.model.ImageDog
import com.joseamaro.breeddog.domain.model.Race
import io.reactivex.Observable

class DogListRepositoryImp(
    private val api: RaceApi,
    private val mapper: RaceEntityToDomainMapper,
    private val mapperImageDog: ImageDogEntityToDomainMapper
) : DogListRepository {

    override fun getRace(): Observable<Race> {
        return api.getRace().map { response ->
            if (!response.status.equals("success")) {
                throw Exception("Error")
            }
            mapper.map(response)
        }
    }

    override fun getImageDog(race_name: String): Observable<ImageDog> {
        return api.getImageDog(race_name).map { response ->
            if (!response.status.equals("success")) {
                throw Exception("Error")
            }
            mapperImageDog.map(response)
        }
    }
}


