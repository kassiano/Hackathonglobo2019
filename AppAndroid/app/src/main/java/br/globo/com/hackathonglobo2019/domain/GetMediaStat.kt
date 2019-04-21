package br.globo.com.hackathonglobo2019.domain

import br.globo.com.hackathonglobo2019.network.globoApi

class GetMediaStat {
    fun execute() = globoApi.getMediaStat()
}