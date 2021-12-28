package com.joseamaro.breeddog.domain.usecase

import com.joseamaro.breeddog.data.repository.DogListRepository
import com.joseamaro.breeddog.domain.model.ImageDog
import com.joseamaro.breeddog.utils.UseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetImageDogListUseCase @Inject constructor(
    private val repository: DogListRepository
) : UseCase<ImageDog>() {

    private lateinit var race_name: String

      fun setData(race_name: String): GetImageDogListUseCase {
        this.race_name = race_name
          return this
    }

    override fun createObservableUseCase(): Observable<ImageDog> {
        return repository.getImageDog(race_name)
    }
}