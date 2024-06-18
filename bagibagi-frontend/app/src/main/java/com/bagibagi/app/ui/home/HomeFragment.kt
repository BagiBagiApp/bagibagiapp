package com.bagibagi.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagibagi.app.databinding.FragmentHomeBinding
import com.bagibagi.app.helper.showSnackbar
import com.bagibagi.app.ui.ViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUILogic()
    }

    private fun setUILogic(){

        viewModel.getUserDetailDashboard()
        viewModel.getRecommendedItems()
        viewModel.getOrganizations()

        with(binding){
            viewModel.userDetail.observe(viewLifecycleOwner) {
                it.map {
                    donateCounter.text = it.sukses_donasi.toString()
                    barterCounter.text = it.sukses_barter.toString()
                }
            }
            rvRecommendations.apply {
                layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                setHasFixedSize(true)
                val recommendationAdapter = ItemAdapter()
                viewModel.recommendedItems.observe(viewLifecycleOwner){ recommendationAdapter.submitList(it) }
                adapter = recommendationAdapter
            }
            rvOrganizations.apply {
                layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                setHasFixedSize(true)
                val organizationAdapter = OrganizationAdapter()
                viewModel.organizations.observe(viewLifecycleOwner){ organizationAdapter.submitList(it) }
                adapter = organizationAdapter
            }
            viewModel.error.observe(viewLifecycleOwner){ showSnackbar(binding.root,it) }

        }
    }
}