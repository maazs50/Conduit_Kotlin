package io.realworld.condiut.ui.feed

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.realworld.api.models.entity.Article
import io.realworld.condiut.databinding.ListItemArticleBinding

class ArticleFeedAdapter(val listener: OnArticleClickedListener) : ListAdapter<Article, ArticleFeedAdapter.ArticleViewHolder>(object : DiffUtil.ItemCallback<Article>(){
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.toString() == newItem.toString()
    }
}) {

    interface OnArticleClickedListener{
        fun onArticleClicked(slug:String)
    }
    inner class ArticleViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding =  ListItemArticleBinding.inflate(parent.context.getSystemService(LayoutInflater::class.java),parent,false)
        return ArticleViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        ListItemArticleBinding.bind(holder.itemView).apply {
            val article = getItem(position)
            authorTextView.text = article.author.username
            titleTextView.text = article.title
            bodySnippetTextView.text = article.body
            dateTextView.text = "December 2020"//TODO date has to be implemented
            avtarImageView.background = ColorDrawable(Color.GRAY)
            root.setOnClickListener {
                listener.onArticleClicked(article.slug)
            }
        }

    }
}