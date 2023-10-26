package activities

import adapter.Adapter
import adapter.SharedPrefs
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.propertyapp.R
import com.example.propertyapp.databinding.ActivityHomeBinding
import com.example.propertyapp.databinding.FiltersLayoutBinding
import com.google.android.material.navigation.NavigationBarItemView
import com.google.android.material.navigation.NavigationBarMenu
import com.google.android.material.navigation.NavigationView
import helper.PersonDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import listeners.OnItemClick
import models.PropertyAddress

class HomeActivity : AppCompatActivity(), OnItemClick {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var dialogBinding: FiltersLayoutBinding
    private lateinit var database: PersonDatabase
    private lateinit var adapter: Adapter
    private lateinit var data: List<PropertyAddress>

    /*private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle*/
    private lateinit var dialog: Dialog
    private var merla3: Boolean? = null
    private var merla5: Boolean? = null
    private var merla10: Boolean? = null
    private var marla5Text: String? = null
    private var marla10Text: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dialogBinding = FiltersLayoutBinding.inflate(layoutInflater)
        /*drawerLayout = binding.drawer*/
        /*drawerLayout.addDrawerListener(actionBarDrawerToggle)*/
        setSupportActionBar(binding.myToolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawer,
            binding.myToolbar,
            R.string.open,
            R.string.close
        )
        toggle.drawerArrowDrawable.color = resources.getColor(R.color.white)
        binding.drawer.addDrawerListener(toggle)
        toggle.syncState()
        binding.navigationView.setNavigationItemSelectedListener { it ->
            when (it.itemId) {
                R.id.filters -> {

                    dialogBinding = DataBindingUtil.inflate(
                        LayoutInflater.from(this),
                        R.layout.filters_layout,
                        null,
                        false
                    )
                    dialog = Dialog(this)
                    dialog.setContentView(dialogBinding.root)

                    dialogBinding.btnOk.setOnClickListener {
                        dialog.dismiss()
                        merla3 = dialogBinding.checkBox3.isChecked
                        merla5 = dialogBinding.checkBox5.isChecked
                        merla10 = dialogBinding.checkBox10.isChecked
                        if (merla3 == true) {
//                         val marla3Text="3 merla"

                            database.propertyAddDo().filteredData("3merla").observe(
                                this@HomeActivity
                            ) {
                                adapter = Adapter(it, this, this)
                                binding.recyclerView.layoutManager =
                                    LinearLayoutManager(this@HomeActivity)
                                binding.recyclerView.adapter = adapter
                                //                            Toast.makeText(this, "$marla3Text", Toast.LENGTH_LONG).show()
                            }
                        } else if (merla5 == true) {
                            marla5Text = "5merla"

                            database.propertyAddDo().filteredData(marla5Text!!)
                                .observe(this@HomeActivity,
                                    Observer {
                                        adapter = Adapter(it, this@HomeActivity, this@HomeActivity)
                                        binding.recyclerView.layoutManager =
                                            LinearLayoutManager(this@HomeActivity)
                                        with(binding) {
                                            recyclerView.adapter = adapter
                                        }
                                    })
                        } else if (merla10 == true) {
                            marla10Text = "10merla"
                            database.propertyAddDo().filteredData(marla10Text!!).observe(
                                this@HomeActivity
                            ) {
                                adapter = Adapter(it, this@HomeActivity, this@HomeActivity)
                                binding.recyclerView.layoutManager =
                                    LinearLayoutManager(this@HomeActivity)
                                with(binding) {
                                    recyclerView.adapter = adapter
                                }
                            }
                        }


//                    5 maerla
                    }
                    /* dialog= Dialog(this)
                     dialog.setContentView(R.layout.filters_layout);*/
                    dialog.window?.setLayout(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    dialog.setCancelable(false);
                    dialog.window?.attributes?.windowAnimations
                    dialog.window
                    dialog.show()
                    binding.drawer.closeDrawer(GravityCompat.START)
                }



                R.id.logout -> {
                    SharedPrefs(this).let {
                        it.remove("email")
                        it.remove("pass")
                        it.remove("isLoggedIn")
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                    binding.drawer.closeDrawer(GravityCompat.START)
                    // Return true to indicate that the item click is handled
                }

                R.id.addPropertyText -> {
                    startActivity(Intent(this, AddPropertyActivity::class.java))
                }
                R.id.allUsers->{
                    startActivity(Intent(this,AllUsersActivity::class.java))
                }

//
                R.id.clearFilters->{
                    binding.drawer.closeDrawer(GravityCompat.START)
                    getAndSetData()

                }

                R.id.allUsers->{
                    binding.drawer.closeDrawer(GravityCompat.START)
                    getAndSetData()

                }
            }



            false
        }

        database = PersonDatabase.getInstance(this)
        val email = SharedPrefs(this).getStringValue("email", "")
        /*user data */
        val headerView = binding.navigationView.getHeaderView(0)
        val user = headerView.findViewById<TextView>(R.id.tvHeaderUser)
        val mail = headerView.findViewById<TextView>(R.id.tvHeaderEmail)

        CoroutineScope(Dispatchers.IO).launch {
            val userData = database.personDao().getUserEmail(email)
            user.text = userData.name
            mail.text = userData.email
        }



        getAndSetData()

    }


    /*drawer layout inflate*/


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar, menu)
        /*menuInflater.inflate(R.menu.drawer_layout,menu)*/
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
           Boolean
           when (item.itemId) {
//               add propery
            R.id.addPropertyText -> {
                startActivity(Intent(this, AddPropertyActivity::class.java))
            }


            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getAndSetData() {
        database.propertyAddDo().getAllProperty().observe(this) {
            data = it
            adapter = Adapter(it, this, this)
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            with(binding) {
                recyclerView.adapter = adapter
            }
        }

    }

    override fun onClick(position: Int) {
        /*val intent: Intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:${data[position].phoneNumber}")
        startActivity(intent)*/
        val bundle: Bundle = Bundle()
        bundle.let {

            it.putInt("id", data[position].propertyId)
            it.putString("email", data[position].userEmail)
            it.putString("propertyName", data[position].propertyName)
            it.putString("address", data[position].propertyAdd)
            it.putString("city", data[position].city)
            it.putString("kitchen", data[position].kitchen)
            it.putString("rooms", data[position].rooms.toString())
            it.putString("washrooms", data[position].washrooms.toString())
            it.putString("size", data[position].size.toString())
            it.putString("furnished", data[position].furnished)
            it.putString("floors", data[position].floors.toString())
            it.putString("sale", data[position].sale)
            it.putString("price", data[position].price.toString())
        }
        val intent: Intent = Intent(this, UpdateActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun onDeleteClick(position: Int) {
        val propertyAddress = PropertyAddress(
            data[position].propertyId,
            data[position].userEmail,
            data[position].propertyName,
            data[position].propertyAdd,
            data[position].city,
            data[position].size,
            data[position].kitchen,
            data[position].rooms,
            data[position].washrooms,
            data[position].furnished,
            data[position].floors,
            data[position].sale,
            data[position].price
        )
        CoroutineScope(Dispatchers.IO).launch {
            database.propertyAddDo().deleteProperty(propertyAddress)

        }

    }


    private fun showToast() {
        Toast.makeText(this, "", Toast.LENGTH_LONG).show()
    }


//dialog


    /*override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btnLogout -> {
                SharedPrefs(this).let {
                    it.remove("email")
                    it.remove("pass")
                    it.remove("isLoggedIn")
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                binding.drawer.closeDrawer(GravityCompat.START)
                return true // Return true to indicate that the item click is handled
            }

        }
        return false
    }*/
}

