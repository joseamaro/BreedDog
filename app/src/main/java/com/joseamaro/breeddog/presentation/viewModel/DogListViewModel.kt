package com.joseamaro.breeddog.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joseamaro.breeddog.domain.model.Race
import com.joseamaro.breeddog.domain.usecase.GetDogListUseCase
import com.joseamaro.breeddog.utils.*
import javax.inject.Inject

class DogListViewModel @Inject constructor(
    private val getDogListUseCase : GetDogListUseCase
): ViewModel(){

    var liveData: MutableLiveData<Resource<Race>> = MutableLiveData()

    fun getRaceDog(){
        liveData.post(status = ResourceState.LOADING)
        getDogListUseCase.execute(object : UseCaseObserver<Race>() {
            override fun onNext(value: Race) {
                liveData.post(status = ResourceState.SUCCESS, data = value)
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                liveData.post(status = ResourceState.ERROR, failure = Failure.Error(
                    "ha ocurrido un error inesperado intente nuevamente"))
            }
        })
    }

    override fun onCleared() {
        getDogListUseCase.dispose()
    }
}