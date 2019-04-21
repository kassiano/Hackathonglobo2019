package br.globo.com.hackathonglobo2019.network

import android.app.Application
import br.globo.com.hackathonglobo2019.BuildConfig
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


const val API_URL_BASE = "https://globo-hackathon.appspot.com/"

private val logInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

private var retrofitOption: Retrofit? = null


private fun provideHttpCache(application: Application): okhttp3.Cache {
    val cacheSize = 10 * 1024 * 1024
    return okhttp3.Cache(application.cacheDir, cacheSize.toLong())
}

private fun httpClientBuilder(): OkHttpClient.Builder =
    OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) {
             this.addInterceptor(logInterceptor)
        }
    }

private fun getRetrofitBuilderDefaults(urlBase:String) =
    Retrofit.Builder()
        .baseUrl(urlBase)
        .addConverterFactory(MoshiConverterFactory.create())//.asLenient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))

private fun provideOkHttpClientOAuth(cache: Cache) : OkHttpClient = httpClientBuilder().cache(cache).build()

private fun createRetrofit(application: Application, urlBase:String) : Retrofit =
    provideHttpCache(application).pipe { cache ->
        provideOkHttpClientOAuth(cache)
    }.let { httpClient ->
        getRetrofitBuilderDefaults(urlBase).client(httpClient).build()
    }

fun initRetrofit(application: Application) {
    if (retrofitOption == null) {
        retrofitOption = createRetrofit(application, API_URL_BASE)
    }
}

val globoApi:GloboApi
    get() = retrofit.create(GloboApi::class.java)

private val retrofit: Retrofit
    get() = retrofitOption?: throw Exception("You need to initialize retrofit.")


inline infix  fun <P,R> P.pipe(f:(P)->R) = f(this)