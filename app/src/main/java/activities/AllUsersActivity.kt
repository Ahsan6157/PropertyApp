package activities

import adapter.ListViewAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.BaseAdapter
import android.widget.ListAdapter
import androidx.databinding.DataBindingUtil
import androidx.room.Database
import com.example.propertyapp.R
import com.example.propertyapp.databinding.ActivityAllUsersBinding
import helper.PersonDatabase

class AllUsersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllUsersBinding
    private lateinit var database: PersonDatabase
    private lateinit var adapter: BaseAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_users)
        database = PersonDatabase.getInstance(this)

        displayAllUsers()
    }

    private fun displayAllUsers() {
        val listView = binding.lvUsers
        val userData = database.personDao().getAllUser()
        userData.observe(this) {
            adapter = ListViewAdapter(this, it)
            listView.adapter = adapter
        }

    }
}