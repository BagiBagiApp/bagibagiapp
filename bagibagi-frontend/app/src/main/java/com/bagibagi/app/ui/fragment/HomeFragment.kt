package com.bagibagi.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bagibagi.app.R
import com.bagibagi.app.ui.adapter.RecommendedItem
import com.bagibagi.app.ui.adapter.RecommendedItemsAdapter
class HomeFragment : Fragment() {

    private lateinit var recommendedItemsRecyclerView: RecyclerView
    private lateinit var donationItemsRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize RecyclerViews and BottomNavigationView
        recommendedItemsRecyclerView = view.findViewById(R.id.recommended_items)
        donationItemsRecyclerView = view.findViewById(R.id.donation_items)

        // Setup RecyclerViews
        setupRecyclerViews()

        return view
    }

    private fun setupRecyclerViews() {
        // Setup layout managers for RecyclerViews
        recommendedItemsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        donationItemsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        // Set adapters for RecyclerViews (you need to implement these adapters)
        recommendedItemsRecyclerView.adapter = RecommendedItemsAdapter(getRecommendedItems())
        donationItemsRecyclerView.adapter = DonationItemsAdapter(getDonationItems())
    }

    // Dummy data functions (replace with real data fetching logic)
    private fun getRecommendedItems(): List<RecommendedItem> {
        // Replace with actual data fetching logic
        return listOf(
            RecommendedItem("Dr. Martens 1461 Blacksmooth", 1),
            RecommendedItem("Dr. Martens 1461 Blacksmooth", 1),
            RecommendedItem("Dr. Martens 1461 Blacksmooth", 1)
        )
    }

    private fun getDonationItems(): List<DonationItem> {
        // Replace with actual data fetching logic
        return listOf(
            DonationItem("Yayasan Cinta Kasih", "Depok, Jawa Barat"),
            DonationItem("Yayasan Cinta Kasih", "Depok, Jawa Barat")
        )
    }
}
