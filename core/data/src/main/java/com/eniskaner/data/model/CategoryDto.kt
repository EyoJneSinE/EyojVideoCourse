package com.eniskaner.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    val id: Int,
    val name: String,
    @SerialName("icon_url") val iconUrl: String,
    val color: String
)
