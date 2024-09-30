package com.itasoft.project.data.model


import com.google.gson.annotations.SerializedName


data class StokBarangItem(
    @SerializedName("id")
    val id: String?,
    @SerializedName("c_expired")
    val cExpired: String?,
    @SerializedName("c_kategori")
    val cKategori: String?,
    @SerializedName("c_nama_barang")
    val cNamaBarang: String?,
    @SerializedName("c_stok")
    val cStok: Int?
)