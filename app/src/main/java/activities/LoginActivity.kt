package activities

import adapter.SharedPrefs
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.propertyapp.R
import com.example.propertyapp.databinding.ActivityLoginBinding
import helper.PersonDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding

    //    lateinit var prefs: SharedPreferences
//    lateinit var editor: SharedPreferences.Editor
    var isLogeedIn: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        binding.etPass.clearFocus()
        val db: PersonDatabase = PersonDatabase.getInstance(this@LoginActivity)
        /*CoroutineScope(Dispatchers.Main).launch {
            db.personDao().getAllUser().observe(this@LoginActivity, Observer { it ->
                it.forEach {
                    if (binding.etEmail.text.toString() == it.email && binding.etPass.text.toString() == it.pass) {
                        isLogeedIn = true
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    }
//                    prefs to store data
                    SharedPrefs(this@LoginActivity).let {
                        it.setString("email", binding.etEmail.text.toString())
                        it.setString("pass", binding.etPass.text.toString())
                        it.setBoolean("isLoggedIn", isLogeedIn)
                    }
                }
            })
        }*/
//            get single user
        CoroutineScope(Dispatchers.IO).launch {
            val email = binding.etEmail.text.toString()
            val pass = binding.etPass.text.toString()
            db.personDao().getUser(email, pass)
            isLogeedIn = true
            /*prefs to store data*/
            SharedPrefs(this@LoginActivity).let {
                it.setString("email", email)
                it.setString("pass", pass)
                it.setBoolean("isLoggedIn", isLogeedIn)
            }
            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
        }
    }
}


