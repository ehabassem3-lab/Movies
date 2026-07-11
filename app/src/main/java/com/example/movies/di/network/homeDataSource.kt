package com.example.movies.di.network

import com.example.movies.data.datasource.home.LocalDataSource
import com.example.movies.data.datasource.home.LocalDataSourceImpl
import com.example.movies.data.datasource.home.RemoteDataSource
import com.example.movies.data.datasource.home.RemoteDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class) 
abstract class DataSourceModule {

    @Binds
    abstract fun bindRemoteDataSource(
        impl: RemoteDataSourceImp
    ): RemoteDataSource
}

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindLocalDataSource(
        impl: LocalDataSourceImpl
    ): LocalDataSource
}