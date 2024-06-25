package com.eclipse.safeguardpro.service.model

import com.google.gson.annotations.SerializedName

data class Epi (
    @SerializedName("id_epi")
    var id: Int = 0,
    var nome_equipamento: String = "",
    var tipo_protecao: String = "",
    var validade_epi: String = "",
    var tempo_uso: String = "",
    var numero_ca: Int = 0,
)