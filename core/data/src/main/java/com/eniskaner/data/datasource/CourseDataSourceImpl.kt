package com.eniskaner.data.datasource

import android.content.res.AssetManager
import com.eniskaner.common.util.Constants.AssetConstants.ALL_COURSE_LIST
import com.eniskaner.common.util.Constants.AssetConstants.CATEGORY_AI
import com.eniskaner.common.util.Constants.AssetConstants.CATEGORY_DESIGN
import com.eniskaner.common.util.Constants.AssetConstants.CATEGORY_TECHNOLOGY
import com.eniskaner.common.util.Constants.AssetConstants.COURSE_CATEGORIES
import com.eniskaner.common.util.Constants.AssetConstants.COURSE_DETAIL
import com.eniskaner.data.model.CategoryDto
import com.eniskaner.data.model.CourseDetailDto
import com.eniskaner.data.model.CourseDto
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream
import javax.inject.Inject

@OptIn(ExperimentalSerializationApi::class)
class CourseDataSourceImpl @Inject constructor(
    private val assetManager: AssetManager,
    private val json: Json
) : CourseDataSource {

    override suspend fun getCourseList(): List<CourseDto> =
        assetManager.open(ALL_COURSE_LIST).use(json::decodeFromStream)

    override suspend fun getCourseDetail(courseId: Int): CourseDetailDto? =
        assetManager.open(COURSE_DETAIL)
            .use<InputStream, List<CourseDetailDto>>(json::decodeFromStream)
            .find { it.id == courseId }

    override suspend fun getCourseListByCategory(categoryId: Int): List<CourseDto> =
        when (categoryId) {
            1 -> assetManager.open(CATEGORY_TECHNOLOGY).use(json::decodeFromStream)
            2 -> assetManager.open(CATEGORY_AI).use(json::decodeFromStream)
            3 -> assetManager.open(CATEGORY_DESIGN).use(json::decodeFromStream)
            else -> emptyList()
        }

    override suspend fun getCourseListBySearchQuery(query: String): List<CourseDto> =
        assetManager.open(ALL_COURSE_LIST)
            .use<InputStream, List<CourseDto>>(json::decodeFromStream)
            .filter { it.title.lowercase().contains(query.lowercase()) }

    override suspend fun getCourseCategories(): List<CategoryDto> =
        assetManager.open(COURSE_CATEGORIES).use(json::decodeFromStream)

}
