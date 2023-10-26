package activities

import adapter.SharedPrefs
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.propertyapp.R
import com.example.propertyapp.databinding.ActivityAddPropertyBinding
import helper.PersonDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import models.PropertyAddress

class AddPropertyActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityAddPropertyBinding
    private lateinit var database: PersonDatabase
    private var email: String? = null
    private var property: PropertyAddress? = null
    private var sizeValue: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_property)
        database = PersonDatabase.getInstance(this)
        binding.btnAdd.setOnClickListener(this)
        email = SharedPrefs(this@AddPropertyActivity).getStringValue("email", "")
    }

    /*//  city = binding.etCity.text.toString()
    //     val address = binding.etAddress.text.toString()
    //     val size = binding.etSize.text.toString()
    //     val propertyName = binding.etPropertyName.text.toString()
    //     val kitchen = binding.etKitchen.text.toString()
    //     val rooms = binding.etRooms.text.toString()
    //     val washrooms = binding.etWashRoom.text.toString()
    //     val furnished = binding.etFurnished.text.toString()
    //     val sale = binding.etSaleRent.text.toString()
    //     val floors = binding.etFloors.text.toString()
    //     val price = binding.etPrice.text.toString()*/
    override fun onClick(v: View?) {
        property = PropertyAddress(
            0,
            email,
            binding.etPropertyName.text.toString(),
            binding.etAddress.text.toString(),
            binding.etCity.text.toString(),
            binding.etSize.text.trim().toString(),
            binding.etKitchen.text.toString(),
            binding.etRooms.text.toString(),
            binding.etWashRoom.text.toString(),
            binding.etSaleRent.text.toString(),
            binding.etFloors.text.toString(),
            binding.etFurnished.text.toString(),
            binding.etPrice.text.toString()
        )
        binding.btnAdd.setOnClickListener {
            sizeValue = binding.etSize.text.trim().toString()
            if (!sizeValue.equals("3merla") || !sizeValue.equals("5merla") || !sizeValue.equals("10merla")) {

                Toast.makeText(this, "Only 3,5,10 merla are allowed ", Toast.LENGTH_LONG).show()
            } else if
                           (!sizeValue.equals("3merla")) {
                binding.etSize.setText("3merla")
                launchCoroutine()

            } else if (!sizeValue.equals("5merla")) {
                binding.etSize.setText("5merla")
                launchCoroutine()
            } else if (!sizeValue.equals("10merla")) {
                binding.etSize.setText("10merla")
                launchCoroutine()
            }
        }
    }

    private fun launchCoroutine() {
//        email = SharedPrefs(this).getStringValue("email","")
        CoroutineScope(Dispatchers.IO).launch {
            property?.let { database.propertyAddDo().insertProperty(it) }
            startActivity(Intent(this@AddPropertyActivity, HomeActivity::class.java))
            finish()
        }
    }

}