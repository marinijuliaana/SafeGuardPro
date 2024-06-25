package com.eclipse.safeguardpro.service.repository.remote

import com.eclipse.safeguardpro.service.model.Epi
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

interface EpiService {

    @GET("select_epis")
    suspend fun getEpis(): List<Epi>

    @Multipart
    @POST("add_epis")
    suspend fun createEpi(
        @Part("nome_equipamento") nome_equipamento: RequestBody,
        @Part("tipo_protecao") tipo_protecao: RequestBody,
        @Part("validade_epi") validade_epi: RequestBody,
        @Part("tempo_uso") tempo_uso: RequestBody,
        @Part("numero_ca") numero_ca: RequestBody,
    ): Response<Epi>

    @GET("epi_id/{epi_id}")
    suspend fun getEpiById(@Path("epi_id") id: Int): Response<List<Epi>>

    @GET("epi/{epi_ca}")
    suspend fun getEpiByCa(@Path("epi_ca") ca: Int): Response<List<Epi>>

    @Multipart
    @PUT("update_epis/{epi_id}")
    suspend fun updateEpi(
        @Path("epi_id") epiId: Int,
        @Part("nome_equipamento") nome_equipamento: RequestBody,
        @Part("tipo_protecao") tipo_protecao: RequestBody,
        @Part("validade_epi") validade_epi: RequestBody,
        @Part("tempo_uso") tempo_uso: RequestBody,
        @Part("numero_ca") numero_ca: RequestBody,
    ): Response<Epi>

    @DELETE("delete_epis/{epi_id}")
    suspend fun deleteEpiById(@Path("epi_id")id: Int): Response<Epi>
}