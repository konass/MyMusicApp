package com.example.mymusicapp.presentation.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.mymusicapp.MainActivity
import com.example.mymusicapp.R
import com.example.mymusicapp.databinding.ActivityMainBinding
import com.example.mymusicapp.databinding.ActivitySignUpBinding
import com.example.mymusicapp.utils.Response
import com.google.android.material.progressindicator.CircularProgressIndicator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.ed_email as ed_email1
import kotlinx.android.synthetic.main.activity_sign_up.ed_password as ed_password1

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    private val viewModel by viewModels <AuthenticationViewModel>()
    lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btn_signUp.setOnClickListener {
            val name = ed_name.text.toString()
            val lastName = ed_last_name.text.toString()
            val email = ed_email.text.toString()
            val password = ed_password.text.toString()
            if (email.isNotEmpty() && name.isNotEmpty() && lastName.isNotEmpty() && password.isNotEmpty()) {
                lifecycleScope.launchWhenStarted {
                    viewModel.signUp(email, password, name, lastName)
                    val intent = Intent(this@SignUpActivity, MainActivity::class.java)
                    intent.putExtra("UserId","${viewModel.getCurrentUserId}")
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
                        Toast.makeText(this, "Sign up failed", Toast.LENGTH_LONG).show()
                    }
                }
                is Response.Error -> {
                    Toast.makeText(this, resource.message, Toast.LENGTH_LONG).show()
                }

            }
        }

    }
    fun backToSignIn (view: View){
        val intent = Intent(this@SignUpActivity,LoginActivity::class.java)
        startActivity(intent)
    }

}