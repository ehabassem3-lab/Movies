package com.example.auth.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.auth.data.local.AuthLocalDataSource
import com.example.auth.data.remote.AuthRemoteDataSource
import com.example.auth.domain.repository.AuthRepository
import com.example.auth.ds.PreferencesKeys
import com.example.auth.network.response.AccountResponse
import com.example.auth.network.response.RequestTokenResponse
import com.example.auth.network.response.SessionResponse
import com.example.movies.ui.main.tabs.profile.UserData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dataSource: AuthRemoteDataSource,
    private val localData: AuthLocalDataSource ,
    private  val dataStore : DataStore<Preferences>,
  )  : AuthRepository{


    override suspend fun createRequestToken(): Result<RequestTokenResponse >{
     val request = dataSource.createRequestToken()
        return if (request.isSuccess){
            Result.success(request.getOrNull()!!)
        }else{

            Result.failure(Throwable(request.exceptionOrNull()))
        }
    }

    override suspend fun createSession(requestToken: String): Result<SessionResponse> {
        return  try {
            val request = dataSource.createSession(requestToken)
             if (request.isSuccess){
                 val session = request.getOrNull()!!

                 dataStore.edit { preferences ->
                     preferences[PreferencesKeys.SESSION_ID] = session.sessionId
                     preferences[PreferencesKeys.IS_LOGGED_IN] = true
                 }
                Result.success(request.getOrNull()!!)
            }else{
                Result.failure(Throwable(request.exceptionOrNull()))
            }
        }catch (e : Throwable){
            Result.failure(e)
        }

    }

    override suspend fun getAccount(sessionId: String): Result<AccountResponse> {
         return try {
               val request = dataSource.getAccount(sessionId)
             if(request.isSuccess){
                 val session = request.getOrNull()!!
                     dataStore.edit {  preferences ->
                         preferences[PreferencesKeys.NAME]  = session.name?:""
                         preferences[PreferencesKeys.ACCOUNT_ID]  = session.id
                         preferences[PreferencesKeys.USERNAME]  = session.username?:""
                         preferences[PreferencesKeys.AVATAR_PATH]  = session.avatar?.tmdb?.avatarPath?:""
                         preferences[PreferencesKeys.GRAVATAR_HASH]  = session.avatar?.gravatar?.hash?:""
                         preferences[PreferencesKeys.INCLUDE_ADULT]  = session.includeAdult?:false



                     }
                 Result.success(session)

             }else{
                 Result.failure(Throwable(request.exceptionOrNull()))
             }
        }catch (e  : Throwable){
             Result.failure(e)
        }


    }

    override suspend fun LogOut(): Result<Unit> {
        dataStore.edit {  preferences ->
           preferences.clear()
        }
        return Result.success(Unit)
    }

    override suspend fun isLoggedIn(): Result<Unit> {
      val status =  dataStore.data.map { it[PreferencesKeys.IS_LOGGED_IN] ?: false }.first()
        return if (status){
             Result.success(Unit)
        }else{
            Result.failure(Throwable("Some Thing Went Wrong"))
        }
    }

    override suspend fun getUser(): Result<UserData> {
       val data = localData.getUser()
      if (data.isSuccess){
          return Result.success(data.getOrNull()!!)
      }else{
          return Result.failure(Throwable(data.exceptionOrNull()))
      }
    }
}