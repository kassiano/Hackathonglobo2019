package br.globo.com.hackathonglobo2019.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.globo.com.hackathonglobo2019.domain.GetPlayer
import br.globo.com.hackathonglobo2019.domain.GetScore
import br.globo.com.hackathonglobo2019.domain.GetStat
import br.globo.com.hackathonglobo2019.model.Player
import br.globo.com.hackathonglobo2019.model.Score
import br.globo.com.hackathonglobo2019.model.Stat
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class PlayerViewModel: ViewModel() {

    val disposables = CompositeDisposable()
    val player = MutableLiveData<Player>()
    val score = MutableLiveData<Score>()
    val stats = MutableLiveData<Stat>()


    val error = MutableLiveData<String>()

    fun fetchPlayer(name:String){

        GetPlayer().execute()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    player.value = it
                },
                {
                    error.value = "Erro ao buscar dados do jogador."
                }
            ).addTo(disposables)

    }


    fun fetchScore(){
        GetScore().execute()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    score.value = it
                },
                {
                    error.value = "Erro ao buscar score."
                }
            ).addTo(disposables)
    }

    fun fetchStats(){
        GetStat().execute()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    stats.value = it
                },
                {
                    error.value = "Erro ao buscar score."
                }
            ).addTo(disposables)
    }


    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}