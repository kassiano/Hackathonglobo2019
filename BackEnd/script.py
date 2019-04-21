import requests

CHAMPIONSHIP_ID = "659"

HEADERS = {
    'Authorization': 'Bearer d5a3f277-638'}

URL_GAMES = "http://apifutebol.footstats.net/3.0/campeonatos/659/equipes/1004/partidas"
URL_GOLS = "http://apifutebol.footstats.net/3.0/partidas/[partida]/gols"

CORINTHIANS_ID = 1004

CLAISON_ID = 38464

games = requests.get(URL_GAMES, headers=HEADERS)

for game in range(0, len(games.json()['data'])):
    URL_GOLS = URL_GOLS.replace(
        "[partida]", str(games.json()['data'][game]['id']))
    gols = requests.get(URL_GOLS, headers=HEADERS)
    for gol in range(0, len(gols.json()['data']['equipeMandante'])):
        if (gols.json()['data']['equipeMandante'][gol]['idEquipe'] == CORINTHIANS_ID):
            if (gols.json()['data']['equipeMandante'][gol]['idJogador'] == CLAISON_ID):
                print(str(games.json()['data'][game]
                          ['dataDaPartida']['dayOfMonth']) +
                      '/' + str(games.json()['data'][game]
                                ['dataDaPartida']['monthValue']))
    for gol in range(0, len(gols.json()['data']['equipeVisitante'])):
        if (gols.json()['data']['equipeVisitante'][gol]['idEquipe'] == CORINTHIANS_ID):
            if (gols.json()['data']['equipeVisitante'][gol]['idJogador'] == CLAISON_ID):
                print(str(games.json()['data'][game]
                          ['dataDaPartida']['dayOfMonth']) +
                      '/' + str(games.json()['data'][game]
                                ['dataDaPartida']['monthValue']))
    URL_GOLS = URL_GOLS.replace(
        str(games.json()['data'][game]['id']), "[partida]")
