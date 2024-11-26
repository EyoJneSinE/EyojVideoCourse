package com.eniskaner.data.model

import com.eniskaner.common.util.Constants.DatabaseObjectConstants.DTO_CATEGORY_ID
import com.eniskaner.common.util.Constants.DatabaseObjectConstants.DTO_CATEGORY_NAME
import com.eniskaner.common.util.Constants.DatabaseObjectConstants.DTO_THUMBNAIL_URL
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseDetailDto(
    val id: Int,
    val title: String,
    @SerialName(DTO_CATEGORY_ID) val categoryId: Int,
    @SerialName(DTO_CATEGORY_NAME)val categoryName: String,
    val description: String,
    val price: String,
    @SerialName(DTO_THUMBNAIL_URL) val thumbnailUrl: String,
    val lessons: List<LessonDto>
)
