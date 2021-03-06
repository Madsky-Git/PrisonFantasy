package pierrick.merielbussy.prisonfantasy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_story_start.*

class StoryStartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_start)

        activityNumber = 3
        userManager.saveUser()

        textStartStory.text = getString(R.string.start_story_text)
    }

    fun onValidate(button: View) {
        nextIntent()
    }

    private fun nextIntent() {
        val intent = Intent(this, FirstCallActivity::class.java)
        startActivity(intent)
    }
}