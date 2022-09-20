package com.example.mymusicapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mymusicapp.R
import com.example.mymusicapp.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.activity_main.*


class HomeFragment : Fragment() {
  lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(layoutInflater, container,false)
        return binding.root
addPost.setOnClickListener{

}
    }


}