package com.example.frume.data

import com.example.frume.util.CategoryType

data class TempProduct(
    val productImgResourceId: Int,
    val productName: String,
    val productPrice: Int,
    val productDescription: String,
    val productCategory: CategoryType
)