package com.assignment.coolwink.presentation

import android.app.Activity
import android.app.Application
import com.assignment.coolwink.domain.di.component.AppComponent
import com.assignment.coolwink.domain.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class AssignmentApp : Application(), HasActivityInjector {
    var appComponent: AppComponent? = null
        private set

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        application = this
        createAppComponent()
    }

    private fun createAppComponent() {
        appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent?.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }

    companion object {
        lateinit var application: AssignmentApp
    }
}
