package com.assignment.coolwink.framework.db

import android.content.Context
import com.db.RepositoryDatabase
import javax.inject.Inject

class LocalDataHelperImpl @Inject constructor(context: Context) : LocalDataHelper {


    val db: RepositoryDatabase by lazy {
        RepositoryDatabase.getDatabase(context)
    }

    override fun insertData(repositoryEntity: RepositoryEntity) {
        repositoryEntity.apply {
            db.repositoryDao().insertRepo(repositoryEntity)
        }

    }

    override fun getAllRepositories(): MutableList<RepositoryEntity> {
        return db.repositoryDao().getAllRepo()
    }

}