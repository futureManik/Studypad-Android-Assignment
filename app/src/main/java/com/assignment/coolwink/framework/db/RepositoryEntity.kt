package com.assignment.coolwink.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "projects")
data class RepositoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "login")
    @SerializedName("login")
    val login: String?,
    @ColumnInfo(name = "avatar_url")
    @SerializedName("avatar_url")
    val avatar: String?,
    @ColumnInfo(name = "html_url")
    @SerializedName("html_url")
    val htmlUrl: String?
)