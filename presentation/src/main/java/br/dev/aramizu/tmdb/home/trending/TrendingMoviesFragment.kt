package br.dev.aramizu.tmdb.home.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.dev.aramizu.domain.features.movie.list.enums.TrendType
import br.dev.aramizu.tmdb.databinding.FragmentTrendingBinding
import br.dev.aramizu.tmdb.home.trending.adapter.TrendListAdapter
import br.dev.aramizu.tmdb.home.trending.viewmodel.ActionState
import br.dev.aramizu.tmdb.home.trending.viewmodel.TrendingViewModel
import br.dev.aramizu.tmdb.home.trending.viewmodel.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrendingMoviesFragment : Fragment() {

    private var _binding: FragmentTrendingBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TrendingViewModel by viewModel()
    private val onTrendItemClickListener: (TrendType) -> Unit = { type ->
        viewModel.getMovieList(type)
    }
    private val trendListAdapter = TrendListAdapter(onTrendItemClickListener)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrendingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLists()
        setUpObservation()
    }

    private fun setupLists() {
        binding.recyclerViewTrendList.adapter = trendListAdapter
    }

    private fun setUpObservation() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is ViewState.ShowContent -> {

                }
                is ViewState.ShowTrend -> {
                    trendListAdapter.submitList(state.trendList)
                }
            }
        })

        viewModel.actionState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                ActionState.ShowToast -> TODO()
                else -> TODO()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}