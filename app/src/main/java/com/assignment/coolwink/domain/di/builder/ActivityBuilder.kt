package com.assignment.coolwink.domain.di.builder

import com.assignment.coolwink.domain.di.module.ViewModelProviderModule
import com.assignment.coolwink.domain.di.scope.ActivityScope
import com.assignment.coolwink.presentation.MainActivity
import com.assignment.coolwink.presentation.WebViewActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ActivityScope
    @ContributesAndroidInjector(modules = [ViewModelProviderModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ViewModelProviderModule::class])
    abstract fun bindWebViewActivity(): WebViewActivity
}
