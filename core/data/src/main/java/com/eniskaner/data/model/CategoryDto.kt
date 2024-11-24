package com.eniskaner.data.model

import com.eniskaner.common.util.Constants.DatabaseObjectConstants.CATEGORY_ICON_URL
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    val id: Int,
    val name: String,
    @SerialName(CATEGORY_ICON_URL) val iconUrl: String,
    val color: String
)
