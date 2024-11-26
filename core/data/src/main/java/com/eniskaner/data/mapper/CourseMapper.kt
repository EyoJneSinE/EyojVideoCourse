package com.eniskaner.data.mapper

import com.eniskaner.data.model.CategoryDto
import com.eniskaner.data.model.CourseDetailDto
import com.eniskaner.data.model.CourseDto
import com.eniskaner.data.model.LessonDto
import com.eniskaner.data.room.entity.CategoryEntity
import com.eniskaner.data.room.entity.CourseDetailEntity
import com.eniskaner.data.room.entity.CourseEntity
import com.eniskaner.data.room.entity.LessonEntity
import com.eniskaner.domain.model.CategoryUIModel
import com.eniskaner.domain.model.CourseDetailUIModel
import com.eniskaner.domain.model.CourseUIModel
import com.eniskaner.domain.model.LessonUIModel
import javax.inject.Inject

/**
 * CategoryDTO to CategoryUIModel Mapper
 */
class CategoryDtoToUIModelMapper @Inject constructor() : Mapper<CategoryDto, CategoryUIModel> {
    override fun map(input: CategoryDto): CategoryUIModel {
        return CategoryUIModel(
            id = input.id,
            name = input.name,
            iconUrl = input.iconUrl,
            color = input.color
        )
    }
}

/**
 * CourseDTO to CourseUIModel Mapper
 */
class CourseDtoToUIModelMapper @Inject constructor() : Mapper<CourseDto, CourseUIModel> {
    override fun map(input: CourseDto): CourseUIModel {
        return CourseUIModel(
            id = input.id,
            title = input.title,
            categoryId = input.categoryId,
            categoryName = input.categoryName,
            description = input.description,
            price = input.price,
            thumbnailUrl = input.thumbnailUrl
        )
    }
}

/**
 * LessonDTO to LessonUIModel Mapper
 */
class LessonDtoToUIModelMapper @Inject constructor() : Mapper<LessonDto, LessonUIModel> {
    override fun map(input: LessonDto): LessonUIModel {
        return LessonUIModel(
            lessonId = input.lessonId,
            title = input.title,
            duration = input.duration,
            videoUrl = input.videoUrl,
            videoImage = input.videoImage
        )
    }
}

/**
 * CourseDetailDTO to CourseDetailUIModel Mapper
 */
class CourseDetailDtoToUIModelMapper @Inject constructor(
    private val lessonDtoToUIModelMapper: LessonDtoToUIModelMapper
) : Mapper<CourseDetailDto, CourseDetailUIModel> {
    override fun map(input: CourseDetailDto): CourseDetailUIModel {
        return CourseDetailUIModel(
            id = input.id,
            title = input.title,
            categoryId = input.categoryId,
            categoryName = input.categoryName,
            description = input.description,
            price = input.price,
            thumbnailUrl = input.thumbnailUrl,
            lessons = input.lessons.map { lessonDtoToUIModelMapper.map(it) }
        )
    }
}

/**
 * CategoryDTO to CategoryEntity Mapper
 */
class CategoryDtoToEntityMapper @Inject constructor() : Mapper<CategoryDto, CategoryEntity> {
    override fun map(input: CategoryDto): CategoryEntity {
        return CategoryEntity(
            id = input.id,
            name = input.name,
            iconUrl = input.iconUrl,
            color = input.color
        )
    }
}

/**
 * CourseDTO to CourseEntity Mapper
 */
class CourseDtoToEntityMapper @Inject constructor() : Mapper<CourseDto, CourseEntity> {
    override fun map(input: CourseDto): CourseEntity {
        return CourseEntity(
            id = input.id,
            title = input.title,
            categoryId = input.categoryId,
            categoryName = input.categoryName,
            description = input.description,
            price = input.price,
            thumbnailUrl = input.thumbnailUrl
        )
    }
}

/**
 * LessonDTO to LessonEntity Mapper
 */
class LessonDtoToEntityMapper @Inject constructor() : Mapper<LessonDto, LessonEntity> {
    override fun map(input: LessonDto): LessonEntity {
        return LessonEntity(
            lessonId = input.lessonId,
            title = input.title,
            duration = input.duration,
            videoUrl = input.videoUrl,
            videoImage = input.videoImage
        )
    }
}

/**
 * CourseDetailDTO to CourseDetailEntity Mapper
 */
class CourseDetailDtoToEntityMapper @Inject constructor(
    private val lessonDtoToEntityMapper: LessonDtoToEntityMapper
) : Mapper<CourseDetailDto, CourseDetailEntity> {
    override fun map(input: CourseDetailDto): CourseDetailEntity {
        return CourseDetailEntity(
            id = input.id,
            title = input.title,
            categoryId = input.categoryId,
            categoryName = input.categoryName,
            description = input.description,
            price = input.price,
            thumbnailUrl = input.thumbnailUrl,
            lessons = input.lessons.map { lessonDtoToEntityMapper.map(it) }
        )
    }
}

/**
 * CategoryEntity to CategoryUIModel Mapper
 */
class CategoryEntityToUIModelMapper @Inject constructor() : Mapper<CategoryEntity, CategoryUIModel> {
    override fun map(input: CategoryEntity): CategoryUIModel {
        return CategoryUIModel(
            id = input.id,
            name = input.name,
            iconUrl = input.iconUrl,
            color = input.color
        )
    }
}

/**
 * CourseEntity to CourseUIModel Mapper
 */
class CourseEntityToUIModelMapper @Inject constructor() : Mapper<CourseEntity, CourseUIModel> {
    override fun map(input: CourseEntity): CourseUIModel {
        return CourseUIModel(
            id = input.id,
            title = input.title,
            categoryId = input.categoryId,
            categoryName = input.categoryName,
            description = input.description,
            price = input.price,
            thumbnailUrl = input.thumbnailUrl
        )
    }
}

/**
 * LessonEntity to LessonUIModel Mapper
 */
class LessonEntityToUIModelMapper @Inject constructor() : Mapper<LessonEntity, LessonUIModel> {
    override fun map(input: LessonEntity): LessonUIModel {
        return LessonUIModel(
            lessonId = input.lessonId,
            title = input.title,
            duration = input.duration,
            videoUrl = input.videoUrl,
            videoImage = input.videoImage
        )
    }
}

/**
 * CourseDetailEntity to CourseDetailUIModel Mapper
 */
class CourseDetailEntityToUIModelMapper @Inject constructor(
    private val lessonEntityToUIMapper: LessonEntityToUIModelMapper
) : Mapper<CourseDetailEntity, CourseDetailUIModel> {
    override fun map(input: CourseDetailEntity): CourseDetailUIModel {
        return CourseDetailUIModel(
            id = input.id,
            title = input.title,
            categoryId = input.categoryId,
            categoryName = input.categoryName,
            description = input.description,
            price = input.price,
            thumbnailUrl = input.thumbnailUrl,
            lessons = input.lessons.map { lessonEntityToUIMapper.map(it) }
        )
    }
}
