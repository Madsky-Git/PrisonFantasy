package pierrick.merielbussy.prisonfantasy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_first_call.*
import kotlinx.android.synthetic.main.activity_story_start.*

class FirstCallActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_call)

        activityNumber = 4

        textFirstCall.text = getString(R.string.first_call, gendertitle, lastname, firstname)
    }

    fun onValidate(button: View) {
        nextIntent()
    }

    private fun nextIntent() {
        val intent = Intent(this, FirstCallActivity::class.java)
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        userManager.saveUser()
    }
}