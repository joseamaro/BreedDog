package com.joseamaro.breeddog.utils.extension

import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

fun Fragment.requireNavController() = Navigation.findNavController(requireView())