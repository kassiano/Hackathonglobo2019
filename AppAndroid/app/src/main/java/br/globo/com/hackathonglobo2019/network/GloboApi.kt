package br.globo.com.hackathonglobo2019.network

import br.globo.com.hackathonglobo2019.model.Score
import br.globo.com.hackathonglobo2019.model.Stat
import io.reactivex.Observable
import retrofit2.http.GET

interface GloboApi {

    @GET("stat")
    fun getStat(): Observable<Stat>

    @GET("media-stat")
    fun getMediaStat():Observable<Stat>

    @GET("pontuation")
    fun getScore():Observable<Score>

}


