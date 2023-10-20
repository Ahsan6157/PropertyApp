package activities

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.example.propertyapp.R
import com.example.propertyapp.databinding.ActivitySignupBinding
import helper.PersonDatabase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import models.Person
import java.time.LocalDate
import java.time.Period
import java.util.Calendar
import java.util.regex.Pattern
/*
* FOR DATE PICKER YOU NEED TO USE DatePickerDialog.OnDateSetListener
*
* */
class SignupActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding: ActivitySignupBinding
    //   1 database instance
    lateinit var database: PersonDatabase

    var age:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        binding.btnLogin.setOnClickListener(this)
        /*binding.btnDatePicker.setOnClickListener(this)*/
//        binding.prevDate.setOnClickListener(this)
        binding.tvHaveAcc.setOnClickListener(this)
    }

    private fun isEmailValid(): Boolean {
        //            email
        val regex =
            Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+")
        if (binding.etName.toString().isEmpty()) {
            binding.etName.error = "Field cannot be empty"
        } else if (!binding.etEmail.text.matches(regex.toRegex())) {
            binding.etEmail.error = "Email must be valid"
        }
        return true
    }

    private fun isNameValid(): Boolean {
        //        name
        if (binding.etName.text.isEmpty()) {
            binding.etName.error = "Field can't be empty"
            return false
        } else if (binding.etName.text.length > 15) {
            binding.etName.error = "15 characters allowed"
            return false
            }
        return true
    }

    private fun isValidPassword(): Boolean {
        val pass_reg =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\\\$%^&+=])(?=\\S+$).{8,20}$")
        val matcher = pass_reg.matcher(binding.etPass.text)
        if (binding.etPass.text.isEmpty()) {
            binding.etPass.error = "Field can't be empty"
            return false
        }
        if (!matcher.matches()) {
//            pass.error = "Password must be valid"
            return false
        }
        return matcher.matches()
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onClick(v: View?) {
        binding.btnLogin.setOnClickListener {
            if (isNameValid() && isEmailValid() && isValidPassword()) {
                val p: Person = Person(0, binding.etName.text.toString(), binding.etEmail.text.toString(),binding.etPhone.text.toString(), binding.etPass.text.toString())
                val p1: Person = Person(0, "Rehman", "anaveedgul@gmail.com","03126360936", "Jhang@#12")
                val p2: Person = Person(0, "Ali", "anaveedgul@gmail.com","03126360936", "Jhang@#12")
                val p4: Person = Person(0, "Nalain", "anaveedgul@gmail.com","03126360936", "Jhang@#12")
                val p5: Person = Person(0, "Farhan", "anaveedgul@gmail.com","03126360936", "Jhang@#12")
                val p6: Person = Person(0, "Zain", "anaveedgul@gmail.com","03126360936", "Jhang@#12")
                val p7: Person = Person(0, "Zille", "anaveedgul@gmail.com","03126360936", "Jhang@#12")
                val p8: Person = Person(0, "Hussain", "HussainMuhammad@gmail.com","03126360936", "Jhang@#12")
                val p9: Person = Person(0, "Zeeshan", "ZeshanMuhammad@gmail.com","03126360936", "Jhang@#12")
                val p10: Person = Person(0, "Ahsan", "anaveedgul@gmail.com","03126360936", "Jhang@#12")
                val p11: Person = Person(0, "Naveed", "anaveedgul@gmail.com","03126360936", "Jhang@#12")
                val p12: Person = Person(0, "Gul", "anaveedgul@gmail.com","03126360936", "Jhang@#12")
                val p13: Person = Person(0, "Saad", "anaveedgul@gmail.com","03126360936", "Jhang@#12")
                val p14: Person = Person(0, "Muzammil", "anaveedgul@gmail.com","03126360936", "Jhang@#12")
                val p15: Person = Person(0, "Mudassir", "anaveedgul@gmail.com","03126360936", "Jhang@#12")



                val list:List<Person> = listOf(p,p1,p2,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15)


                database = PersonDatabase.getInstance(this)
                GlobalScope.launch {
                    database.personDao().insert(list)
                }
                val intent: Intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        /*binding.btnDatePicker.setOnClickListener {
            showDatePicker()
        }
        binding.prevDate.setOnClickListener {
            showDatePicker()
        }*/
        binding.tvHaveAcc.setOnClickListener {
            startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
        }
    }

    /*private fun showDatePicker() {
        val cal: Calendar = Calendar.getInstance()
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val month = cal.get(Calendar.MONTH)
        val year = cal.get(Calendar.YEAR)
        val dialog: DatePickerDialog = DatePickerDialog(this, this, year, month, day)
        dialog.datePicker.maxDate = System.currentTimeMillis()
        return dialog.show()
    }*/

    /*@RequiresApi(Build.VERSION_CODES.O)
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        binding.tvDate.text = "$dayOfMonth/${month.plus(1)}/$year"
        age = Period.between(
            LocalDate.of(year, month + 1, dayOfMonth),
            LocalDate.now()
        ).years
        binding.prevDate.text = age.toString() + "years"
    }*/
}