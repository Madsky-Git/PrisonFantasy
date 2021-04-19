package pierrick.merielbussy.prisonfantasy

import android.content.Context
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import java.util.concurrent.Flow
import java.util.prefs.Preferences

private val Context.dataStore by preferencesDataStore("user_prefs")

class UserManager(context: Context) {
    private val dataStore = context.dataStore

    companion object {

        val CHAR_LASTNAME_KEY = stringPreferencesKey("CHAR_LASTNAME")
        val CHAR_FIRSTNAME_KEY = stringPreferencesKey("CHAR_FIRSTNAME")
        val CHAR_BIRTHDAY_KEY = stringPreferencesKey("CHAR_BIRTHDAY")
        val CHAR_HEIGHT_KEY = intPreferencesKey("CHAR_HEIGHT")
        val CHAR_WEIGHT_KEY = intPreferencesKey("CHAR_WEIGHT")
        val CHAR_GENDER_KEY = stringPreferencesKey("CHAR_GENDER")
    }

    suspend fun storeUser(lastname: String, firstname: String, birthday: String, height: Int, weight: Int, gender: String) {
        dataStore.edit {
            it[CHAR_LASTNAME_KEY] = lastname
            it[CHAR_FIRSTNAME_KEY] = firstname
            it[CHAR_BIRTHDAY_KEY] = birthday
            it[CHAR_HEIGHT_KEY] = height
            it[CHAR_WEIGHT_KEY] = weight
            it[CHAR_GENDER_KEY] = gender
        }
    }

    val userLastNameFlow: kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {
        it[CHAR_LASTNAME_KEY] ?: ""
    }

    val userFirstNameFlow: kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {
        it[CHAR_FIRSTNAME_KEY] ?: ""
    }

    val userBirthdayFlow: kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {
        it[CHAR_BIRTHDAY_KEY] ?: ""
    }

    val userHeightFlow: kotlinx.coroutines.flow.Flow<Int> = dataStore.data.map {
        val height = it[CHAR_HEIGHT_KEY] ?: 0
        if (height <100 || height >200){
            Toast.makeText(context, "La taille doit être entre 100 et 200 cm", Toast.LENGTH_SHORT).show()
        }
        height
    }

    val userWeightFlow: kotlinx.coroutines.flow.Flow<Int> = dataStore.data.map {
        val weight = it[CHAR_WEIGHT_KEY] ?: 0
        if (weight <50 || weight >200){
            Toast.makeText(context, "Le poids doit être entre 50 et 200 kg", Toast.LENGTH_SHORT).show()
        }
        weight
    }

    val userGenderFlow: kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {
        it[CHAR_GENDER_KEY] ?: ""
    }

}