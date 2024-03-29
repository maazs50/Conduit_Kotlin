package io.realworld.condiut.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.realworld.api.ConduitClient
import io.realworld.api.models.entity.Article
import kotlinx.coroutines.launch

class ArtileViewModel: ViewModel() {
    val api = ConduitClient.publicApi

    private val _article = MutableLiveData<Article>()
    val article: LiveData<Article> = _article
    fun fetchArtile(slug:String) = viewModelScope.launch {
        val response = api.getArticlesBySlug(slug)
        response.body()?.article.let { 
            _article.postValue(it)
        }
    }
}