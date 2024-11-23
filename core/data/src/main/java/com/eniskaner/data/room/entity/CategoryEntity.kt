package com.eniskaner.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "category")
data class CategoryEntity(
    val id: Int,
    val name: String,
    @ColumnInfo("icon_url") val iconUrl: String,
    val color: String
)
