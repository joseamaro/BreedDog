package com.joseamaro.breeddog.domain.usecase

import com.joseamaro.breeddog.data.repository.DogListRepository
import com.joseamaro.breeddog.domain.model.Race
import com.joseamaro.breeddog.utils.UseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetDogListUseCase @Inject constructor(
   private val dogListRepository: DogListRepository
) : UseCase<Race>() {

    override fun createObservableUseCase(): Observable<Race> {
        return dogListRepository.getRace()
    }
}