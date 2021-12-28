package com.joseamaro.breeddog.di.component

import com.joseamaro.breeddog.di.module.DogListFragmentModule
import com.joseamaro.breeddog.di.module.DogListRepositoryModule
import com.joseamaro.breeddog.presentation.fragment.DogListFragment
import com.joseamaro.breeddog.utils.FragmentComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DogListFragmentModule::class, DogListRepositoryModule::class])
interface DogListFragmentComponent : FragmentComponent<DogListFragment> {
}