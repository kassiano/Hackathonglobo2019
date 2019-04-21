package br.globo.com.hackathonglobo2019.application

import android.app.Application
import br.globo.com.hackathonglobo2019.network.initRetrofit

class GloboEsporte :Application() {

    override fun onCreate(){
        super.onCreate()
        initRetrofit(this)
    }
}