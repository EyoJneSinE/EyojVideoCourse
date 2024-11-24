package com.eniskaner.data.datasource

import com.eniskaner.data.room.database.CourseDatabase
import com.eniskaner.data.room.entity.CategoryEntity
import com.eniskaner.data.room.entity.CourseDetailEntity
import com.eniskaner.data.room.entity.CourseEntity
import com.eniskaner.data.room.entity.LessonEntity
import javax.inject.Inject

class CourseLocalDataSourceImpl @Inject constructor(
    private val courseDatabase: CourseDatabase
) : CourseLocalDataSource {

    override suspend fun getCourseList(): List<CourseEntity> =
        courseDatabase.getCourseDao().getCourseList()

    override suspend fun saveCourseList(courseList: List<CourseEntity>) =
        courseDatabase.getCourseDao().saveCourseList(courseList = courseList)

    override suspend fun getCourseDetail(courseId: Int): CourseDetailEntity? =
        courseDatabase.getCourseDao().getCourseDetail(courseId = courseId)

    override suspend fun saveCourseDetail(courseDetail: CourseDetailEntity) =
        courseDatabase.getCourseDao().saveCourseDetail(courseDetail = courseDetail)

    override suspend fun getCourseListByCategory(categoryId: Int): List<CourseEntity> =
        courseDatabase.getCourseDao().getCourseListByCategory(categoryId = categoryId)

    override suspend fun getCourseListBySearchQuery(query: String): List<CourseEntity> =
        courseDatabase.getCourseDao().getCourseListBySearchQuery(query = query)

    override suspend fun getCourseCategories(): List<CategoryEntity> =
        courseDatabase.getCourseDao().getCourseCategories()

    override suspend fun saveCourseCategories(courseCategories: List<CategoryEntity>) =
        courseDatabase.getCourseDao().saveCourseCategories(courseCategories = courseCategories)

    override suspend fun getCourseLesson(lessonId: Int): List<LessonEntity> =
        courseDatabase.getCourseDao().getCourseLesson(lessonId = lessonId)

    override suspend fun saveCourseLesson(courseLesson: List<LessonEntity>) =
        courseDatabase.getCourseDao().saveLesson(lesson = courseLesson)

}
