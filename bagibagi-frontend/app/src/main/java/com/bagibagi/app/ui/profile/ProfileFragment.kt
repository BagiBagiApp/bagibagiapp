package com.bagibagi.app.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.bagibagi.app.R
import com.bagibagi.app.databinding.FragmentProfileBinding
import com.bagibagi.app.ui.ViewModelFactory
import com.bagibagi.app.ui.welcome.WelcomeActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding

    private val viewModel by viewModels<ProfileViewModel>(){
        ViewModelFactory.refreshInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUILogic()
        getUserData()
    }
    private fun setUILogic(){
        with(binding){
            btnLogoutProfile.setOnClickListener {
                lifecycleScope.launch{
                    viewModel.logout()
                    val intent = Intent(requireContext(),WelcomeActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                }
            }
        }
    }
    private fun getUserData(){
        viewModel.getUserDetail()

        viewModel.getUserItem()

        viewModel.userDetail.observe(viewLifecycleOwner, Observer { userDetail ->
            userDetail.forEach {
                with(binding){
                    txtUsernameProfile.setText(it.username)
                    txtAddressProfile.setText(it.alamat)
                    txtWhatsappProfile.setText("wa.me/${it.notelp}")
                }
            }
            Log.d("ProfileFragment", "User details fetched successfully")
        })

        viewModel.userItem.observe(viewLifecycleOwner, Observer {
            binding.rvUserItem.apply {
                layoutManager = GridLayoutManager(requireContext(),2)
                setHasFixedSize(true)
                val userItemAdapter = UserProductAdapter()
                userItemAdapter.submitList(it)
                adapter = userItemAdapter
            }
        })
        viewModel.error.observe(viewLifecycleOwner, Observer {
            Log.e("ProfileFragment", "Error: $it")
            Snackbar.make(binding.root, "Error: $it", Snackbar.LENGTH_LONG).show()
        })
    }
}