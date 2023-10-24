package activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.room.Database
import com.example.propertyapp.R
import com.example.propertyapp.databinding.ActivityAllUsersBinding
import helper.PersonDatabase

class AllUsersActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAllUsersBinding
    private lateinit var database: PersonDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_all_users)
        database = PersonDatabase.getInstance(this)

        /*displayAllUsers()*/
    }

    /*private fun displayAllUsers() {
        val userData = database.personDao().getAllUser()
        userData.observe(this){
*//*binding.lvUsers.*//*
        }

    }*/
}