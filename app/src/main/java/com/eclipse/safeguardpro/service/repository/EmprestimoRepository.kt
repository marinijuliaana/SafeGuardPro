package com.eclipse.safeguardpro.service.repository

import android.content.Context
import com.eclipse.safeguardpro.service.model.Emprestimo
import com.eclipse.safeguardpro.service.repository.remote.EmprestimoService
import com.eclipse.safeguardpro.service.repository.remote.RetrofitClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class EmprestimoRepository(context: Context){

    private val mRemote = RetrofitClient.createService(EmprestimoService::class.java)
    private val emprestimoEmpty = Emprestimo(0, " ", " ", 0, 0, 0, 0)

    suspend fun getEmprestimos(): List<Emprestimo>{
        return mRemote.getEmprestimos()
    }

    suspend fun insertEmprestimo(emprestimo: Emprestimo): Emprestimo {
        return mRemote.createEmprestimo(
            data_entrega = emprestimo.data_entrega.toRequestBody("text/plain".toMediaTypeOrNull()),
            data_devolucao = emprestimo.data_devolucao.toRequestBody("text/plain".toMediaTypeOrNull()),
            numero_ca = emprestimo.numero_ca.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            funcionario_id = emprestimo.funcionario_id.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            epi_id = emprestimo.epi_id.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        ).body() ?:emprestimoEmpty
    }

    suspend fun getEmprestimo(id: Int): Emprestimo {
        val response = mRemote.getEmprestimoById(id)
        return if (response.isSuccessful) {
            response.body()?.first() ?: emprestimoEmpty
        } else {
            emprestimoEmpty
        }
    }

    suspend fun updateEmprestimo(id: Int, emprestimo: Emprestimo): Emprestimo {
        return mRemote.updateEmprestimo(
            data_entrega = emprestimo.data_entrega.toRequestBody("text/plain".toMediaTypeOrNull()),
            data_devolucao = emprestimo.data_devolucao.toRequestBody("text/plain".toMediaTypeOrNull()),
            numero_ca = emprestimo.numero_ca.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            epi_id = emprestimo.epi_id.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            funcionario_id = emprestimo.funcionario_id.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            emprestimoId = id
        ).body() ?: emprestimoEmpty
    }
    suspend fun deleteEmprestimo(id: Int): Boolean {
        return mRemote.deleteEmprestimoById(id).isSuccessful
    }
}

