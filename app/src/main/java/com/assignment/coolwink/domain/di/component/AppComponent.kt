package com.assignment.coolwink.domain.di.component

import com.assignment.coolwink.domain.di.builder.ActivityBuilder
import com.assignment.coolwink.domain.di.module.ApiModule
import com.assignment.coolwink.domain.di.module.AppModule
import com.assignment.coolwink.presentation.AssignmentApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ApiModule::class, ActivityBuilder::class])
interface AppComponent {

    fun inject(application: AssignmentApp)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: AssignmentApp): Builder
    }
}
