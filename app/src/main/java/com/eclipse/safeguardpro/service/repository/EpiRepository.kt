package com.eclipse.safeguardpro.service.repository

import android.content.Context
import com.eclipse.safeguardpro.service.model.Epi
import com.eclipse.safeguardpro.service.model.Funcionario
import com.eclipse.safeguardpro.service.repository.remote.EpiService
import com.eclipse.safeguardpro.service.repository.remote.FuncionarioService
import com.eclipse.safeguardpro.service.repository.remote.RetrofitClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class EpiRepository (context: Context) {

    private val mRemote = RetrofitClient.createService(EpiService::class.java)
    private val EpiEmpty = Epi(0, " ", " ", " ", "", 0)

    suspend fun getEpis(): List<Epi>{
        return mRemote.getEpis()
    }

    suspend fun insertEpi(epi: Epi): Epi {
        return mRemote.createEpi(
            nome_equipamento = epi.nome_equipamento.toRequestBody("text/plain".toMediaTypeOrNull()),
            tipo_protecao = epi.tipo_protecao.toRequestBody("text/plain".toMediaTypeOrNull()),
            validade_epi = epi.validade_epi.toRequestBody("text/plain".toMediaTypeOrNull()),
            tempo_uso = epi.tempo_uso.toRequestBody("text/plain".toMediaTypeOrNull()),
            numero_ca = epi.numero_ca.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        ).body() ?: EpiEmpty
    }

    suspend fun getEpi(id: Int): Epi {
        val response = mRemote.getEpiById(id)
        return if (response.isSuccessful) {
            response.body()?.first() ?: EpiEmpty
        } else {
            EpiEmpty
        }
    }

    suspend fun getEpiByCa(ca: Int): Epi {
        val response = mRemote.getEpiByCa(ca)
        return if (response.isSuccessful) {
            response.body()?.first() ?: EpiEmpty
        } else {
            EpiEmpty
        }
    }

    suspend fun updateEpi(id: Int, epi: Epi): Epi {
        return mRemote.updateEpi(
            nome_equipamento = epi.nome_equipamento.toRequestBody("text/plain".toMediaTypeOrNull()),
            tipo_protecao = epi.tipo_protecao.toRequestBody("text/plain".toMediaTypeOrNull()),
            validade_epi = epi.validade_epi.toRequestBody("text/plain".toMediaTypeOrNull()),
            tempo_uso = epi.tempo_uso.toRequestBody("text/plain".toMediaTypeOrNull()),
            numero_ca = epi.numero_ca.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            epiId = id
        ).body() ?: EpiEmpty
    }

    suspend fun deleteEpi(id: Int): Boolean {
        return mRemote.deleteEpiById(id).isSuccessful
    }

}