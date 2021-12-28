package com.joseamaro.breeddog.utils

interface IProgressView {
    fun showProgress(show: Boolean)
    fun showMessage(message: String)
}