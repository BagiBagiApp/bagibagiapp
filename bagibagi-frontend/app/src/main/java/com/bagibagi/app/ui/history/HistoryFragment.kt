package com.bagibagi.app.ui.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagibagi.app.R
import com.bagibagi.app.databinding.FragmentHistoryBinding
import com.bagibagi.app.helper.showSnackbar
import com.bagibagi.app.ui.ViewModelFactory
import com.bagibagi.app.ui.notification.NotificationAdapter
import com.bagibagi.app.ui.notification.NotificationViewModel

class HistoryFragment : Fragment() {

    private lateinit var binding : FragmentHistoryBinding

    private val viewModel by viewModels<HistoryViewModel>(){
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUI()
    }

    private fun setUI(){
        viewModel.getAllHistory()

        viewModel.historyItem.observe(viewLifecycleOwner, Observer {
            val historyAdapter = HistoryAdapter()
            binding.rvHistory.apply {
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                historyAdapter.submitList(it)
                adapter = historyAdapter

            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            showSnackbar(binding.root,"Error: $it")
        })
    }
}