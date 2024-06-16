package com.eclipse.safeguardpro.service.repository.remote

import android.view.PixelCopy.Request
import com.eclipse.safeguardpro.service.model.Funcionario
import okhttp3.RequestBody

import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface FuncionarioService {

    @GET("get_funcionarios")
    suspend fun getFuncionarios(): List<Funcionario>

    @Multipart
    @POST("add_funcionarios")
    suspend fun createFuncionario(
        @Part("nome") nome: RequestBody,
        @Part("cpf") cpf: RequestBody,
        @Part("cargo") cargo: RequestBody,
        @Part("senha") senha: RequestBody,
        @Part("admin") admin: RequestBody,
        ): Response<Funcionario>

    @GET("select_funcionarios/{funcionario_id}")
    suspend fun getFuncionarioById(@Part("funcionario_id") id: Int): Response<List<Funcionario>>

    @GET("get_funcionario/{funcionario_cpf}")
    suspend fun getFuncionarioByCpf(@Path("funcionario_cpf") cpf: Int): Response<List<Funcionario>>

    @Multipart
    @PUT("update_funcionarios/{funcionario_id}")
    suspend fun updateFuncionario(
        @Part("funcionario_id") funcionarioId: Int,
        @Part("nome") nome: RequestBody,
        @Part("cpf") cpf: RequestBody,
        @Part("cargo") cargo: RequestBody,
        @Part("senha") senha: RequestBody,
        @Part("admin") admin: RequestBody,
    ): Response<Funcionario>

    @DELETE("delete_funcionarios/{funcionario_id}")
    suspend fun deleteFuncionarioById(@Part("funcionario_id")id: Int): Response<Funcionario>
}