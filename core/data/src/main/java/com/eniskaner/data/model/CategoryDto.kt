package com.eniskaner.data.model

import kotlinx.serialization.SerialName

data class CategoryDto(
    val id: Int,
    val name: String,
    @SerialName("icon_url") val iconUrl: String,
    val color: String
)
