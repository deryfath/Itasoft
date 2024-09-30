package com.itasoft.project.data.api

import com.itasoft.project.data.model.StokBarangResponse
import com.itasoft.project.data.model.StokBarangItem
import retrofit2.http.GET
import kotlinx.coroutines.Deferred
import retrofit2.http.Query


interface ApiService {

    @GET("/jw/api/list/list_formStokBarang")
    fun getListStokBarang(
        @Query("startOffset") startOffset:Int,
        @Query("pageSize") pageSize:Int
    ):Deferred<StokBarangResponse>
}