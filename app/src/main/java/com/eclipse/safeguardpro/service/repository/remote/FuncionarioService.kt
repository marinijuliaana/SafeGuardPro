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

    @GET("select_funcionarios")
    suspend fun getFuncionarios(): List<Funcionario>

    @Multipart
    @POST("add_funcionarios")
    suspend fun createFuncionario(
        @Part("nome_funcionario") nome: RequestBody,
        @Part("cpf") cpf: RequestBody,
        @Part("cargo") cargo: RequestBody,
        @Part("senha") senha: RequestBody,
        @Part("admin") admin: RequestBody,
        ): Response<Funcionario>

    @GET("funcionario_id/{funcionario_id}")
    suspend fun getFuncionarioById(@Path("funcionario_id") id: Int): Response<List<Funcionario>>

    @GET("funcionario_cpf/{funcionario_cpf}")
    suspend fun getFuncionarioByCpf(@Path("funcionario_cpf") cpf: Int): Response<List<Funcionario>>

    @Multipart
    @PUT("update_funcionarios/{funcionario_id}")
    suspend fun updateFuncionario(
        @Path("funcionario_id") funcionarioId: Int,
        @Part("nome_funcionario") nome: RequestBody,
        @Part("cpf") cpf: RequestBody,
        @Part("cargo") cargo: RequestBody,
        @Part("senha") senha: RequestBody,
        @Part("admin") admin: RequestBody,
    ): Response<Funcionario>

    @DELETE("delete_funcionarios/{funcionario_id}")
    suspend fun deleteFuncionarioById(@Path("funcionario_id")id: Int): Response<Funcionario>
}