package com.bagibagi.app.ui.signup

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bagibagi.app.R
import com.bagibagi.app.databinding.ActivitySignupBinding
import com.bagibagi.app.ui.ViewModelFactory
import com.bagibagi.app.ui.login.LoginActivity
import com.bagibagi.app.ui.welcome.WelcomeViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SignupActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignupBinding

    private val viewModel by viewModels<SignupViewModel>(){
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUILogic()
    }
    private fun setUILogic(){
        binding.progressIndicator.visibility = View.INVISIBLE

        with(binding){
            btnSignupActivity.setOnClickListener {
                val fullname = txtUsername.text.toString()
                val password = txtPassword.text.toString()
                val alamat = txtAlamat.text.toString()
                val noTelp = txtNoHp.text.toString()
                val email = txtEmail.text.toString()
                val tglLahir = txtTglLahir.text.toString()
                val jenisKelamin = txtJenisKelamin.text.toString()

                progressIndicator.visibility = View.VISIBLE

                lifecycleScope.launch {

                    viewModel.signup(
                        fullname,
                        password,
                        alamat,
                        noTelp,
                        email,
                        tglLahir,
                        jenisKelamin
                    ).apply {
                        if (message == "Registration successful.") {
                            Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show()
                            progressIndicator.visibility = View.INVISIBLE
                            startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
                            finish()
                        } else {
                            Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show()
                            progressIndicator.visibility = View.INVISIBLE
                        }
                    }
                }
            }
        }

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