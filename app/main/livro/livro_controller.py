from flask_restx import Resource, Namespace, fields
from flask import request
from .livro_db import LivroDb

api = Namespace('Livro', description='CRUD de livros')

modelo = api.model('LivroModel', {
    'id': fields.Integer,
    'nome': fields.String,
    'sinopse': fields.String,
    'autor': fields.String,
})

@api.route('/')
class LivroController(Resource):
    @api.response(200, 'Busca realizada com sucesso')
    def get(self):
        return LivroDb.obter(), 200
    @api.expect(modelo)
    def post(self):
        return LivroDb.adicionar(request.json), 201
    
    
@api.route('/<id>')
class LivroIdController(Resource):
    @api.response(200, 'Busca realizada com sucesso')
    def get(self, id):
        return LivroDb.obter(id), 200
    
    @api.response(200, 'Busca realizada com sucesso')
    # @api.param('nome', 'Nome do livro')
    # @api.param('sinopse', 'Sinopse do livro')
    # @api.param('autor', 'Autor do livro')
    @api.expect(modelo)
    def put(self, id):
        return LivroDb.alterar(id, request.json), 200
    
    def delete(self, id):
        return LivroDb.remover(id), 200