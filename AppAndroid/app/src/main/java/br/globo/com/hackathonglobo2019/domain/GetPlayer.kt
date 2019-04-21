package br.globo.com.hackathonglobo2019.domain

import br.globo.com.hackathonglobo2019.R
import br.globo.com.hackathonglobo2019.model.Player
import br.globo.com.hackathonglobo2019.model.Video
import io.reactivex.Observable

class GetPlayer {

    fun execute():Observable<Player>{

        return Observable.just(
            Player("Clayson Henrique",
                R.drawable.clayson,
                "Atacante",
                "Corinthians",
                R.drawable.corin,
                "Brasil",
                    R.drawable.ic_brazil,
                    getVideos()
                )
        )

    }


    private fun getVideos():List<Video>{
        return listOf<Video>(
            Video("12/05/2019", "Corinthians X Santos","45’ 2º tempo", R.raw.corinthians_santos, R.drawable.video_tumbnail),
            Video("12/05/2019", "Corinthians X Santos","45’ 2º tempo", R.raw.corinthians_santos, R.drawable.video_tumbnail),
            Video("12/05/2019", "Corinthians X Santos","45’ 2º tempo", R.raw.corinthians_santos, R.drawable.video_tumbnail),
            Video("12/05/2019", "Corinthians X Santos","45’ 2º tempo", R.raw.corinthians_santos, R.drawable.video_tumbnail),
            Video("12/05/2019", "Corinthians X Santos","45’ 2º tempo", R.raw.corinthians_santos, R.drawable.video_tumbnail)
        )
    }

}