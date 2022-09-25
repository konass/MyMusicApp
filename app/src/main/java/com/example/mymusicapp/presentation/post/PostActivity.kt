package com.example.mymusicapp.presentation.post

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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
    private var imageUri: Uri?= null
    lateinit var resultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btn_save.setOnClickListener {
            val text = ed_post.text.toString()
            viewModel.setPostData(imageUri.toString(),null, text, 0,  null,null, null,null)
            val intent = Intent(this@PostActivity, MainActivity::class.java)
            startActivity(intent)
        }
im_btn_add.setOnClickListener {

    Intent(Intent.ACTION_GET_CONTENT).also {
        it.type = "image/*"
        resultLauncher.launch(it)
    }
}
    resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            imageUri = result.data?.data
            viewModel.uploadImage(imageUri!!)
            binding.imBtnAdd.setImageURI(imageUri)
            binding.imBtnAdd.scaleType=ImageView.ScaleType.CENTER_CROP
        }
    }
}
}