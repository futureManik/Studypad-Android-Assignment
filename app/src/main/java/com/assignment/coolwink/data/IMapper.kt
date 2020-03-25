package com.assignment.coolwink.data

interface IMapper<Entity, Model> {

    fun mapToModel(entity: Entity): Model
    fun mapToEntity(model: Model): Entity


    fun mapEntityToModel(entities: MutableList<Entity>): MutableList<Model> {
        val list = mutableListOf<Model>()
        entities.mapTo(list) { mapToModel(it) }
        return list
    }

    fun mapModelToEntity(model: MutableList<Model>): MutableList<Entity> {
        val list = mutableListOf<Entity>()
        model.mapTo(list) { mapToEntity(it) }
        return list
    }
}