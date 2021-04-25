package pierrick.merielbussy.prisonfantasy

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_create_identity.*

class CreateIdentityActivity : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_identity)

        activityNumber = 1

        gender = getString(R.string.result_gender_f)

        initDatePicker()

        val selectGender = switchGenderButton
        selectGender.setOnCheckedChangeListener {selectGender, _ ->
            resultGender.text = if (selectGender.isChecked)
                getString(R.string.result_gender_m)
            else
                getString(R.string.result_gender_f)
        }
    }

    fun onValidate(button:View) {
        calculateAge()

        when {
            createLastName.text.isEmpty() -> Toast.makeText(applicationContext, getString(R.string.error_lastname_empty), Toast.LENGTH_LONG).show()
            createFirstName.text.isEmpty() -> Toast.makeText(applicationContext, getString(R.string.error_firstname_empty), Toast.LENGTH_LONG).show()
            characterHeight.text.isEmpty() -> Toast.makeText(applicationContext, getString(R.string.error_height_empty), Toast.LENGTH_LONG).show()
            characterWeight.text.isEmpty() -> Toast.makeText(applicationContext, getString(R.string.error_weight_empty), Toast.LENGTH_LONG).show()
            else -> {
                lastname = createLastName.text.toString()
                firstname = createFirstName.text.toString()
                height = characterHeight.text.toString().toInt()
                weight = characterWeight.text.toString().toInt()
                gender = resultGender.text.toString()
                genderToTitle()
                // POSSIBLEMENT INUTILE
                age = age.toString().toInt()
                //

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
                    nextIntent()
                }
            }
        }
    }

    private fun genderToTitle() {
        gendertitle = if (gender == getString(R.string.result_gender_f)) {
            getString(R.string.gender_title_f)
        } else {
            getString(R.string.gender_title_m)
        }
    }

    private fun initDatePicker() {
        datePicker.init(cYear, cMonth, cDay) {
            view, year, monthOfYear, dayOfMonth ->
            rYear = datePicker.year
            rMonth = datePicker.month
            rDay = datePicker.dayOfMonth
        }
    }

    private fun calculateAge() {
        age = cYear - rYear
        if (rMonth > cMonth) {age -= 1}
        else if (rMonth == cMonth && rDay > cDay) {age -= 1}
    }

    private fun nextIntent() {
        val intent = Intent(this, ConfirmIdentityActivity::class.java)
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        userManager.saveUser()
    }
}