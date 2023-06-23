package com.michael.actorlist.data

import com.michael.actorlist.model.Actor
import com.michael.actorlist.model.FakeActorsDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ActorRepository {

    private val actor = mutableListOf<Actor>()

    init {
        if (actor.isEmpty()) {
            FakeActorsDataSource.dummyActors.forEach {
                actor.add(it)
            }
        }
    }

    fun getAllActors(): Flow<List<Actor>> {
        return flowOf(actor)
    }

    fun getActorById(actorId: Long): Actor {
        return actor.first {
            it.id == actorId
        }
    }

    fun getActorByName(query: String): Flow<List<Actor>>{
        return flowOf(actor.filter {
            it.name.contains(query, ignoreCase = true)
        }.toList())
    }

    companion object {
        @Volatile
        private var instance: ActorRepository? = null

        fun getInstance(): ActorRepository =
            instance ?: synchronized(this) {
                ActorRepository().apply {
                    instance = this
                }
            }
    }
}

