package io.realworld.api

import org.junit.Assert.assertNotNull
import org.junit.Test

class ConduitClientTest {
    private val conduitClient = ConduitClient()
    @Test
    fun `GET ARTICLES`(){
        val articles = conduitClient.api.articles().execute()
        assertNotNull(articles.body()?.articles)
    }
}