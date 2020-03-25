package com.assignment.coolwink.usecases

import com.assignment.coolwink.data.FetchRepository

/**
 * Get repositories list from remote server or database
 */
class GetRepositories(private val fetchRepository: FetchRepository) {
    operator fun invoke() = fetchRepository.getRepository().toObservable()
}