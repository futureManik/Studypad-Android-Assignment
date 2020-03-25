package com.assignment.coolwink.domain.di.module

import android.content.Context
import com.apis.ApiServices
import com.assignment.coolwink.data.FetchRepository
import com.assignment.coolwink.domain.di.scope.ActivityScope
import com.assignment.coolwink.framework.db.LocalDataHelper
import com.assignment.coolwink.framework.db.LocalDataHelperImpl
import com.assignment.coolwink.usecases.GetRepositories
import com.assignment.coolwink.usecases.Instructors
import com.assignment.coolwink.usecases.StoreDataLocally
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ViewModelProviderModule {


    @Singleton
    @Provides
    fun provideLocalDataHelper(context: Context): LocalDataHelper {
        return LocalDataHelperImpl(context)
    }

    @ActivityScope
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }

    @ActivityScope
    @Provides
    fun provideIntractor(searchRepo: FetchRepository): Instructors {
        return Instructors(GetRepositories(searchRepo), StoreDataLocally(searchRepo))
    }
}

