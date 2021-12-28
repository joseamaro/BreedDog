package com.joseamaro.breeddog.di.component

import com.joseamaro.breeddog.di.module.DogListDetailsFragmentModule
import com.joseamaro.breeddog.di.module.DogListRepositoryModule
import com.joseamaro.breeddog.presentation.fragment.DogListDetailsFragment
import com.joseamaro.breeddog.utils.FragmentComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DogListDetailsFragmentModule::class, DogListRepositoryModule::class])
interface DogListDetailsFragmentComponent : FragmentComponent<DogListDetailsFragment>