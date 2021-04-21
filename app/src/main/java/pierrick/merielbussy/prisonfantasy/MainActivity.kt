package pierrick.merielbussy.prisonfantasy

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
/*
import androidx.lifecycle.asLiveData
 */
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

lateinit var userManager: UserManager

var lastname = ""
var firstname = ""
var age = 0
var gender = ""
var height = 0
var weight = 0

val c: Calendar = Calendar.getInstance()
val cYear = c.get(Calendar.YEAR)
val cMonth = c.get(Calendar.MONTH)
val cDay = c.get(Calendar.DAY_OF_MONTH)

var rYear = 0
var rMonth = 0
var rDay = 0

class MainActivity : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userManager = UserManager(this)
        gender = getString(R.string.result_gender_f)

        datePicker.init(cYear, cMonth, cDay) {
            view, year, monthOfYear, dayOfMonth ->
            rYear = datePicker.year
            rMonth = datePicker.month
            rDay = datePicker.dayOfMonth
        }

        // A UTILISER DANS LES AUTRES ACTIVITES
        /*
        saveUserCreated()
         */

        val selectGender = switchGenderButton
        selectGender.setOnCheckedChangeListener {selectGender, _ ->
            resultGender.text = if (selectGender.isChecked)
                                    getString(R.string.result_gender_m)
                                else
                                    getString(R.string.result_gender_f)
        }
    }

    /*
    private fun saveUserCreated() {
        userManager.userLastNameFlow.asLiveData().observe(this, {
            lastname = it
        })
        userManager.userFirstNameFlow.asLiveData().observe(this, {
            firstname = it
        })
        userManager.userHeightFlow.asLiveData().observe(this, {
            height = it
        })
        userManager.userWeightFlow.asLiveData().observe(this, {
            weight = it
        })
        userManager.userGenderFlow.asLiveData().observe(this, {
            gender = it
        })
        userManager.userAgeFlow.asLiveData().observe(this, {
            age = it
        })
    }
     */

    fun onValidate(button:View) {
        calculateAge()

        if (createLastName.text.isEmpty()){
            Toast.makeText(applicationContext, getString(R.string.error_lastname_empty), Toast.LENGTH_LONG).show()
        }
        else if (createFirstName.text.isEmpty()) {
            Toast.makeText(applicationContext, getString(R.string.error_firstname_empty), Toast.LENGTH_LONG).show()
        }
        else if (characterHeight.text.isEmpty()) {
            Toast.makeText(applicationContext, getString(R.string.error_height_empty), Toast.LENGTH_LONG).show()
        }
        else if (characterWeight.text.isEmpty()) {
            Toast.makeText(applicationContext, getString(R.string.error_weight_empty), Toast.LENGTH_LONG).show()
        }
        else {
            lastname = createLastName.text.toString()
            firstname = createFirstName.text.toString()

            height = characterHeight.text.toString().toInt()
            weight = characterWeight.text.toString().toInt()

            gender = resultGender.text.toString()

            // POSSIBLEMENT INUTILE
            age = age.toString().toInt()

            if (age <18){
                Toast.makeText(applicationContext, getString(R.string.error_age_value), Toast.LENGTH_LONG).show()
            }
            else if (height <100 || height >250){
                Toast.makeText(applicationContext, getString(R.string.error_height_value), Toast.LENGTH_LONG).show()
            }
            else if (weight <30 || weight >300){
                Toast.makeText(applicationContext, getString(R.string.error_weight_value), Toast.LENGTH_LONG).show()
            }
            else {
                GlobalScope.launch { userManager.storeUser(lastname, firstname, age, height, weight, gender) }

                val intent = Intent(this, FirstActivity::class.java)
                startActivity(intent)
            }
        }
    }

    fun calculateAge() {
        age = cYear - rYear

        if (rMonth < cMonth){
            age -= 1
        }
        else if (rMonth == cMonth && rDay < cDay) {
            age -= 1
        }
    }

}


