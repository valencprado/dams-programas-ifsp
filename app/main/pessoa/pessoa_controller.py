from flask_restx import Resource, Namespace, fields
from flask import request
from .pessoa_db import PessoaDb

api = Namespace('Pessoa', description='Manutenção dados de pessoa')

modelo = api.model('PessoaModel', {
    'id': fields.Integer,
    'nome': fields.String,
    'endereco': fields.String
})

@api.route('/')
class PessoaController(Resource):
    @api.response(200, 'Busca realizada com sucesso')
    def get(self):
        return PessoaDb.obter(), 200
    @api.expect(modelo)
    def post(self):
        return PessoaDb.adicionar(request.json), 201
    
    
@api.route('/<id>')
class PessoaIdController(Resource):
    @api.response(200, 'Busca realizada com sucesso')
    def get(self, id):
        return PessoaDb.obter(id), 200
    
    @api.response(200, 'Busca realizada com sucesso')
    @api.param('nome', 'Nome do Pessoa')
    @api.param('endereco', 'endereco do Pessoa')
    def put(self, id):
        return PessoaDb.alterar(id, request.json), 200
    
    def delete(self, id):
        return PessoaDb.remover(id), 200