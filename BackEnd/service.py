import requests

CHAMPIONSHIP_ID = "659"

HEADERS = {
    'Authorization': 'Bearer d5a3f277-638'}

URL_GAMES = "http://apifutebol.footstats.net/3.0/campeonatos/659/equipes/1004/partidas"

CORINTHIANS_ID = 1004

CLAISON_ID = 38464
# 52064


def player_gols():
    URL_GOLS = "http://apifutebol.footstats.net/3.0/partidas/[partida]/gols"
    g = 0
    games = requests.get(URL_GAMES, headers=HEADERS)

    for game in range(0, len(games.json()['data'])):
        URL_GOLS = URL_GOLS.replace(
            "[partida]", str(games.json()['data'][game]['id']))
        gols = requests.get(URL_GOLS, headers=HEADERS)

        for gol in range(0, len(gols.json()['data']['equipeMandante'])):
            if (gols.json()['data']['equipeMandante'][gol]['idEquipe'] == CORINTHIANS_ID):
                if (gols.json()['data']['equipeMandante'][gol]['idJogador'] == CLAISON_ID):
                    g += 1

        for gol in range(0, len(gols.json()['data']['equipeVisitante'])):
            if (gols.json()['data']['equipeVisitante'][gol]['idEquipe'] == CORINTHIANS_ID):
                if (gols.json()['data']['equipeVisitante'][gol]['idJogador'] == CLAISON_ID):
                    g += 1
        URL_GOLS = URL_GOLS.replace(
            str(games.json()['data'][game]['id']), "[partida]")

    return g


def player_games():
    URL_PLAYERS = "http://apifutebol.footstats.net/3.0/partidas/[partida]/jogadores/scout"
    games = requests.get(URL_GAMES, headers=HEADERS)
    g = 0
    for game in range(0, len(games.json()['data'])):
        URL_PLAYERS = URL_PLAYERS.replace(
            "[partida]", str(games.json()['data'][game]['id']))
        players = requests.get(URL_PLAYERS, headers=HEADERS)

        for player in range(0, len(players.json()['data'])):
            if (players.json()['data'][player]['idJogador'] == CLAISON_ID):
                g += 1

        URL_PLAYERS = URL_PLAYERS.replace(
            str(games.json()['data'][game]['id']), "[partida]")
    return g


def player_stat():
    URL_PLAYERS = "http://apifutebol.footstats.net/3.0/partidas/[partida]/jogadores/scout"
    games = requests.get(URL_GAMES, headers=HEADERS)
    n_game = 0
    assistencia = 0
    gol = 0
    cartaoAmarelo = 0
    cartaoVermelho = 0
    cruzamento = 0
    faltaCometida = 0
    faltaRecebida = 0
    finalizacao = 0
    interceptacao = 0
    lancamento = 0
    posseDeBola = 0
    viradaDeJogo = 0

    for game in range(0, len(games.json()['data'])):
        URL_PLAYERS = URL_PLAYERS.replace(
            "[partida]", str(games.json()['data'][game]['id']))
        players = requests.get(URL_PLAYERS, headers=HEADERS)
        for player in range(0, len(players.json()['data'])):
            if (players.json()['data'][player]['idJogador'] == CLAISON_ID):
                n_game += 1
                assistencia += players.json()['data'][player]['assistencia'] + players.json()[
                    'data'][player]['assistenciaFinalizacao']
                gol += players.json()['data'][player]['golPro']
                cartaoAmarelo += players.json(
                )['data'][player]['cartaoAmarelo']
                cartaoVermelho += players.json(
                )['data'][player]['cartaoVermelho']
                cruzamento += players.json()['data'][player]['cruzamentoCerto']
                faltaCometida += players.json(
                )['data'][player]['faltaCometida']
                faltaRecebida += players.json(
                )['data'][player]['faltaRecebida']
                finalizacao += players.json()['data'][player]['finalizacaoCerta']
                interceptacao += players.json(
                )['data'][player]['interceptacaoCerta']
                lancamento += players.json()['data'][player]['lancamentoCerto']
                posseDeBola += players.json()['data'][player]['posseDeBola']
                viradaDeJogo += players.json(
                )['data'][player]['viradaDeJogoCerta']

        URL_PLAYERS = URL_PLAYERS.replace(
            str(games.json()['data'][game]['id']), "[partida]")

    response = {
        "n_game": n_game,
        "assistencia": assistencia,
        "gol": gol,
        "cartaoAmarelo": cartaoAmarelo,
        "cartaoVermelho": cartaoVermelho,
        "cruzamento": cruzamento,
        "faltaCometida": faltaCometida,
        "faltaRecebida": faltaRecebida,
        "finalizacao": finalizacao,
        "interceptacao": interceptacao,
        "lancamento": lancamento,
        "posseDeBola": posseDeBola,
        "viradaDeJogo": viradaDeJogo
    }

    return response


def player_media():
    data = player_stat()
    data['assistencia'] = data['assistencia']/data['n_game']
    data['gol'] = data['gol']/data['n_game']
    data['cartaoAmarelo'] = data['cartaoAmarelo']/data['n_game']
    data['cartaoVermelho'] = data['cartaoVermelho']/data['n_game']
    data['cruzamento'] = data['cruzamento']/data['n_game']
    data['faltaCometida'] = data['faltaCometida']/data['n_game']
    data['faltaRecebida'] = data['faltaRecebida']/data['n_game']
    data['finalizacao'] = data['finalizacao']/data['n_game']
    data['interceptacao'] = data['interceptacao']/data['n_game']
    data['lancamento'] = data['lancamento']/data['n_game']
    data['posseDeBola'] = data['posseDeBola']/data['n_game']
    data['viradaDeJogo'] = data['viradaDeJogo']/data['n_game']
    return data


def player_pontuation():
    URL_PLAYERS = "http://apifutebol.footstats.net/3.0/partidas/[partida]/jogadores/scout"
    games = requests.get(URL_GAMES, headers=HEADERS)
    n_game = 0
    assistencia = 0
    cruzamento = 0
    desarme = 0
    drible = 0
    finalizacao = 0
    interceptacao = 0
    lancamento = 0
    passe = 0
    posseDeBola = 0
    viradaDeJogo = 0

    for game in range(0, len(games.json()['data'])):
        URL_PLAYERS = URL_PLAYERS.replace(
            "[partida]", str(games.json()['data'][game]['id']))
        players = requests.get(URL_PLAYERS, headers=HEADERS)
        for player in range(0, len(players.json()['data'])):
            if (players.json()['data'][player]['idJogador'] == CLAISON_ID):
                n_game += 1
                assistencia += players.json()['data'][player]['assistencia'] + players.json()[
                    'data'][player]['assistenciaFinalizacao']
                cruzamentoTotal = (players.json()['data'][player]['cruzamentoCerto'] + players.json()[
                    'data'][player]['cruzamentoErrado'])
                if (cruzamentoTotal != 0):
                    cruzamento += players.json(
                    )['data'][player]['cruzamentoCerto'] / cruzamentoTotal
                desarmeTotal = (players.json()['data'][player]['desarmeCerto'] + players.json()[
                    'data'][player]['desarmeErrado'])
                if (desarmeTotal != 0):
                    desarme += players.json()['data'][player]['desarmeCerto'] / \
                        desarmeTotal
                totalDrible = (players.json()['data'][player]['dribleCerto'] + players.json()[
                    'data'][player]['dribleErrado'])
                if (totalDrible != 0):
                    drible += players.json()['data'][player]['dribleCerto'] / \
                        totalDrible
                totalFinalizacao = (players.json()['data'][player]['finalizacaoCerta'] + players.json()[
                    'data'][player]['finalizacaoErrada'])
                if (totalFinalizacao != 0):
                    finalizacao += players.json(
                    )['data'][player]['finalizacaoCerta'] / totalFinalizacao
                totalInterceptacao = (players.json()['data'][player]['interceptacaoCerta'] + players.json()[
                    'data'][player]['interceptacaoErrada'])
                if (totalInterceptacao != 0):
                    interceptacao += players.json(
                    )['data'][player]['interceptacaoCerta'] / totalInterceptacao

                totalLancamento = (players.json()['data'][player]['lancamentoCerto'] + players.json()[
                    'data'][player]['lancamentoErrado'])
                if (totalLancamento != 0):
                    lancamento += players.json(
                    )['data'][player]['lancamentoCerto'] / totalLancamento

                passesTotal = (players.json()['data'][player]['passeCerto'] + players.json()[
                    'data'][player]['passeErrado'])
                if (passesTotal != 0):
                    passe += 1.5 * \
                        players.json()[
                            'data'][player]['passeCerto'] / passesTotal

                posseDeBola += 1.2 * \
                    players.json()['data'][player]['posseDeBola'] / 90

                totalVirada = (players.json()['data'][player]['viradaDeJogoCerta'] + players.json()[
                    'data'][player]['viradaDeJogoErrada'])
                if (totalVirada != 0):
                    viradaDeJogo += players.json(
                    )['data'][player]['viradaDeJogoCerta'] / totalVirada

        URL_PLAYERS = URL_PLAYERS.replace(
            str(games.json()['data'][game]['id']), "[partida]")

    assistencia = assistencia / n_game
    cruzamento = cruzamento / n_game
    desarme = desarme / n_game
    drible = drible / n_game
    finalizacao = finalizacao / n_game
    interceptacao = interceptacao / n_game
    passe = passe / n_game
    posseDeBola = posseDeBola / n_game
    viradaDeJogo = viradaDeJogo / n_game

    total = assistencia + cruzamento + desarme + drible + \
        finalizacao + interceptacao + passe + posseDeBola + viradaDeJogo
    total = (total / 9) * 10
    return {"pontuation": total}
