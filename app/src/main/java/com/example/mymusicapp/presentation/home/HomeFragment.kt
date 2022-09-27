package com.example.mymusicapp.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymusicapp.R
import com.example.mymusicapp.databinding.FragmentHomeBinding
import com.example.mymusicapp.domain.models.Post
import com.example.mymusicapp.presentation.apadters.PostAdapter
import com.example.mymusicapp.utils.Response
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var postAdapter: PostAdapter
    private lateinit var postList : List<Post?>
    private val viewModel by viewModels<HomeViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPosts()
        Log.e("HomeFragment", "im in home" )
        initPostAdapter()
        lifecycleScope.launchWhenStarted {
            viewModel.getPostState.collect{
                when(it){
                    is Response.Success -> {
                        Log.e("HomeFragment", ""+ it.data.size)
                        postList = it.data
                        postAdapter.differ.submitList(postList)
                    }
                    is Response.Error -> {

                        Snackbar.make(
                            requireView(), "error" + it.message, Snackbar.LENGTH_LONG
                        ).show()

                    }
                    is Response.Loading -> {
                        Log.e("HomeFragment", "loading" )
                    }
                }

            }
        }
    }
    private fun initPostAdapter() {
        postAdapter = PostAdapter()
     rv_posts.apply {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }
    }
}