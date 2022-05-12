package at.technikum_wien.polzert.news.viewmodels

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

data class UserPreferences(val text : String)

private const val USER_PREFERENCES_NAME = "user_preferences"

val Context.dataStore by preferencesDataStore(
    name = USER_PREFERENCES_NAME)

class UserPreferencesRepository(private val userPreferenceDataStore : DataStore<Preferences>) {
    private object PreferenceKeys {
        val TEXT = stringPreferencesKey("text")
    }

    val userPreferencesFlow = userPreferenceDataStore.data
        .catch {
            if (it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map {
            val text = it[PreferenceKeys.TEXT] ?: ""
            UserPreferences(text)
        }

    suspend fun updateText(text : String) {
        userPreferenceDataStore.edit {
            it[PreferenceKeys.TEXT] = text
        }
    }
}