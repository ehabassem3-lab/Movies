package com.example.movies.di.network

import com.example.movies.data.repositories.home.HomeRepositoryImp
import com.example.movies.domain.repositories.home.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindHomeRepository(
        impl: HomeRepositoryImp
    ): HomeRepository
}