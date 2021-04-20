package pierrick.merielbussy.prisonfantasy

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        findViewById<TextView>(R.id.fullTextView).text = "Perso = \n$lastname\n$firstname\n$age\n$height\n$weight\n$gender"

    }
}