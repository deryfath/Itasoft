package com.itasoft.project.data.model

data class StokBarangResponse(
    val `data`: MutableList<StokBarangItem>,
    val size: Int,
    val total: Int
)