package com.joseamaro.breeddog.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joseamaro.breeddog.domain.model.ImageDog
import com.joseamaro.breeddog.domain.usecase.GetImageDogListUseCase
import com.joseamaro.breeddog.utils.*
import javax.inject.Inject

class DogListDetailsViewModel @Inject constructor(
    private val getImageDogListUseCase: GetImageDogListUseCase
) : ViewModel() {

    var liveData: MutableLiveData<Resource<List<String>>> = MutableLiveData()
    private lateinit var auxListImageDog: ArrayList<String>
    private var countStar: Int = 0

    fun getImageRace(race_name: String) {
        liveData.post(status = ResourceState.LOADING)
        getImageDogListUseCase.setData(race_name).execute(object : UseCaseObserver<ImageDog>() {
            override fun onNext(value: ImageDog) {
                auxListImageDog = ArrayList()
                auxListImageDog.addAll(value.message)
                loadImageList()
            }

            override fun onError(e: Throwable) {
                liveData.post(status = ResourceState.ERROR, failure = Failure.Error(
                        "Ha ocurrido un error inesperado"))
            }
        })
    }

    fun loadImageList() {
        if (countStar < auxListImageDog.size) {
            var countEnd  = countStar + 10

            if (countEnd > auxListImageDog.size) {
                countEnd = countStar + (auxListImageDog.size - countStar)
            }
            liveData.post(status = ResourceState.SUCCESS, data = auxListImageDog.subList(countStar, countEnd))
            countStar = countEnd
        }
    }

    override fun onCleared() {
        getImageDogListUseCase.dispose()
    }
}
