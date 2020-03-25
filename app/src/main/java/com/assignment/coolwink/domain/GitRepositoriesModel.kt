package com.assignment.coolwink.domain


import com.google.gson.annotations.SerializedName

data class GitRepositoriesModel(
    @SerializedName("items")
    val items: MutableList<Item>?
) {
    data class Item(
        @SerializedName("avatar_url")
        val avatarUrl: String?,
        @SerializedName("html_url")
        val htmlUrl: String?,
        @SerializedName("login")
        val login: String?
    )
}