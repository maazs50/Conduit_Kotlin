package io.realworld.api

import io.realworld.api.models.entity.UserCred
import io.realworld.api.models.requests.SignupRequest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import kotlin.math.sign
import kotlin.random.Random

class ConduitClientTest {
    private val conduitClient = ConduitClient()

    @Test
    fun `GET ARTICLES`() {
        runBlocking {
            val articles = conduitClient.api.getArticles()
            assertNotNull(articles.body()?.articles)
        }
    }

    @Test
    fun `GET ARTICLES BY AUTHOR`() {
        runBlocking {
            val articles = conduitClient.api.getArticles(author = "Gerome")
            assertNotNull(articles.body()?.articles)
        }
    }

    @Test
    fun `GET ARTICLES BY TAG`() {
        runBlocking {
            val articles = conduitClient.api.getArticles(tag = "welcome")
            assertNotNull(articles.body()?.articles)
        }
    }

    @Test
    fun `GET ARTICLES BY FAVORITED`() {
        runBlocking {
            val articles = conduitClient.api.getArticles(favorited = "maazm622@gmail.com")
            assertNotNull(articles.body()?.articles)
        }
    }

    //Create user
    @Test
    fun `POST USER-create user`() {
        runBlocking {
            val signup = UserCred(
                "ui${Random.nextInt(65, 6500)}@jhik.com",
                "uipass${Random.nextInt(70, 9886)}",
                "ui${Random.nextInt(65, 6500)}@jhik.com",
            )
            val user = conduitClient.api.signupUser(SignupRequest(signup))
            assertEquals(signup.username, user.body()?.user?.username)
        }
    }
}