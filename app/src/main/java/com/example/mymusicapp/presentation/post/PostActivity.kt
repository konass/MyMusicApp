package com.example.mymusicapp.presentation.post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mymusicapp.MainActivity
import com.example.mymusicapp.R
import com.example.mymusicapp.databinding.ActivityPostBinding
import com.example.mymusicapp.domain.models.Post
import com.example.mymusicapp.presentation.authentication.AuthenticationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_post.*
@AndroidEntryPoint
class PostActivity : AppCompatActivity() {
    private val viewModel by viewModels <PostViewModel>()
    lateinit var binding : ActivityPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btn_save.setOnClickListener {
            val text = ed_post.text.toString()
            viewModel.setPostData("",null, text, 0,  null,null, null,null)
            val intent = Intent(this@PostActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

}