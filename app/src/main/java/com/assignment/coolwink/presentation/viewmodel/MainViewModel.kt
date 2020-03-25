package com.assignment.coolwink.presentation.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.assignment.coolwink.data.DataMapper
import com.assignment.coolwink.domain.GitRepositoriesModel
import com.assignment.coolwink.domain.IInternetStatus
import com.assignment.coolwink.usecases.Instructors
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class MainViewModel constructor(
    application: Application, val intractors: Instructors, val mInternetStatus: IInternetStatus
) : BaseViewModel(application, intractors, mInternetStatus) {
    val isLoading = ObservableField<Boolean>()
    val isError = ObservableField<Boolean>()
    val isFetchData = ObservableField<Boolean>()
    private val mCompositeDisposable by lazy {
        CompositeDisposable()
    }

    val repoResponseData = MutableLiveData<MutableList<GitRepositoriesModel.Item>>()

    fun getRepositories() {
        intractors.repositories().subscribe(object : Observer<GitRepositoriesModel> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
                mCompositeDisposable.add(d)
            }

            override fun onNext(repositoriesModel: GitRepositoriesModel) {
                isFetchData.set(true)
                isError.set(false)
                isLoading.set(false)
                repositoriesModel.items?.let {
                    repoResponseData.postValue(it)
                }

                if (mInternetStatus.isConnected) {
                    repositoriesModel.items?.let {
                        intractors.storeDataLocally(DataMapper().mapModelToEntity(it))
                            .subscribe()
                    }

                }
            }

            override fun onError(e: Throwable) {
                isFetchData.set(false)
                isError.set(true)
            }

        })
    }

    override fun onCleared() {
        mCompositeDisposable.dispose()
        super.onCleared()
    }

    fun callRetry() {
        isError.set(false)
        getRepositories()
    }


    fun onRefresh() {
        isLoading.set(true)
        getRepositories()
    }

}