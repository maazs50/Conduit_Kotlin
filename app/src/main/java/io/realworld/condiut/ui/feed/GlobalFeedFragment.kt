package io.realworld.condiut.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.realworld.condiut.databinding.FragmentGlobalFeedBinding

class GlobalFeedFragment: Fragment() {

    private var _binding: FragmentGlobalFeedBinding? = null
    private lateinit var viewModel: FeedViewModel
    private lateinit var feedAdapter: ArticleFeedAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGlobalFeedBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        setupRecyclerView()
        viewModel.fetchGlobalFeed()
        viewModel.feed.observe({lifecycle}){
            feedAdapter.submitList(it)
        }
        return _binding?.root
    }


    fun setupRecyclerView(){
        feedAdapter = ArticleFeedAdapter()
        _binding?.rvFeed?.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = feedAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}