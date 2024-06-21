package com.bagibagi.app.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagibagi.app.data.pref.UserPreference
import com.bagibagi.app.data.repo.UserRepository
import com.bagibagi.app.databinding.FragmentHomeBinding
import com.bagibagi.app.helper.showSnackbar
import com.bagibagi.app.ui.ViewModelFactory
import com.bagibagi.app.ui.search.SearchActivity

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUILogic()

    }
    private fun setUILogic() {
        // Fetch data
        viewModel.getUserDetailDashboard()
        viewModel.getRecommendedItems()
        viewModel.getOrganizations()

        with(binding) {
            searchview.setupWithSearchBar(searchBar)

            searchview.editText.setOnEditorActionListener { v, actionId, event ->
                val query = searchview.text.toString().trim()
                val intent = Intent(requireContext(), SearchActivity::class.java)
                intent.putExtra(EXTRA_QUERY, query)
                startActivity(intent)
                false
            }

            viewModel.userDetail.observe(viewLifecycleOwner, Observer { userDetails ->
                userDetails?.let {
                    val userDetail = it.firstOrNull()
                    donateCounter.text = userDetail?.sukses_donasi?.toString() ?: "null"
                    barterCounter.text = userDetail?.sukses_barter?.toString() ?: "null"
                }
            })
            val recommendationAdapter = ItemAdapter()
            viewModel.recommendedItems.observe(viewLifecycleOwner, Observer { items ->
                items?.let { recommendationAdapter.submitList(it) }
                rvRecommendations.apply {
                    layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    setHasFixedSize(true)
                    adapter = recommendationAdapter
                }
            })
            val organizationAdapter = OrganizationAdapter()
            viewModel.organizations.observe(viewLifecycleOwner, Observer { orgs ->
                orgs?.let { organizationAdapter.submitList(it) }
                rvOrganizations.apply {
                    layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    setHasFixedSize(true)
                    adapter = organizationAdapter
                }
                Log.d("HomeFragment", "org: $orgs")
            })
            viewModel.error.observe(viewLifecycleOwner, Observer { error ->
                error?.let {
                    Log.e("HomeFragment", "error: $it")
                    showSnackbar(binding.root, it)
                }
            })
        }
    }
    companion object{
        const val EXTRA_QUERY = "extra_query"
    }
}
