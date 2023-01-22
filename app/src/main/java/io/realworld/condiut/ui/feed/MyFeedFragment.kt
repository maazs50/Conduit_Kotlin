package io.realworld.condiut.ui.feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.realworld.condiut.R
import io.realworld.condiut.databinding.FragmentGlobalFeedBinding
import io.realworld.condiut.databinding.FragmentMyFeedBinding

class MyFeedFragment : Fragment() {

    private var _binding: FragmentMyFeedBinding? = null
    private lateinit var viewModel: FeedViewModel
    private lateinit var feedAdapter: ArticleFeedAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMyFeedBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        setupRecyclerView()

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchMyFeed()
        viewModel.feed.observe({lifecycle}){
            feedAdapter.submitList(it)
        }
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