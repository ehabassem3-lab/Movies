package com.example.auth.ds

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {

    // Auth
    val SESSION_ID = stringPreferencesKey("session_id")
    val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")

    val ACCOUNT_ID = intPreferencesKey("account_id")
    val USERNAME = stringPreferencesKey("username")
    val NAME = stringPreferencesKey("name")
    val ISO_639_1 = stringPreferencesKey("iso_639_1")
    val ISO_3166_1 = stringPreferencesKey("iso_3166_1")
    val INCLUDE_ADULT = booleanPreferencesKey("include_adult")

    // Avatar
    val AVATAR_PATH = stringPreferencesKey("avatar_path")
    val GRAVATAR_HASH = stringPreferencesKey("gravatar_hash")
}