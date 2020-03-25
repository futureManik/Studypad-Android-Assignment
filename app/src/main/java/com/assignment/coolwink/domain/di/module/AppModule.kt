package com.assignment.coolwink.domain.di.module

import android.content.Context
import com.assignment.coolwink.presentation.AssignmentApp
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    val gson: Gson
        @Singleton
        @Provides
        get() = GsonBuilder().create()

    @Singleton
    @Provides
    fun appContext(): Context {
        return AssignmentApp.application.applicationContext
    }

}
