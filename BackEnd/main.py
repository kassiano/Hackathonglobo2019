import datetime
import requests
import json
from flask import Flask, render_template, request, Response
import service

app = Flask(__name__)


@app.route('/')
def root():
    return render_template(
        'index.html')


@app.route('/stat', methods=['GET'])
def player_stats():
    return Response(json.dumps(service.player_stat()), status=200, mimetype='application/json')


@app.route('/media-stat', methods=['GET'])
def player_media_stat():
    return Response(json.dumps(service.player_media()), status=200, mimetype='application/json')


@app.route('/pontuation', methods=['GET'])
def player_pontuation():
    return Response(json.dumps(service.player_pontuation()), status=200, mimetype='application/json')


if __name__ == '__main__':
    app.run(host='127.0.0.1', port=8080, debug=True)
