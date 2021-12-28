package com.joseamaro.breeddog.data.repository

import com.joseamaro.breeddog.domain.model.ImageDog
import com.joseamaro.breeddog.domain.model.Race
import io.reactivex.Observable

interface DogListRepository {
    fun getRace() : Observable<Race>
    fun getImageDog(race_name : String) : Observable<ImageDog>
}