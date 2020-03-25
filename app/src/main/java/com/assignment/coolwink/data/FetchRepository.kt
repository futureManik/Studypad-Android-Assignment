package com.assignment.coolwink.data

import com.apis.ApiServices
import com.assignment.coolwink.domain.GitRepositoriesModel
import com.assignment.coolwink.domain.IInternetStatus
import com.assignment.coolwink.framework.db.LocalDataHelperImpl
import com.assignment.coolwink.framework.db.RepositoryEntity
import com.assignment.coolwink.presentation.Exceptions.NoDataException
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Responsible to get data from remote server or database.
 */
class FetchRepository @Inject
constructor(private val mInternetStatus: IInternetStatus) {

    @Inject
    lateinit var mDataSource: LocalDataHelperImpl

    @Inject
    lateinit var mApiService: ApiServices


    /**
     * Get Single observable from the method which first check
     * net connection if it is then get data from remote server otherwise
     * from local database
     */
    fun getRepository(): Single<GitRepositoriesModel> {
        //From local store
        if (!mInternetStatus.isConnected) {
            return getLocallySavedDataObservable().map {
                GitRepositoriesModel(DataMapper().mapEntityToModel(it))
            }.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
        }
        return mApiService.getRepositories()
            .flatMap { repos -> Single.just(repos) }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * Get Single observable from the method which loads data from database
     *
     * @return Single Observable to return List of repository object
     */
    private fun getLocallySavedDataObservable(): Single<MutableList<RepositoryEntity>> {
        return Single.fromCallable {
            mDataSource.getAllRepositories()
        }
    }

    /**
     * Get Single observable from the method which loads data from database async on condition of search query
     *
     * @return Single Observable to return List of repository object
     */
    fun storeDataLocallyObservable(repositories: List<RepositoryEntity>): Observable<Boolean> {
        return if (repositories.isEmpty()) {
            return Observable.error(NoDataException("No Data found"))
        } else Observable.fromIterable(repositories)
            .flatMap { repository ->
                Observable.just(repository)
            }.map { repository ->
                //> Store path in local database
                mDataSource.insertData(repository)
                true
            }.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}