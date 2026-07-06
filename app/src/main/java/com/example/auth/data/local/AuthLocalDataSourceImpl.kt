package com.example.auth.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.auth.data.remote.AuthRemoteDataSource
import com.example.auth.ds.PreferencesKeys
import com.example.movies.ui.main.tabs.profile.UserData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(
    private  val dataStore : DataStore<Preferences>
) : AuthLocalDataSource {
    override suspend fun getUser(): Result<UserData> {
      val user = dataStore.data.map {
          UserData(
              name = it[PreferencesKeys.NAME]?:"",
              username = it[PreferencesKeys.USERNAME]?:"",
              avatar = it[PreferencesKeys.AVATAR_PATH]?:"",
              isIncludedAdult = it[PreferencesKeys.INCLUDE_ADULT]?:false
          )
      }

        return Result.success(user.first())



    }
}