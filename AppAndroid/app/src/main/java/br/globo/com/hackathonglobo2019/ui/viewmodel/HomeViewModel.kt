package br.globo.com.hackathonglobo2019.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.globo.com.hackathonglobo2019.domain.GetNews
import br.globo.com.hackathonglobo2019.model.News
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class HomeViewModel: ViewModel() {

    val disposables = CompositeDisposable()
    val news = MutableLiveData<List<News>>()
    val error = MutableLiveData<String>()

    fun fetchNews(){

        GetNews().execute()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    news.value = it
                },
                {
                      error.value = "Erro ao acessar API"
                }
            ).addTo(disposables)

    }


    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}