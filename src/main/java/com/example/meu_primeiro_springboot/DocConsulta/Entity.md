# Entidade 
entidade é uma classe java que representa uma tabela no banco de dados 
Marcada como @Entity.
Cada atributo representa uma coluna da tabela.
Se nao tiver usando lombook, precisa sempre usar os getters e setters.

Precisa sempre identifcar a entidade 
com Id: indica chave primaria 
e usar strategy = GenerationType.IDENTITY // pq o spring que seria responsavel por auto incrimentar 

Ai sim pode se criar um Repository 
usamos para fazer persistencia, é uma interface, não precisa implementar

Dentro do Repository tem o Jpa para se extender a interface. 
ou seja vai ser criada a interface, vai extender jpa. 
o jpa vai ter dois parametros = <Produto, Long> // exemplo, Produto é a entidade que vamos mexer e Long é tipo da chave primaria

pesquisar na documentacao do spring + jpa para ver quais sao as querys e oq da para fazer 
