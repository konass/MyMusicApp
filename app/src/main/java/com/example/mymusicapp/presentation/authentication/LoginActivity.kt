package com.example.mymusicapp.presentation.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mymusicapp.MainActivity
import com.example.mymusicapp.R
import com.example.mymusicapp.databinding.ActivityLoginBinding
import com.example.mymusicapp.databinding.ActivitySignUpBinding
import com.example.mymusicapp.utils.Response
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val viewModel by viewModels <AuthenticationViewModel>()
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btn_signIn.setOnClickListener{
            val email = ed_email.text.toString()
            val password = ed_password.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                lifecycleScope.launchWhenStarted {
                    viewModel.signIn(email, password)
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            } else {
                Toast.makeText(this, "Please enter all the data", Toast.LENGTH_LONG).show()
            }
            when (val resource = viewModel.signUpState.value) {
                is Response.Loading -> {
                }
                is Response.Success -> {
                    if (resource.data) {

                    } else {
                        Toast.makeText(this, "Sign in failed", Toast.LENGTH_LONG).show()
                    }
                }
                is Response.Error -> {
                    Toast.makeText(this, resource.message, Toast.LENGTH_LONG).show()
                }

            }
        }
    }
    fun backToSignUp(view: View){
        val intent = Intent(this@LoginActivity,SignUpActivity::class.java)
        startActivity(intent)
    }
}