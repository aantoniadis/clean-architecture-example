package com.github.aantoniadis.core.entities

import java.math.BigDecimal
import java.time.LocalDateTime

data class Product(
    val code: ProductCode,
    val description: String,
    val price: BigDecimal,
    val createdAt: LocalDateTime?
)

data class ProductCode(val value: String)