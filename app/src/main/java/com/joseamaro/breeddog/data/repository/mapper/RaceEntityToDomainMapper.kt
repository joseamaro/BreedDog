package com.joseamaro.breeddog.data.repository.mapper

import com.joseamaro.breeddog.data.entity.RaceEntity
import com.joseamaro.breeddog.domain.model.Race
import com.joseamaro.breeddog.utils.Mapper
import javax.inject.Inject

class RaceEntityToDomainMapper @Inject constructor(): Mapper<RaceEntity, Race>() {

    override fun map(value: RaceEntity?): Race? {
        value?.let {
            return Race(it.status, it.message)
        }
        return null
    }

    override fun reverseMap(value: Race?): RaceEntity? {
        value?.let {
            return RaceEntity(it.status, it.message)
        }
        return null
    }

}