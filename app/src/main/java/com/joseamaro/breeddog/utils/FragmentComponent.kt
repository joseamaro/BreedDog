package com.joseamaro.breeddog.utils

import androidx.fragment.app.Fragment

interface FragmentComponent<T : Fragment> {
    /**
     * Indica que T requiere inyección desde este componente
     * @param target T
     */
    fun inject(target: T)
}