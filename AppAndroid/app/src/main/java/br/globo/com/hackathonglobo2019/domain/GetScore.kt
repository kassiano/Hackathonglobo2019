package br.globo.com.hackathonglobo2019.domain

import br.globo.com.hackathonglobo2019.network.globoApi

class GetScore {
    fun execute() = globoApi.getScore()
}