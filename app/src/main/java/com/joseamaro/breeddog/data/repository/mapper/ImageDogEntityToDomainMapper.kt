package com.joseamaro.breeddog.data.repository.mapper

import com.joseamaro.breeddog.data.entity.ImageDogEntity
import com.joseamaro.breeddog.domain.model.ImageDog
import com.joseamaro.breeddog.utils.Mapper
import javax.inject.Inject

class ImageDogEntityToDomainMapper @Inject constructor() : Mapper<ImageDogEntity, ImageDog>() {

    override fun map(value: ImageDogEntity?): ImageDog? {
        value?.let {
            return ImageDog(it.message, it.status)
        }
        return null
    }

    override fun reverseMap(value: ImageDog?): ImageDogEntity? {
        value?.let {
            return ImageDogEntity(it.message, it.status)
        }
        return null
    }
}