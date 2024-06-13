package com.eclipse.safeguardpro.service.repository

import android.content.Context
import com.eclipse.safeguardpro.service.model.Funcionario
import com.eclipse.safeguardpro.service.repository.remote.FuncionarioService
import com.eclipse.safeguardpro.service.repository.remote.RetrofitClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class FuncionarioRepository(context: Context) {

    private val mRemote = RetrofitClient.createService(FuncionarioService::class.java)
    private val funcionarioEmpty = Funcionario(0, "", 0, "", "", false)

    suspend fun getFuncionarios(): List<Funcionario>{
        return mRemote.getFuncionarios()
    }

    suspend fun insertFuncionario(funcionario: Funcionario): Funcionario {
        return mRemote.createFuncionario(
            nome = funcionario.nome.toRequestBody("text/plain".toMediaTypeOrNull()),
            cpf = funcionario.cpf.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            cargo = funcionario.cargo.toRequestBody("text/plain".toMediaTypeOrNull())
        ).body() ?: funcionarioEmpty
    }

    suspend fun getFuncionario(id: Int): Funcionario {
        val response = mRemote.getFuncionarioById(id)
        return if (response.isSuccessful) {
            response.body()?.first() ?: funcionarioEmpty
        } else {
            funcionarioEmpty
        }
    }

    suspend fun getFuncionarioByCpf(cpf: Int): Funcionario {
        val response = mRemote.getFuncionarioByCpf(cpf)
        return if (response.isSuccessful) {
            response.body()?.first() ?: funcionarioEmpty
        } else {
            funcionarioEmpty
        }
    }

    suspend fun updateFuncionario(id: Int, funcionario: Funcionario): Funcionario {
        return mRemote.updateFuncionario(
            nome = funcionario.nome.toRequestBody("text/plain".toMediaTypeOrNull()),
            cpf = funcionario.cpf.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            cargo = funcionario.cargo.toRequestBody("text/plain".toMediaTypeOrNull()),
            funcionarioId = id
        ).body() ?: funcionarioEmpty
    }
    suspend fun deleteFuncionario(id: Int): Boolean {
        return mRemote.deleteFuncionarioById(id).isSuccessful
    }
}