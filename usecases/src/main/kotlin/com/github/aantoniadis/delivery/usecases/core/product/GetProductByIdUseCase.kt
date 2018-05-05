package com.github.aantoniadis.delivery.usecases.core.product

import com.github.aantoniadis.core.entities.Product
import com.github.aantoniadis.core.entities.ProductCode
import com.github.aantoniadis.delivery.usecases.core.UseCase
import com.github.aantoniadis.delivery.usecases.core.exceptions.NotFoundException

class GetProductByIdUseCase(private val productRepository: ProductRepository) :
    UseCase<ProductCode, Product> {
    override fun execute(productCode: ProductCode) =
        productRepository.getByProductCode(productCode) ?: throw NotFoundException("No product for code: $productCode")

    interface ProductRepository {
        fun getByProductCode(productCode: ProductCode): Product?
    }
}