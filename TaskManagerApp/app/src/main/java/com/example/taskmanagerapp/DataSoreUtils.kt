package com.example.taskmanagerapp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

object DataStoreUtils {
    private val DARK_THEME_KEY = booleanPreferencesKey("dark_theme_key")

    fun readTheme(context: Context): Flow<Boolean> {
        return context.dataStore.data
            .map { preferences ->
                preferences[DARK_THEME_KEY] ?: false
            }
    }

    suspend fun saveTheme(context: Context, isDarkTheme: Boolean) {
        context.dataStore.edit { settings ->
            settings[DARK_THEME_KEY] = isDarkTheme
        }
    }
}