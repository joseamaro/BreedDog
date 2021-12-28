package com.joseamaro.breeddog.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.joseamaro.breeddog.presentation.viewModel.DogListDetailsViewModel
import com.joseamaro.breeddog.utils.ViewModelFactory
import com.joseamaro.breeddog.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DogListDetailsFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(DogListDetailsViewModel::class)
    abstract fun bindCompaniesViewModel(viewModel: DogListDetailsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}