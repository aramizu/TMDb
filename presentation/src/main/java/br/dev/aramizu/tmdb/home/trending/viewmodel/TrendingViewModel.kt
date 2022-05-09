package br.dev.aramizu.tmdb.home.trending.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.dev.aramizu.domain.features.movie.list.GetTrendClassificationUseCase
import br.dev.aramizu.domain.features.movie.list.GetTrendingMovieListUseCase
import br.dev.aramizu.domain.features.movie.list.enums.TrendType
import br.dev.aramizu.tmdb.commons.SingleLiveEvent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class TrendingViewModel(
    private val getTrendingMovieListUseCase: GetTrendingMovieListUseCase,
    private val getTrendClassificationUseCase: GetTrendClassificationUseCase
) : ViewModel() {

    val viewState = MutableLiveData<ViewState>()
    val actionState = SingleLiveEvent<ActionState>()
    private val compositeDisposable = CompositeDisposable()

    init {
        getTrendList()
        getMovieList(TrendType.NOW_PLAYING)
    }

    private fun getTrendList() {
        viewState.value = ViewState.ShowTrendTypes(
            getTrendClassificationUseCase()
        )
    }

    fun getMovieList(type: TrendType) = getTrendingMovieListUseCase(type.value)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { movies ->
                viewState.value = ViewState.ShowMovies(movies)
            },
            { e ->
                e.printStackTrace()
            }
        ).apply { compositeDisposable.add(this) }

    fun action() {
        actionState.value = ActionState.ShowToast
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}