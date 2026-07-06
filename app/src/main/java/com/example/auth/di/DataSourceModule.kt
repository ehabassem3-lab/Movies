package com.example.auth.di

import com.example.auth.data.local.AuthLocalDataSource
import com.example.auth.data.local.AuthLocalDataSourceImpl
import com.example.auth.data.remote.AuthRemoteDataSource
import com.example.auth.data.remote.AuthRemoteDataSourceImpl
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

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {
    @Binds
    abstract fun bindLocalDataSource(
        impl: AuthLocalDataSourceImpl
    ): AuthLocalDataSource
}