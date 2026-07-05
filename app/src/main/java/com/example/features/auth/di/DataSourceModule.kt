package com.example.features.auth.di

import com.example.auth.data.AuthRemoteDataSource
import com.example.auth.data.AuthRemoteDataSourceImpl
import com.example.movies.data.datasource.home.RemoteDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindAuthDataSource(
        impl: AuthRemoteDataSourceImpl
    ): AuthRemoteDataSource
}