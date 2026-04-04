package com.harshcode.newsapp.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.harshcode.newsapp.domain.manager.LocalUserManager
import com.harshcode.newsapp.util.Constants
import com.harshcode.newsapp.util.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(
    private val context : Context
): LocalUserManager {

    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings ->
            settings[PreferencesKeys.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
       return context.dataStore.data.map { preferences ->
           preferences[PreferencesKeys.APP_ENTRY]?: false
       }
    }
}

//So we want to have instance from dataStore
// we can simply access dataStore by using Context
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

//Now to save key value inside dataStorePreferences  we need preferences keys so lets create private object
private object PreferencesKeys{
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
}

