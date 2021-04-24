package pierrick.merielbussy.prisonfantasy

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
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

/*
var activityNumber = 0
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userManager = UserManager(this)
    }

    fun createIdentity(button:View) {
        goToActivity(CreateIdentity::class.java)
        /*
        activityNumber = 1
         */
    }

    fun resumeStory(button: View) {
        storedData()
        /*
        when (activityNumber) {
            0 -> goToActivity(MainActivity::class.java)
            1 -> goToActivity(CreateIdentity::class.java)
            2 -> goToActivity(ConfirmIdentityActivity::class.java)
        }
         */
    }

    private fun goToActivity(classname: Class<*>) {
        val intent = Intent(this, classname)
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
        userManager.userAgeFlow.asLiveData().observe(this, {
            age = it
        })
        /*
        userManager.userActivityFlow.asLiveData().observe(this, {
        activityNumber = it
        })
         */
    }
}


