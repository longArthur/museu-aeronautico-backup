# Museu Aeronautico
Esse é um trabalho de Programação III, no Instituto Federal do Rio Grande do Sul, no ano de 2023. Ele servirá como trabalho final do grupo de Arthur Stedile e Caio Pereira, nas disciplinas de
Programação, Banco de Dados e Análise e Projeto de Sistemas.

Ele servirá como sistema de Login empresarial e de gerenciamento de visitantes.


Aqui, indicamos o esquema logico-textual do Museu Aeronautico, demarcados com ! se terminados,
e "#" se parcialmente terminados.

```
!Visitante(codigo, nome, sobrenome, genero)

!Visita(cod_visitante#, cod_hangar#, data_ingresso, tempo_estadia)
    cod_visitante referencia Visitante.
    cod_hangar referencia Hangar.

!Endereco(codigo, cidade, bairro, rua, numero_endereco)

!Hangar(codigo, bloco, qtd_vagas, largura_metros, comprimento_metros, capacidade_visitantes, cod_departamento#, endereco#)
    !cod_departamento referencia Departamento.
    !endereco referencia Endereco.

!Departamento(codigo, data_criacao, orcamento)

!Empregado(CPF, data_ingressao, nome, sobrenome, salario, tipo, endereco#, departamento#)
    !endereco referencia Endereco.
    !departamento referencia Departamento.
    
    !Engenheiro(CPF_empregado#, area_atuacao, CREA)
    !    CPF_empregado referencia Empregado.
    
    !Piloto(CPF_empregado#, CHT)
    !    CPF_empregado referencia Empregado.

    !Historiador(CPF_empregado#, registro)
    !    CPF_empregado referencia Empregado.

    !Gerente(CPF_empregado#, inicio_gerencia)
    !    CPF_empregado referencia Empregado.

!Modelo(codigo, marca, data_producao, comprimento_metros, largura_metros, historia_aviao, tipo, cod_hangar#)
    !cod_hangar referencia Hangar.
    
    !Aviao(cod_modelo#, area_atuacao)
        cod_modelo referencia modelo

    !Replica_Aviao(cod_modelo#, material_usado)
        cod_modelo referencia modelo.

Pilota(CPF_piloto#, cod_aviao#)
    CPF_piloto referencia Piloto.
    cod_aviao referencia Avião.
```

# TODO
- [ ] Criar o banco de dados
- [X] Criar a interface
- [ ] Criar o sistema de login
- [ ] Criar o sistema de gerenciamento de visitantes
- [ ] Criar o sistema de gerenciamento de empregados
- [ ] Criar o sistema de gerenciamento de hangares
- [ ] Criar o sistema de gerenciamento de departamentos
- [ ] Criar o sistema de gerenciamento de modelos


# Problemas a resolver
- [X] Organização de layout de classes
- [ ] Interoperabilidade entre NetBeans e IntelliJ
