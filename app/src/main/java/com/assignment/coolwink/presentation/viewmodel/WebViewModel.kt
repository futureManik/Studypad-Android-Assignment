package com.assignment.coolwink.presentation.viewmodel

import android.app.Application
import com.assignment.coolwink.domain.IInternetStatus
import com.assignment.coolwink.usecases.Instructors

class WebViewModel constructor(
    application: Application, val intractors: Instructors, val mInternetStatus: IInternetStatus
) : BaseViewModel(application, intractors, mInternetStatus) {

}