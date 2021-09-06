package br.dev.aramizu.tmdb.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.dev.aramizu.domain.features.movie.list.GetMovieListUseCase
import br.dev.aramizu.tmdb.commons.SingleLiveEvent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(private val getMovieListUseCase: GetMovieListUseCase) : ViewModel() {

    val viewState = MutableLiveData<ViewState>()
    val actionState = SingleLiveEvent<ActionState>()
    private val compositeDisposable = CompositeDisposable()

    init {
        get()
    }

    private fun get() = getMovieListUseCase.getNowPlaying()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { movie ->

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