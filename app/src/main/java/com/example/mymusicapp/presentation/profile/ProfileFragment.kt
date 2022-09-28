package com.example.mymusicapp.presentation.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mymusicapp.R
import com.example.mymusicapp.databinding.FragmentProfileBinding
import com.example.mymusicapp.domain.models.User
import com.example.mymusicapp.presentation.home.HomeViewModel
import com.example.mymusicapp.utils.Response
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile.*

@AndroidEntryPoint
class ProfileFragment : Fragment() {
private var user: User? = null
    private val viewModel by viewModels<ProfileViewModel>()
    lateinit var binding : FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater,  container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCurrentUser()
        lifecycleScope.launchWhenStarted {
            viewModel.getUserState.collect{
            when (it){
                is Response.Success->{
                    user= it.data
                    tv_name.text=user?.name.toString()
                    tv_last_name.text= user?.lastName.toString()
                }
                is Response.Error -> {

                    Snackbar.make(
                        requireView(), "error" + it.message, Snackbar.LENGTH_LONG
                    ).show()

                }
                is Response.Loading -> {
                    Log.e("ProfileFragment", "loading" )
                }
            }
            }
        }
    }

}