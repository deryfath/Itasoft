package com.itasoft.project.data

import com.itasoft.project.data.api.ApiService
import com.itasoft.project.data.model.StokBarangResponse

class DataRepository(private val apiDataRepository: ApiService) {

    suspend fun getListStokBarang(curPage: Int, pageSize: Int): StokBarangResponse {
        val data = apiDataRepository.getListStokBarang(curPage, pageSize).await()
        println("datana $data")
        return  data
    }

}