package pierrick.merielbussy.prisonfantasy

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


lateinit var userManager: UserManager

var lastname = ""
var firstname = ""
var age = 0
var height = 0
var weight = 0
var gender = ""

val c: Calendar = Calendar.getInstance()
val cYear = c.get(Calendar.YEAR)
val cMonth = c.get(Calendar.MONTH)
val cDay = c.get(Calendar.DAY_OF_MONTH)

var rYear = cYear
var rMonth = cMonth
var rDay = cDay


class MainActivity : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userManager = UserManager(this)

        datePicker.init(cYear, cMonth, cDay,
            DatePicker.OnDateChangedListener {view, year, monthOfYear, dayOfMonth ->
                rYear = datePicker.year
                rMonth = datePicker.month
                rDay = datePicker.dayOfMonth
                age = cYear - rYear
            })

        saveUserCreated()

        findViewById<TextView>(R.id.resultGender).text = getString(R.string.result_gender_f)
                val selectGender = findViewById<Switch>(R.id.switchGenderButton)
                selectGender.setOnCheckedChangeListener {selectGender, _ ->
                    findViewById<TextView>(R.id.resultGender).text = if (selectGender.isChecked)
                        getString(R.string.result_gender_m)
                    else
                        getString(R.string.result_gender_f)
        }
    }

    private fun saveUserCreated() {
        userManager.userLastNameFlow.asLiveData().observe(this, {
            lastname = it
            findViewById<TextView>(R.id.createLastName).text = it.toString()
        })
        userManager.userFirstNameFlow.asLiveData().observe(this, {
            firstname = it
            findViewById<TextView>(R.id.createFirstName).text = it.toString()
        })
        userManager.userHeightFlow.asLiveData().observe(this, {
            height = it
            findViewById<TextView>(R.id.characterHeight).text = it.toString()
        })
        userManager.userWeightFlow.asLiveData().observe(this, {
            weight = it
            findViewById<TextView>(R.id.characterWeight).text = it.toString()
        })
        userManager.userGenderFlow.asLiveData().observe(this, {
            gender = it
            findViewById<TextView>(R.id.resultGender).text = it.toString()
        })
    }

    fun onValidate(button:View) {
        findViewById<Button>(R.id.buttonCreateID).setOnClickListener {


            /*
        if (age <18){
            Toast.makeText(context, "Votre personnage doit être majeur", Toast.LENGTH_SHORT).show()
        }

        if (height <100 || height >200){
            Toast.makeText(context, "La taille doit être entre 100 et 200 cm", Toast.LENGTH_SHORT).show()
        }

        if (weight <50 || weight >200){
            Toast.makeText(context, "Le poids doit être entre 50 et 200 kg", Toast.LENGTH_SHORT).show()
        }
             */

            lastname = findViewById<TextView>(R.id.createLastName).text.toString()
            firstname = findViewById<TextView>(R.id.createFirstName).text.toString()
            height = findViewById<TextView>(R.id.characterHeight).text.toString().toInt()
            weight = findViewById<TextView>(R.id.characterWeight).text.toString().toInt()
            gender = findViewById<TextView>(R.id.resultGender).text.toString()

            GlobalScope.launch {userManager.storeUser(lastname, firstname, age, height, weight, gender)}

            val intent = Intent(this, FirstActivity::class.java)
            startActivity(intent)
        }
    }

}


