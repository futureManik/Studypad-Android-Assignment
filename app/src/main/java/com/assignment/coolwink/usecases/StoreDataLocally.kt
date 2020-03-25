package com.assignment.coolwink.usecases

import com.assignment.coolwink.data.FetchRepository
import com.assignment.coolwink.framework.db.RepositoryEntity

/**
 * Store data locally
 */
class StoreDataLocally(private val photoSearchRepository: FetchRepository) {
    operator fun invoke(repositoryEntities: List<RepositoryEntity>) =
        photoSearchRepository.storeDataLocallyObservable(
            repositories = repositoryEntities
        )
}