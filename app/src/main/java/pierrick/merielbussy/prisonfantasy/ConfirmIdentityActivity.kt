package pierrick.merielbussy.prisonfantasy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_confirm_identity.*
import java.util.*

class ConfirmIdentityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_identity)

        textPage1.text = getString(R.string.test_string, firstname, lastname, rDay, rMonth+1, rYear, age, height, weight, gender)
    }

    fun onValidate(button: View) {

        val answer = answerPage1.text.toString().toUpperCase()

        if (answer.isEmpty()){
            Toast.makeText(applicationContext, getString(R.string.error_answer_empty), Toast.LENGTH_LONG).show()
        }
        else {
            if (answer.contains(getString(R.string.confirm))) {
                nextIntent()
            }
            else if (answer.contains(getString(R.string.cancel))) {
                mainIntent()
            }
            else {
                Toast.makeText(applicationContext, getString(R.string.error_answer_incorrect_page1), Toast.LENGTH_LONG).show()
            }
        }
    }

    fun nextIntent() {
        val intent = Intent(this, ConfirmIdentityActivity::class.java)
        startActivity(intent)
    }

    fun mainIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}