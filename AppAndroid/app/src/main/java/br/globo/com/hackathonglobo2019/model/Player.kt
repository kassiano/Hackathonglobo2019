package br.globo.com.hackathonglobo2019.model

data class Player(
    val name:String,
    val picture:Int,
    val position:String,
    val team:String,
    val teamPicture:Int,
    val country:String,
    val countryPicture:Int,
    val videos:List<Video>
)
