package com.joseamaro.breeddog.di.component

import com.joseamaro.breeddog.utils.ActivityComponent
import com.joseamaro.breeddog.di.module.DogListActivityModule
import com.joseamaro.breeddog.presentation.activity.DogListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DogListActivityModule::class])
interface DogListActivityComponent: ActivityComponent<DogListActivity>