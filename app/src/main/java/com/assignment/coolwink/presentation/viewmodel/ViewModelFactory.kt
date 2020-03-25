package com.assignment.coolwink.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.assignment.coolwink.domain.IInternetStatus
import com.assignment.coolwink.usecases.Instructors
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    val application: Application, val intractors: Instructors,
    private val internetStatus: IInternetStatus
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (BaseViewModel::class.java.isAssignableFrom(modelClass)) {
            return modelClass.getConstructor(
                Application::class.java,
                Instructors::class.java, IInternetStatus::class.java
            )
                .newInstance(application, intractors, internetStatus)
        } else {
            throw IllegalArgumentException("unexpected viewModel class $modelClass")
        }
    }
}