package br.dev.aramizu.tmdb.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import br.dev.aramizu.domain.features.movie.list.GetMovieListUseCase
import br.dev.aramizu.tmdb.databinding.ActivityMainBinding
import br.dev.aramizu.tmdb.home.viewmodel.ActionState
import br.dev.aramizu.tmdb.home.viewmodel.HomeViewModel
import br.dev.aramizu.tmdb.home.viewmodel.ViewState
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding?.let { setContentView(it.root) }

        setUpObservation()
    }

    private fun setUpObservation() {
        viewModel.viewState.observe(this, Observer { state ->
            when (state) {
                is ViewState.ShowContent -> {
                    binding?.textViewGreeting?.text = state.content
                    binding?.textViewGreeting?.setOnClickListener {
                        viewModel.action()
                    }
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
}