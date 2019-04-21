package br.globo.com.hackathonglobo2019.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.MediaController
import br.globo.com.hackathonglobo2019.model.Player
import br.globo.com.hackathonglobo2019.ui.viewmodel.PlayerViewModel
import kotlinx.android.synthetic.main.activity_player_videos.*



class PlayerVideosActivity : AppCompatActivity() {

    companion object {
        const val PLAYER_NAME = "PLAYER_NAME"
    }


    val adapter = VideoAdapter()

    val viewmodel by lazy {
        ViewModelProviders.of(this@PlayerVideosActivity)
            .get(PlayerViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(br.globo.com.hackathonglobo2019.R.layout.activity_player_videos)

        supportActionBar?.hide()
        rvVideos.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvVideos.adapter = adapter


        adapter.onclick = {

            setOpacity(0.6f)
            viewPlayer.visibility = View.GONE
            closeToolBar.visibility = View.VISIBLE
            viewVideo.visibility = View.VISIBLE

            val video = Uri.parse("android.resource://${packageName}/${it.videoFile}")

            val mediaController = MediaController(this@PlayerVideosActivity)
            mediaController.setAnchorView(videoView)
            videoView.setMediaController(mediaController)
            videoView.setVideoURI(video)
            videoView.start()
        }


        val playerName = intent.getStringExtra(PLAYER_NAME)

        viewmodel.fetchPlayer(playerName)
        viewmodel.player.observe(this, Observer(::updateUI))

        //score
        viewmodel.fetchScore()
        viewmodel.score.observe(this, Observer {
             it?.let {
                 ratingBar.rating = it.pontuation / 2
             }
        })

        btClose.setOnClickListener {
            setOpacity(1f)
            videoView.stopPlayback()
            viewPlayer.visibility = View.VISIBLE
            closeToolBar.visibility = View.GONE
            viewVideo.visibility = View.GONE
        }


        imgShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, playerName)
                type = "text/plain"
            }
            startActivity(sendIntent)
        }

        btBack.setOnClickListener {
            finish()
        }

    }


    fun setOpacity(n:Float){
        tvLastGoals.alpha = n
        tvPlayerName2.alpha = n
        adapter.setOpacity(n)
    }


    fun updateUI(player:Player?){
        player?.let {

            tvPlayerName.text = player.name
            tvPlayerPosition.text = player.position
            tvTeamName.text = player.team
            imgPlayerPicture.setImageResource(player.picture)
            tvTeamName.text = player.team
            tvCountry.text = player.country
            imgTeamPicture.setImageResource(player.teamPicture)
            imgCountryPicture.setImageResource(player.countryPicture)
            tvPlayerName2.text = player.name

            adapter.setItems(it.videos)
        }
    }
}
