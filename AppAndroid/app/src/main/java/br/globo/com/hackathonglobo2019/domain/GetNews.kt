package br.globo.com.hackathonglobo2019.domain

import br.globo.com.hackathonglobo2019.R
import br.globo.com.hackathonglobo2019.model.News
import io.reactivex.Observable

class GetNews {

    fun execute(): Observable<List<News>> =

        Observable.just(
            listOf(
                    News("Fred provoca presidente do Atlético-MG e fala sobre retorno ao Cruzeiro: \"Caí para cima\"",
                            "futebol",
                    "Centroavante, campeão mineiro e artilheiro do Estadual admite que procurou Sérgio Sette Câmara para \"dar uma olhada\"",
                              R.drawable.img_01,
                       "Há 47 minutos"
                    ),

                    News("Técnico do PSG diz que Neymar pode voltar aos gramados neste domingo",
                        "futebol francês",
                        "“Posso perceber que ele Ney se sente livre, não tem medo, está confiante”, afirma Thomas Tuchel. Brasileiro não joga há quase três meses",
                        R.drawable.img_02,
                        "Há 3 horas"
                    ),

                    News("Melhores do Mineiro: seleção do Mineiro mostra soberania do Campeão Cruzeiro",
                        "campeonato mineiro",
                        "Com oito jogadores, Cruzeiro domina a seleção do Mineiro;Ricardo Oliveira e Luan, do Atlético-MG, e Leandro Silva, do América-MG, completam a lista",
                        R.drawable.img_03,
                        "Há 3 horas"
                    ),

                    News("Defensores marcam, Barcelona vence a Real Sociedad e fica mais perto do título",
                        "campeonato europeu",
                        "Time catalão segue com ótima vantagem sobre o Atlético de Madrid, segundo colocado",
                        R.drawable.img_04,
                        "Há 5 horas"
                    ),

                    News("Após furto, Sampaoli ganha bicicleta do Santos; crianças entregam o presente no CT",
                        "santos",
                        "Bike do treinador havia sido roubada na última quinta-feira em frente a agência bancária",
                        R.drawable.img_05,
                        "Há 4 horas"
                    ),

                    News("Nicholas Santos, 39, faz 2º melhor tempo do mundo, mas não vai a Mundial: \"Pagaria do bolso\"",
                        "natação",
                        "Critério de convocação da CBDA não prevê ida de atletas para distâncias que não fazem parte dos Jogos Olímpicos",
                        R.drawable.img_06,
                        "Há 56 minutos"
                    )
                 )
        )

}