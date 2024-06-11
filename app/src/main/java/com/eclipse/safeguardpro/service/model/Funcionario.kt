package com.eclipse.safeguardpro.service.model

data class Funcionario(
    var id: Int = 0,
    var nome: String,
    var cpf: Int ,
    var cargo: String,
    var senha: String,
)
