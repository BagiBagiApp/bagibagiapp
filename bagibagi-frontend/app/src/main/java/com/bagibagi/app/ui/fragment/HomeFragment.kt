package com.bagibagi.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bagibagi.app.R
import com.bagibagi.app.ui.adapter.RecommendationAdapter
import com.bagibagi.app.ui.adapter.OrganizationAdapter
import com.bagibagi.app.data.api.ApiConfig
import com.bagibagi.app.data.model.OrganizationModel
import com.bagibagi.app.data.model.RecommendationItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var recommendationRecyclerView: RecyclerView
    private lateinit var organizationRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        /*
        Set up recommendation RecyclerView
        recommendationRecyclerView = root.findViewById(R.id.recommendation_recycler_view)
        recommendationRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        // Set up organization RecyclerView
        organizationRecyclerView = root.findViewById(R.id.organization_recycler_view)
        organizationRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        Fetch data from API
        fetchData()
        */

        return root
    }

    /*
    private fun fetchData() {
    val apiService = ApiConfig.getApiService()

    // Fetch recommendations
    apiService.getRecommendations().enqueue(object : Callback<List<RecommendationItem>> {
    override fun onResponse(call: Call<List<RecommendationItem>>, response: Response<List<RecommendationItem>>) {
    if (response.isSuccessful) {
    val recommendations = response.body() ?: emptyList()
    recommendationRecyclerView.adapter = RecommendationAdapter(recommendations)
    }
    }

    override fun onFailure(call: Call<List<RecommendationItem>>, t: Throwable) {
    // Handle API call failure
    }
    })

    // Fetch organizations
    apiService.getOrganizations().enqueue(object : Callback<List<OrganizationModel>> {
    override fun onResponse(call: Call<List<OrganizationModel>>, response: Response<List<OrganizationModel>>) {
    if (response.isSuccessful) {
    val organizations = response.body() ?: emptyList()
    organizationRecyclerView.adapter = OrganizationAdapter(organizations)
    }
    }

    override fun onFailure(call: Call<List<OrganizationModel>>, t: Throwable) {
    // Handle API call failure
    }
    })
    }
    */
}