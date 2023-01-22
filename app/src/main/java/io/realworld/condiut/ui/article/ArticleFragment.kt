package io.realworld.condiut.ui.article

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import io.realworld.condiut.R
import io.realworld.condiut.databinding.FragmentArticleBinding

class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    lateinit var articleViewModel: ArtileViewModel
    private var articleId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentArticleBinding.inflate(inflater,container,false)
        articleViewModel = ViewModelProvider(this).get(ArtileViewModel::class.java)
        articleId = arguments?.getString(getString(R.string.article_id))
        articleId?.let {
            articleViewModel.fetchArtile(it)
        }
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(this.requireContext(),"Opening article $articleId",Toast.LENGTH_SHORT).show()
        articleViewModel.article.observe({lifecycle}) {
            _binding?.apply {
                titleTextView.text = it.title
                bodyTextView.text = it.body
                usernameTextView.text = it.author.username
                dateTextView.text = it.createdAt
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}