package io.realworld.condiut

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import io.realworld.api.models.entity.User
import io.realworld.condiut.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object{
        const val PREFS_AUTH = "prefs_auth"
        const val PREFS_KEY_TOKEN = "prefs_auth_token"
    }
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var authViewModel: AuthViewModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences(PREFS_AUTH, Context.MODE_PRIVATE)
        setSupportActionBar(binding.appBarMain.toolbar)
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_global_feed,
                R.id.nav_my_feed,
                R.id.nav_auth
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        sharedPreferences.getString(PREFS_KEY_TOKEN,null)?.let {
            authViewModel.getCurrentUser(it)
        }
        authViewModel.user.observe({lifecycle}){
            updateMenu(it)
            //After login goes to the feed screen
            it?.token?.let { t ->
                sharedPreferences.edit{
                    putString(PREFS_KEY_TOKEN,t)
                }
            } ?: run{
                //If the above condition is null
                Toast.makeText(this,"Logged out",Toast.LENGTH_SHORT).show()
                sharedPreferences.edit{
                    remove(PREFS_KEY_TOKEN)
                }
            }
            navController.navigateUp()
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
    }
    private fun updateMenu(user: User?){
        binding.navView.menu.clear()
        when(user){
            is User ->{
                binding.navView.inflateMenu(R.menu.menu_main_user)
            }
            else ->{
                binding.navView.inflateMenu(R.menu.menu_main_guest)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_logout ->{
                authViewModel.logout()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}