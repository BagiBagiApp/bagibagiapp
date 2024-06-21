package com.bagibagi.app.ui.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagibagi.app.R
import com.bagibagi.app.databinding.FragmentNotificationsBinding
import com.bagibagi.app.helper.showSnackbar
import com.bagibagi.app.ui.ViewModelFactory
import com.bagibagi.app.ui.profile.ProfileViewModel

class NotificationsFragment : Fragment() {

    private lateinit var binding : FragmentNotificationsBinding

    private val viewModel by viewModels<NotificationViewModel>(){
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI()
    }

    private fun setUI(){
        viewModel.getAllNotification()

        viewModel.notificationItem.observe(viewLifecycleOwner, Observer {
            val notifAdapter = NotificationAdapter()
            binding.rvNotif.apply {
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                notifAdapter.submitList(it)
                adapter = notifAdapter

            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            showSnackbar(binding.root,"Error: $it")
        })
    }
}