package pierrick.merielbussy.prisonfantasy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_first_call.*
import kotlinx.android.synthetic.main.activity_postman.*

class PostmanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_postman)

        activityNumber = 5
        userManager.saveUser()

        textPostMan.text = getString(R.string.postman_text, gendertitle, lastname)
    }

    fun onValidate(button: View) {
        nextIntent()
    }

    private fun nextIntent() {
        val intent = Intent(this, PostmanActivity::class.java)
        startActivity(intent)
    }
}