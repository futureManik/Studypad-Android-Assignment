package com.assignment.coolwink.framework.db


interface LocalDataHelper {
    fun insertData(projectsEntity: RepositoryEntity)
    fun getAllRepositories(): List<RepositoryEntity>
}