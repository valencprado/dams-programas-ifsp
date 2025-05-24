from flask import Flask, Blueprint
from flask_restx import Api
from app.main.pessoa.pessoa_controller import api as home_ns
from app.main.livro.livro_controller import api as livro

app = Flask(__name__)
blueprint = Blueprint('api', __name__)
app.register_blueprint(blueprint)

api = Api(app, title='Livros/Pessoas', version='1.0', description='Api para gerenciar livros e pessoas (uso do Swagger com Flask).')

api.add_namespace(home_ns, path='/pessoa')
api.add_namespace(livro, path='/livro')