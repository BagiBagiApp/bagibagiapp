package com.bagibagi.app.ui.signup

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bagibagi.app.R
import com.bagibagi.app.databinding.ActivitySignupBinding
import com.bagibagi.app.ui.login.LoginActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SignupActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUILogic()
    }
    private fun setUILogic(){
        binding.progressIndicator.visibility = View.INVISIBLE

        val gender = resources.getStringArray(R.array.jenis_kelamin)
        val arrayAdapter = ArrayAdapter(this,R.layout.dropdown_item,gender)
        binding.txtJenisKelamin.setAdapter(arrayAdapter)

        val myCalendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR,year)
            myCalendar.set(Calendar.MONTH,month)
            myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            val myFormat = "yyyy-MM-dd"
            val sdf = SimpleDateFormat(myFormat,Locale.getDefault())
            binding.txtTglLahir.setText(sdf.format(myCalendar.time))
        }
        binding.inputLayoutTglLahir.setEndIconOnClickListener { DatePickerDialog(this,datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show() }
        binding.txtLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}