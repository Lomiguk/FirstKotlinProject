package com.lomiguk.usersflowers.data.dto

import com.lomiguk.usersflowers.data.entity.FlowerType

class FlowerTypeDTO(val name: String){
    constructor(flower: FlowerType): this(flower.name)
}