package com.joseamaro.breeddog.data.remote

import com.joseamaro.breeddog.data.entity.ImageDogEntity
import com.joseamaro.breeddog.data.entity.RaceEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface RaceApi {
    @GET("breeds/list")
    fun getRace(): Observable<RaceEntity>
    @GET("breed/{race_name}/images")
    fun getImageDog(@Path("race_name") race_name : String): Observable<ImageDogEntity>
}