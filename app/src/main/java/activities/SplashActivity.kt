package activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.propertyapp.R
import com.example.propertyapp.databinding.ActivityLoginBinding
import helper.PersonDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        /*window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,

        )*/
        val prefs: SharedPreferences = getSharedPreferences("userData", MODE_PRIVATE)
        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        //Normal Handler is deprecated , so we have to change the code little bit
        Handler(Looper.getMainLooper()).postDelayed({
            if (prefs.contains("isLoggedIn")){
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this, SignupActivity::class.java)
                startActivity(intent)
            }

        }, 3000) // 3
    }
}