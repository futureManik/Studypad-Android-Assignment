package com.assignment.coolwink.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.assignment.coolwink.domain.IInternetStatus
import com.assignment.coolwink.presentation.AssignmentApp
import com.assignment.coolwink.usecases.Instructors

open class BaseViewModel protected constructor(
    application: Application,
    intractors: Instructors, mInternetStatus: IInternetStatus
) : AndroidViewModel(application) {
    protected val application: AssignmentApp = getApplication()
}