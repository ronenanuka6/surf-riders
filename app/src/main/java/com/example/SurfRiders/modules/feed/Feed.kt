package com.example.SurfRiders.modules.feed


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.SurfRiders.databinding.FragmentCocktailsFeedBinding
import com.example.SurfRiders.data.review.ReviewModel

class Feed : Fragment() {

        private var reviewsRecyclerView: RecyclerView? = null
        private var adapter: FeedRecycleAdapter? = null
        private var _binding: FragmentCocktailsFeedBinding? = null
        private val binding get() = _binding!!
        private lateinit var viewModel: CocktailsFeedViewModel

        @SuppressLint("NotifyDataSetChanged")
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            _binding = FragmentCocktailsFeedBinding.inflate(inflater, container, false)
            val view = binding.root

            viewModel = ViewModelProvider(this)[CocktailsFeedViewModel::class.java]

            reviewsRecyclerView = binding.Feed
            reviewsRecyclerView?.setHasFixedSize(true)
            reviewsRecyclerView?.layoutManager = LinearLayoutManager(context)
            adapter = FeedRecycleAdapter(viewModel.reviews.value, viewModel.users.value)

            reviewsRecyclerView?.adapter = adapter

            viewModel.reviews.observe(viewLifecycleOwner) {
                adapter?.reviews = it
                adapter?.notifyDataSetChanged()
            }

            viewModel.users.observe(viewLifecycleOwner) {
                adapter?.users = it
                adapter?.notifyDataSetChanged()
            }

            binding.pullToRefresh.setOnRefreshListener {
                viewModel.reloadData()
            }

            viewModel.reviewsListLoadingState.observe(viewLifecycleOwner) { state ->
                binding.pullToRefresh.isRefreshing = state == ReviewModel.LoadingState.LOADING
            }

            return view
        }


        override fun onResume() {
            super.onResume()
            viewModel.reloadData()
        }


        override fun onDestroy() {
            super.onDestroy()
            _binding = null
        }
    }
