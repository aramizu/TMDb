package br.dev.aramizu.tmdb.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.dev.aramizu.tmdb.R
import br.dev.aramizu.tmdb.databinding.ActivityMainBinding
import br.dev.aramizu.tmdb.home.viewmodel.ActionState
import br.dev.aramizu.tmdb.home.viewmodel.HomeViewModel
import br.dev.aramizu.tmdb.home.viewmodel.ViewState
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val viewModel: HomeViewModel by viewModel()

    private val nowPlayingMoviesFragment = NowPlayingMoviesFragment()
    private val searchMoviesFragment = SearchMoviesFragment()
    private val favouritesMoviesFragment = FavouritesMoviesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding?.let { setContentView(it.root) }

        setUpObservation()
        setUpBottomNavigation()
        switchFragment(nowPlayingMoviesFragment)
    }

    private fun setUpBottomNavigation() {
        binding?.bottomNavigation?.let {
            it.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_now_playing -> {
                        switchFragment(nowPlayingMoviesFragment)
                        true
                    }
                    R.id.nav_search -> {
                        switchFragment(searchMoviesFragment)
                        true
                    }
                    R.id.nav_favourites -> {
                        switchFragment(favouritesMoviesFragment)
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations( android.R.anim.fade_in, android.R.anim.fade_out )
            .replace(R.id.fragment_container, fragment, fragment.tag)
            .commit()
    }

    private fun setUpObservation() {
        viewModel.viewState.observe(this, Observer { state ->
            when (state) {
                is ViewState.ShowContent -> {

                }
            }
        })

        viewModel.actionState.observe(this, Observer { state ->
            when (state) {
                ActionState.ShowToast -> Toast.makeText(this, "Clickou", Toast.LENGTH_SHORT).show()
                else -> TODO()
            }
        })
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}