package com.assignment.coolwink.data

import com.assignment.coolwink.domain.GitRepositoriesModel
import com.assignment.coolwink.framework.db.RepositoryEntity

/**
 * To map entity to repositoriesModel
 * @entity RepositoryEntity
 */
class DataMapper : IMapper<RepositoryEntity, GitRepositoriesModel.Item> {
    override fun mapToEntity(model: GitRepositoriesModel.Item): RepositoryEntity {
        return RepositoryEntity(
            login = model.login ?: "",
            avatar = model.avatarUrl,
            htmlUrl = model.htmlUrl
        )
    }

    override fun mapToModel(entity: RepositoryEntity): GitRepositoriesModel.Item {
        return GitRepositoriesModel.Item(
            login = entity.login,
            avatarUrl = entity.avatar,
            htmlUrl = entity.htmlUrl
        )
    }

}