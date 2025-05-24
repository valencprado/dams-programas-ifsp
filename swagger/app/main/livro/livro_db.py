class LivroDb: 
    items = [
        {
            'id': 1,
            'nome': 'A Fantástica Revolução dos 100 Dias',
            'sinopse': 'A Fantástica Revolução dos 100 Dias é um livro .....',
            'autor': 'Rogério Glaser'
        },
        {
            'id': 2,
            'nome': '1984',
            'sinopse': '1984 é um livro de ficção científica escrito por George Orwell.',
            'autor': 'George Orwell'
        },{
            'id': 3,
            'nome': 'Rita Lee: Uma autobiografia',
            'sinopse': 'Rita Lee: Uma autobiografia é um livro de memórias da cantora e compositora brasileira Rita Lee.',
            'autor': 'Rita Lee'
        }
    ]
    
    @classmethod
    def adicionar(cls, item):
        cls.items.append(item)
        return True
    
    
    @classmethod
    def obter(cls, id=None):
        id = str(id)
        if id:
            return next(filter(lambda x: str(x['id']) == id, cls.items), {})
        return cls.items
    
    @classmethod
    def remover(cls, id):
        id = str(id)
        cls.items = list(filter(lambda x: str(x['id']) != id, cls.items))
        return {"mensagem": f"id {id} deletado com sucesso"}
    
    @classmethod
    def alterar(cls, id, novo_item: dict):
        id=str(id)
        # Find the item by ID
        item = next(filter(lambda x: str(x['id']) == id, cls.items), None)
        print(cls.items)
        if not item:
            # If the item is not found, return an error message
            return {"mensagem": f"Item com id {id} não encontrado"}, 404
    
        # Update the item with the new values, if provided
        if novo_item.get('nome'):
            item['nome'] = novo_item.get('nome')
        
        if novo_item.get('sinopse'):
            item['sinopse'] = novo_item.get('sinopse')
        
        if novo_item.get('autor'):
            item['autor'] = novo_item.get('autor')
        
        return item
    