package com.example.mymusicapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mymusicapp.databinding.ActivityMainBinding
import com.example.mymusicapp.presentation.authentication.LoginActivity
import com.example.mymusicapp.presentation.authentication.SignUpActivity
import com.example.mymusicapp.presentation.post.PostActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            bottom_nav.setupWithNavController(
                navController = nav_host_fragment.findNavController()
            )
        val userId=intent.getStringExtra("Username")
addPost.setOnClickListener {
    val intent = Intent(this@MainActivity, PostActivity::class.java)
    intent.putExtra("UserId","$userId")
    startActivity(intent)
}
    }
}