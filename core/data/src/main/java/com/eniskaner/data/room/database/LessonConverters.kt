package com.eniskaner.data.room.database

import androidx.room.TypeConverter
import com.eniskaner.data.room.entity.LessonEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LessonConverters {

    @TypeConverter
    fun fromLessonList(value: List<LessonEntity>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toLessonList(value: String): List<LessonEntity> {
        return Gson().fromJson(value, object : TypeToken<List<LessonEntity>>() {}.type)
    }
}