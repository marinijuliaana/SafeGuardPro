package com.eclipse.safeguardpro.service.repository.remote

import com.eclipse.safeguardpro.service.model.Emprestimo
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface EmprestimoService {

    @GET("select_emprestimos")
    suspend fun getEmprestimos(): List<Emprestimo>

    @Multipart
    @POST("add_emprestimos")
    suspend fun createEmprestimo(
        @Part("data_entrega") data_entrega: RequestBody,
        @Part("nome_devolucao") data_devolucao: RequestBody,
        @Part("numero_ca") numero_ca: RequestBody,
        @Part("epi_id") epi_id: RequestBody,
        @Part("funcionario_id") funcionario_id: RequestBody,
    ): Response<Emprestimo>

    @GET("emprestimo/{emprestimo_id}")
    suspend fun getEmprestimoById(@Path("emprestimo_id") id: Int): Response<List<Emprestimo>>

    @Multipart
    @PUT("update_emprestimos/{emprestimo_id}")
    suspend fun updateEmprestimo(
        @Path("emprestimo_id") emprestimoId: Int,
        @Part("data_entrega") data_entrega: RequestBody,
        @Part("data_devolucao") data_devolucao: RequestBody,
        @Part("numero_ca") numero_ca: RequestBody,
        @Part("epi_id") epi_id: RequestBody,
        @Part("funcionario_id") funcionario_id: RequestBody,
    ): Response<Emprestimo>

    @DELETE("delete_emprestimos/{emprestimo_id}")
    suspend fun deleteEmprestimoById(@Path("emprestimo_id")id: Int): Response<Emprestimo>

}