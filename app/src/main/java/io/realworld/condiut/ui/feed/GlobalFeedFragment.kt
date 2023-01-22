package io.realworld.condiut.ui.feed

import android.os.Bundle
import android.telephony.ims.RcsUceAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import io.realworld.condiut.R
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
        feedAdapter = ArticleFeedAdapter(object : ArticleFeedAdapter.OnArticleClickedListener{
            override fun onArticleClicked(slug:String) = openArticle(slug)

        })
        _binding?.rvFeed?.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = feedAdapter
        }
    }

    fun openArticle(articleId:String){
        findNavController().navigate(
            R.id.action_nav_global_feed_to_nav_article,
            bundleOf(getString(R.string.article_id) to articleId)
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}