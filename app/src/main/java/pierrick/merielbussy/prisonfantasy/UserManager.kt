package pierrick.merielbussy.prisonfantasy

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

var activityNumber = 0

private val Context.dataStore by preferencesDataStore("user_prefs")

class UserManager(context: Context) {
    private val dataStore = context.dataStore

    companion object {
        val CHAR_LASTNAME_KEY = stringPreferencesKey("CHAR_LASTNAME")
        val CHAR_FIRSTNAME_KEY = stringPreferencesKey("CHAR_FIRSTNAME")
        val CHAR_AGE_KEY = intPreferencesKey("CHAR_AGE")
        val CHAR_HEIGHT_KEY = intPreferencesKey("CHAR_HEIGHT")
        val CHAR_WEIGHT_KEY = intPreferencesKey("CHAR_WEIGHT")
        val CHAR_GENDER_KEY = stringPreferencesKey("CHAR_GENDER")
        val ACTIVITY_KEY = intPreferencesKey("ACTIVITY")
    }

    suspend fun storeUser(lastname: String, firstname: String, age: Int, height: Int, weight: Int, gender: String, activity: Int) {
        dataStore.edit {
            it[CHAR_LASTNAME_KEY] = lastname
            it[CHAR_FIRSTNAME_KEY] = firstname
            it[CHAR_AGE_KEY] = age
            it[CHAR_HEIGHT_KEY] = height
            it[CHAR_WEIGHT_KEY] = weight
            it[CHAR_GENDER_KEY] = gender
            it[ACTIVITY_KEY] = activity
        }
    }

    val userLastNameFlow: kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {it[CHAR_LASTNAME_KEY] ?: ""}

    val userFirstNameFlow: kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {it[CHAR_FIRSTNAME_KEY] ?: ""}

    val userAgeFlow: kotlinx.coroutines.flow.Flow<Int> = dataStore.data.map {it[CHAR_AGE_KEY] ?: 0}

    val userHeightFlow: kotlinx.coroutines.flow.Flow<Int> = dataStore.data.map {it[CHAR_HEIGHT_KEY] ?: 0}

    val userGenderFlow: kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {it[CHAR_GENDER_KEY] ?: ""}

    val userWeightFlow: kotlinx.coroutines.flow.Flow<Int> = dataStore.data.map {it[CHAR_WEIGHT_KEY] ?: 0}

    val userActivityFlow: kotlinx.coroutines.flow.Flow<Int> = dataStore.data.map {it[ACTIVITY_KEY] ?: 0}

    fun saveUser() {
        GlobalScope.launch {
            userManager.storeUser(
                    lastname,
                    firstname,
                    age,
                    height,
                    weight,
                    gender,
                    activityNumber
            )
        }
    }
}