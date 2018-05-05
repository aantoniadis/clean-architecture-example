package com.github.aantoniadis.delivery.usecases.core.product

import com.github.aantoniadis.core.entities.Product
import com.github.aantoniadis.core.entities.ProductCode
import com.github.aantoniadis.delivery.usecases.core.UseCase
import com.github.aantoniadis.delivery.usecases.core.exceptions.ValidationException
import java.math.BigDecimal
import java.time.LocalDateTime

class CreateProductUseCase(private val productRepository: ProductRepository) : UseCase<Product, Unit> {
    override fun execute(product: Product) {
        if (product.price < BigDecimal.ZERO)
            throw ValidationException("Produce price should not be negative")
        productRepository.save(product.copy(createdAt = LocalDateTime.now()))
    }

    interface ProductRepository {
        fun existsProductCode(productCode: ProductCode): Boolean
        fun save(product: Product)
    }
}