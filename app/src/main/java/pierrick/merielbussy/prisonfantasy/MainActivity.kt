package pierrick.merielbussy.prisonfantasy

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import java.util.*

lateinit var userManager: UserManager
lateinit var activityName: Class<*>

var lastname = ""
var firstname = ""
var age = 0
var gender = ""
var gendertitle = ""
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userManager = UserManager(this)
        storedData()
    }

    fun createIdentity(button:View) {
        val intent = Intent(this, CreateIdentityActivity::class.java)
        startActivity(intent)
    }

    fun resumeStory(button: View) {
        ConversionIntToClassName().convertInt(activityNumber)

        val intent = Intent(this, activityName)
        startActivity(intent)
    }

    private fun storedData() {
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
        userManager.userGenderTitleFlow.asLiveData().observe(this, {
            gendertitle = it
        })
        userManager.userAgeFlow.asLiveData().observe(this, {
            age = it
        })
        userManager.userActivityFlow.asLiveData().observe(this, {
            activityNumber = it
        })

    }
}


