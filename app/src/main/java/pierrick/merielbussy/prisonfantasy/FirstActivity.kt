package pierrick.merielbussy.prisonfantasy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_first.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        textPage1.text = getString(R.string.test_string, firstname, lastname, rDay, rMonth+1, rYear, age, height, weight, gender)

    }

    fun onValidate(button: View) {

        var answer = answerPage1.text
        
        val confirmCapsOn = "Confirmer"
        val confirmCapsOff = "confirmer"
        val cancelCapsOn = "Annuler"
        val cancelCapsOff = "annuler"


        if (answer.isEmpty()){
            Toast.makeText(applicationContext, getString(R.string.error_answer_empty), Toast.LENGTH_LONG).show()
        }
        else {
            if (answer.toString() == confirmCapsOn || answer.toString() == confirmCapsOff) {
                val intent = Intent(this, FirstActivity::class.java)
                startActivity(intent)
            }
            else if (answer.toString() == cancelCapsOn || answer.toString() == cancelCapsOff) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(applicationContext, getString(R.string.error_answer_incorrect_page1), Toast.LENGTH_LONG).show()
            }

        }
    }
}