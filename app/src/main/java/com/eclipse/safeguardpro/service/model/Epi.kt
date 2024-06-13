package com.eclipse.safeguardpro.service.model

data class Epi (
    var id: Int = 0,
    var nome_equipamento: String = "",
    var tipo_protecao: String = "",
    var validade_epi: String = "",
    var tempo_uso: String = "",
    var numero_ca: Int = 0,
)