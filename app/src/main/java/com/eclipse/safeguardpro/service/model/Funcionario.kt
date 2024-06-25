package com.eclipse.safeguardpro.service.model

import com.google.gson.annotations.SerializedName

data class Funcionario(
    @SerializedName("id_funcionario")
    var id: Int = 0,
    @SerializedName("nome_funcionario")
    var nome: String = "",
    var cpf: Int = 0,
    var cargo: String = "",
    var senha: String = "",
    var admin: Boolean = false,
)