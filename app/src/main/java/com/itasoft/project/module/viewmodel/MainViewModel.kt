package com.itasoft.project.module.viewmodel

import androidx.lifecycle.MutableLiveData
import com.itasoft.project.data.DataRepository
import com.itasoft.project.data.model.StokBarangResponse
import kotlinx.coroutines.launch

class MainViewModel(private val repository: DataRepository):AbstractViewModel() {

    val stokLiveData = MutableLiveData<StokBarangResponse>()

    fun getListStokBarang(curPage: Int, pageSize: Int = 10){
        launch {
            try {
                setLoading()
                val dataNews = repository.getListStokBarang(curPage, pageSize)
                stokLiveData.postValue(dataNews)

            }catch (t:Throwable){
                println("ERROR COROUTINE "+t.message)

                setError(t)
            }finally {
                setLoading(false)
            }
        }
    }
}