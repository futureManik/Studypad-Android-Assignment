package com.apis

import com.assignment.coolwink.domain.GitRepositoriesModel
import io.reactivex.Single
import retrofit2.http.GET

interface ApiServices {
    @GET("/search/users?q=alphabetagama")
    fun getRepositories(): Single<GitRepositoriesModel>
}
