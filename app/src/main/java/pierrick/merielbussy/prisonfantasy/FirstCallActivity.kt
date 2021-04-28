package pierrick.merielbussy.prisonfantasy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_first_call.*

class FirstCallActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_call)

        activityNumber = 4
        userManager.saveUser()

        Generate().genPassword()
        Generate().genUsername()
        Generate().genFourDigitsCode()

        password = tempPassword
        username = tempUsername
        code = tempCode

        textFirstCall.text = getString(R.string.first_call_text, gendertitle, lastname, firstname, tempCode, tempUsername, tempPassword)
    }

    fun onValidate(button: View) {
        nextIntent()
    }

    private fun nextIntent() {
        val intent = Intent(this, PostmanActivity::class.java)
        startActivity(intent)
    }
}