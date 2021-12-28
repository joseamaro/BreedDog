package com.joseamaro.breeddog.di.module

import com.joseamaro.breeddog.presentation.fragment.DogListFragment
import dagger.Module
import dagger.Provides

@Module
class DogListActivityModule {
    @Provides
    fun provideFragment(): DogListFragment {
        return DogListFragment.newInstance()
    }
}