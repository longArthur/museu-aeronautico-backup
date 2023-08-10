# Museu Aeronautico
Esse é um trabalho de Programação III, no Instituto Federal do Rio Grande do Sul, no ano de 2023. Ele servirá como trabalho final do grupo de Arthur Stedile e Caio Pereira, nas disciplinas de
Programação, Banco de Dados e Análise e Projeto de Sistemas.

Ele servirá como sistema de Login empresarial e de gerenciamento de visitantes.


Aqui, indicamos o esquema logico-textual do Museu Aeronautico, demarcados com ! se terminados,
e "#" se parcialmente terminados.

```
!Logic.Visitante(codigo, nome, sobrenome, genero)

!Logic.Visita(cod_visitante#, cod_hangar#, data_ingresso, tempo_estadia)
    cod_visitante referencia Logic.Visitante.
    cod_hangar referencia Logic.Hangar.

!Logic.Endereco(codigo, cidade, bairro, rua, numero_endereco)

!Logic.Hangar(codigo, bloco, qtd_vagas, largura_metros, comprimento_metros, capacidade_visitantes, cod_departamento#, endereco#)
    !cod_departamento referencia Logic.Departamento.
    !endereco referencia Logic.Endereco.

!Logic.Departamento(codigo, data_criacao, orcamento)

!Logic.Empregado(Logic.CPF, data_ingressao, nome, sobrenome, salario, tipo, endereco#, departamento#)
    !endereco referencia Logic.Endereco.
    !departamento referencia Logic.Departamento.
    
    !Logic.Engenheiro(CPF_empregado#, area_atuacao, CREA)
    !    CPF_empregado referencia Logic.Empregado.
    
    !Logic.Piloto(CPF_empregado#, CHT)
    !    CPF_empregado referencia Logic.Empregado.

    !Logic.Historiador(CPF_empregado#, registro)
    !    CPF_empregado referencia Logic.Empregado.

    !Logic.Gerente(CPF_empregado#, inicio_gerencia)
    !    CPF_empregado referencia Logic.Empregado.

!Logic.Modelo(codigo, marca, data_producao, comprimento_metros, largura_metros, historia_aviao, tipo, cod_hangar#)
    !cod_hangar referencia Logic.Hangar.
    
    !Aviao(cod_modelo#, area_atuacao)
        cod_modelo referencia modelo

    !Replica_Aviao(cod_modelo#, material_usado)
        cod_modelo referencia modelo.

Pilota(CPF_piloto#, cod_aviao#)
    CPF_piloto referencia Logic.Piloto.
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
