package com.joseamaro.breeddog.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.joseamaro.breeddog.presentation.viewModel.DogListViewModel
import com.joseamaro.breeddog.utils.ViewModelFactory
import com.joseamaro.breeddog.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DogListFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(DogListViewModel::class)
    abstract fun bindCompaniesViewModel(viewModel: DogListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}