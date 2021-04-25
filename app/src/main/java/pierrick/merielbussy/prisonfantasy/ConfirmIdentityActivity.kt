package pierrick.merielbussy.prisonfantasy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_confirm_identity.*

class ConfirmIdentityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_identity)

        activityNumber = 2

        textSummary.text = getString(R.string.test_string, firstname, lastname, rDay, rMonth+1, rYear, age, height, weight, gender)
    }

    fun onValidate(button: View) {

        val answer = answerSummary.text.toString().toUpperCase()

        when {
            answer.isEmpty() -> Toast.makeText(applicationContext, getString(R.string.error_answer_empty), Toast.LENGTH_LONG).show()
            else -> {
                when {
                    answer.contains(getString(R.string.confirm_identity)) -> confirmIdentity()
                    answer.contains(getString(R.string.modify_identity)) -> modifyIdentity()
                    answer.contains(getString(R.string.delete_identity)) -> deleteIdentity()
                    else -> Toast.makeText(applicationContext, getString(R.string.error_answer_incorrect_confirm_identity), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun confirmIdentity() {
        userManager.saveUser()
        Toast.makeText(applicationContext, getString(R.string.identity_confirmed), Toast.LENGTH_LONG).show()
        nextIntent()
    }

    private fun modifyIdentity() {
        onBackPressed()
    }

    private fun deleteIdentity() {
        val intent = Intent(this, CreateIdentityActivity::class.java)
        startActivity(intent)
    }

    private fun nextIntent() {
        val intent = Intent(this, StoryStartActivity::class.java)
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        userManager.saveUser()
    }
}