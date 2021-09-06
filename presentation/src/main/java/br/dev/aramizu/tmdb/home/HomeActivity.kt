package br.dev.aramizu.tmdb.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.dev.aramizu.tmdb.R
import br.dev.aramizu.tmdb.databinding.ActivityMainBinding
import br.dev.aramizu.tmdb.home.favourites.FavouritesMoviesFragment
import br.dev.aramizu.tmdb.home.search.SearchMoviesFragment
import br.dev.aramizu.tmdb.home.trending.TrendingMoviesFragment
import br.dev.aramizu.tmdb.home.trending.viewmodel.ActionState
import br.dev.aramizu.tmdb.home.trending.viewmodel.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private val nowPlayingMoviesFragment = TrendingMoviesFragment()
    private val searchMoviesFragment = SearchMoviesFragment()
    private val favouritesMoviesFragment = FavouritesMoviesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding?.let { setContentView(it.root) }

        setUpBottomNavigation()
        switchFragment(nowPlayingMoviesFragment)
    }

    private fun setUpBottomNavigation() {
        binding?.bottomNavigation?.let {
            it.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_trending -> {
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

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}