package pierrick.merielbussy.prisonfantasy

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity

var genderSelected = "testResultGender"

class MainActivity : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<TextView>(R.id.resultGender).text = getString(R.string.result_gender_f)

                val selectGender = findViewById<Switch>(R.id.switchGenderButton)

                selectGender.setOnCheckedChangeListener {selectGender, _ ->
                    findViewById<TextView>(R.id.resultGender).text = if (selectGender.isChecked)
                        getString(R.string.result_gender_m)
                    else
                        getString(R.string.result_gender_f)
        }
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    fun onValidate(button:View) {
        findViewById<Button>(R.id.buttonCreateID).setOnClickListener {

            val selectGender = findViewById<Switch>(R.id.switchGenderButton)

            if (selectGender.isChecked) {
                genderSelected = getString(R.string.result_gender_m)
                val intent = Intent(this, FirstActivity::class.java)
                startActivity(intent)
            } else {
                genderSelected = getString(R.string.result_gender_f)
                val intent = Intent(this, FirstActivity::class.java)
                startActivity(intent)
            }
        }
    }

}


