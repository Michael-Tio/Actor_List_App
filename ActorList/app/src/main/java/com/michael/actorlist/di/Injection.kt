package com.michael.actorlist.di

import com.michael.actorlist.data.ActorRepository

object Injection {
    fun provideRepository(): ActorRepository {
        return ActorRepository.getInstance()
    }
}