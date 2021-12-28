package com.joseamaro.breeddog.utils

import android.app.Activity

interface ActivityComponent<T : Activity> {
    /**
     * Indica que T requiere inyección desde este componente
     * @param target T
     */
    fun inject(target: T)
}