package com.eniskaner.data.repository

import com.eniskaner.common.util.Constants.ErrorMessages.DEFAULT_ERROR_MESSAGE
import com.eniskaner.common.util.Resource
import com.eniskaner.data.datasource.CourseDataSource
import com.eniskaner.data.datasource.CourseLocalDataSource
import com.eniskaner.data.mapper.CategoryDtoToEntityMapper
import com.eniskaner.data.mapper.CategoryDtoToUIModelMapper
import com.eniskaner.data.mapper.CategoryEntityToUIModelMapper
import com.eniskaner.data.mapper.CourseDetailDtoToEntityMapper
import com.eniskaner.data.mapper.CourseDetailDtoToUIModelMapper
import com.eniskaner.data.mapper.CourseDetailEntityToUIModelMapper
import com.eniskaner.data.mapper.CourseDtoToEntityMapper
import com.eniskaner.data.mapper.CourseDtoToUIModelMapper
import com.eniskaner.data.mapper.CourseEntityToUIModelMapper
import com.eniskaner.data.mapper.LessonDtoToEntityMapper
import com.eniskaner.data.mapper.LessonDtoToUIModelMapper
import com.eniskaner.data.mapper.LessonEntityToUIModelMapper
import com.eniskaner.domain.model.CategoryUIModel
import com.eniskaner.domain.model.CourseDetailUIModel
import com.eniskaner.domain.model.CourseUIModel
import com.eniskaner.domain.model.LessonUIModel
import com.eniskaner.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val courseDataSource: CourseDataSource,
    private val courseLocalDataSource: CourseLocalDataSource,
    private val categoryDtoToUIModelMapper: CategoryDtoToUIModelMapper,
    private val courseDtoToUIModelMapper: CourseDtoToUIModelMapper,
    private val lessonDtoToUIModelMapper: LessonDtoToUIModelMapper,
    private val courseDetailDtoToUIModelMapper: CourseDetailDtoToUIModelMapper,
    private val categoryDtoToEntityMapper: CategoryDtoToEntityMapper,
    private val courseDtoToEntityMapper: CourseDtoToEntityMapper,
    private val lessonDtoToEntityMapper: LessonDtoToEntityMapper,
    private val courseDetailDtoToEntityMapper: CourseDetailDtoToEntityMapper,
    private val courseEntityToUIModelMapper: CourseEntityToUIModelMapper,
    private val categoryEntityToUIModelMapper: CategoryEntityToUIModelMapper,
    private val courseDetailEntityToUIModelMapper: CourseDetailEntityToUIModelMapper,
    private val lessonEntityToUIMapper: LessonEntityToUIModelMapper
) : CourseRepository {

    override suspend fun getCourseList(): Flow<Resource<List<CourseUIModel>>> =
        checkLocalThenRemote(
            localFetcher = { courseLocalDataSource.getCourseList().takeIf { it.isNotEmpty() } },
            remoteFetcher = { courseDataSource.getCourseList() },
            localSaver = { remoteData ->
                courseLocalDataSource.saveCourseList(remoteData.map { courseDtoToEntityMapper.map(it) })
            },
            localMapper = { localData ->
                localData.map { courseEntityToUIModelMapper.map(it) }
            },
            remoteMapper = { remoteData ->
                remoteData.map { courseDtoToUIModelMapper.map(it) }
            }
        )

    override suspend fun getCourseDetail(id: Int): Flow<Resource<CourseDetailUIModel?>> =
        checkLocalThenRemote(
            localFetcher = { courseLocalDataSource.getCourseDetail(id).takeIf { it != null } },
            remoteFetcher = { courseDataSource.getCourseDetail(id) },
            localSaver = { remoteData ->
                remoteData?.let {
                    courseLocalDataSource.saveCourseDetail(courseDetailDtoToEntityMapper.map(it))
                }
            },
            localMapper = { localData ->
                courseDetailEntityToUIModelMapper.map(localData)
            },
            remoteMapper = { remoteData ->
                remoteData?.let { courseDetailDtoToUIModelMapper.map(it) }
            }
        )

    override suspend fun getCourseCategory(categoryId: Int): Flow<Resource<List<CategoryUIModel>>> =
        checkLocalThenRemote(
            localFetcher = {
                courseLocalDataSource.getCourseCategories().takeIf { it.isNotEmpty() }
            },
            remoteFetcher = { courseDataSource.getCourseCategories() },
            localSaver = { remoteData ->
                courseLocalDataSource.saveCourseCategories(remoteData.map {
                    categoryDtoToEntityMapper.map(
                        it
                    )
                })
            },
            localMapper = { localData ->
                localData.map { categoryEntityToUIModelMapper.map(it) }
            },
            remoteMapper = { remoteData ->
                remoteData.map { categoryDtoToUIModelMapper.map(it) }
            }
        )

    override suspend fun getCourseListBySearch(query: String): Flow<Resource<List<CourseUIModel>>> =
        checkLocalThenRemote(
            localFetcher = {
                courseLocalDataSource.getCourseListBySearchQuery(query).takeIf { it.isNotEmpty() }
            },
            remoteFetcher = { courseDataSource.getCourseListBySearchQuery(query) },
            localSaver = { remoteData ->
                courseLocalDataSource.saveCourseList(remoteData.map { courseDtoToEntityMapper.map(it) })
            },
            localMapper = { localData ->
                localData.map { courseEntityToUIModelMapper.map(it) }
            },
            remoteMapper = { remoteData ->
                remoteData.map { courseDtoToUIModelMapper.map(it) }
            }
        )

    override suspend fun getCourseLesson(lessonId: Int): Flow<Resource<List<LessonUIModel>>> =
        checkLocalThenRemote(
            localFetcher = {
                courseLocalDataSource.getCourseLesson(lessonId).takeIf { it.isNotEmpty() }
            },
            remoteFetcher = { courseDataSource.getCourseDetail(lessonId) },
            localSaver = { localData ->
                localData?.let {
                    courseLocalDataSource.saveCourseLesson(localData.lessons.map {
                        lessonDtoToEntityMapper.map(
                            it
                        )
                    })
                }
            },
            localMapper = { localData ->
                localData.map { lessonEntityToUIMapper.map(it) }
            },
            remoteMapper = { remoteData ->
                remoteData?.lessons?.map { lessonDtoToUIModelMapper.map(it) } ?: emptyList()
            }
        )

    private inline fun <LocalType, RemoteType, UiType> checkLocalThenRemote(
        crossinline localFetcher: suspend () -> LocalType?,
        crossinline remoteFetcher: suspend () -> RemoteType,
        crossinline localSaver: suspend (RemoteType) -> Unit,
        crossinline localMapper: (LocalType) -> UiType,
        crossinline remoteMapper: (RemoteType) -> UiType

    ): Flow<Resource<UiType>> = flow {
        emit(Resource.Loading())
        val localData = localFetcher()
        if (localData == null || (localData is List<*> && localData.isEmpty())) {
            val remoteData = remoteFetcher()
            localSaver(remoteData)
            emit(Resource.Success(data = remoteMapper(remoteData)))
        } else {
            emit(Resource.Success(data = localMapper(localData)))
        }
    }.catch {
        emit(Resource.Error(message = it.localizedMessage ?: DEFAULT_ERROR_MESSAGE))
    }
}
