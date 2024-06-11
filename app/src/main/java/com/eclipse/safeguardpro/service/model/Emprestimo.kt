package com.eclipse.safeguardpro.service.model

data class Emprestimo(
    var id: Int = 0,
    var data_entrega: String = "",
    var data_devolucao: String = "",
    var numero_ca: Int = 0,
    var epi_id: Int = 0,
    var funcionario_id: Int = 0,
    var cpf: Int = 0,
)