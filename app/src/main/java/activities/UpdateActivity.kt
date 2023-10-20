package activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.propertyapp.R
import com.example.propertyapp.databinding.ActivityUpdateBinding
import helper.PersonDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import models.PropertyAddress

class UpdateActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var database: PersonDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update)
        database = PersonDatabase.getInstance(this)
        binding.btnUpdate.setOnClickListener(this)
        // Use setData for API level 33 and above
        setData()

    }

    private fun setData() {
        /*val parceledData: PropertyAddress? = intent.getParcelableExtra<PropertyAddress>("data")*//*

        }*/
        binding.let {
            it.etPropertyName.setText(intent.getStringExtra("propertyName")).toString()
            it.etAddress.setText(intent.getStringExtra("address")).toString()
            it.etCity.setText(intent.getStringExtra("city")).toString()
            it.etSize.setText(intent.getStringExtra("size")).toString()
            it.etKitchen.setText(intent.getStringExtra("kitchen")).toString()
            it.etRooms.setText(intent.getStringExtra("rooms")).toString()
            it.etWashRoom.setText(intent.getStringExtra("washrooms")).toString()
            it.etFurnished.setText(intent.getStringExtra("furnished")).toString()
            it.etFloors.setText(intent.getStringExtra("floors")).toString()
            it.etSaleRent.setText(intent.getStringExtra("sale")).toString()
            it.etPrice.setText(intent.getStringExtra("price")).toString()
        }

    }

    override fun onClick(v: View?) {
        binding.btnUpdate.setOnClickListener {
            val id = intent.getIntExtra("id",0)
            val email = intent.getStringExtra("email").toString()
            val property = binding.etPropertyName.text
            val address = binding.etAddress.text
            val city = binding.etCity.text
            val size = binding.etSize.text
            val kitchen = binding.etKitchen.text
            val room = binding.etRooms.text
            val washroom = binding.etWashRoom.text
            val furnished = binding.etFurnished.text
            val floor = binding.etFloors.text
            val sale = binding.etSaleRent.text
            val price = binding.etPrice.text

            Toast.makeText(this, "$property", Toast.LENGTH_LONG).show()
            val propertyAddress: PropertyAddress = PropertyAddress(
                id,
                email,
                property.toString(),
                address.toString(),
                city.toString(),
                size.toString(),
                kitchen.toString(),
                room.toString(),
                washroom.toString(),
                furnished.toString(),
                floor.toString(),
                sale.toString(),
                price.toString()
            )
            CoroutineScope(Dispatchers.IO).launch {
                database.propertyAddDo().updateProperty(propertyAddress)
            }
            startActivity(Intent(this@UpdateActivity, HomeActivity::class.java))
        }
    }
}