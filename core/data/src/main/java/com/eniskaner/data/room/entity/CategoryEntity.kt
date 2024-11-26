package com.eniskaner.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eniskaner.common.util.Constants.DatabaseObjectConstants.CATEGORY_TABLE_NAME

@Entity(tableName = CATEGORY_TABLE_NAME)
data class CategoryEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val iconUrl: String,
    val color: String
)
