package com.github.aantoniadis.delivery.rest.imp

import com.github.aantoniadis.core.entities.ProductCode
import com.github.aantoniadis.delivery.rest.api.ProductDto
import com.github.aantoniadis.delivery.rest.api.ProductsResource
import com.github.aantoniadis.delivery.rest.api.toProduct
import com.github.aantoniadis.delivery.rest.api.toProductDto
import com.github.aantoniadis.delivery.usecases.core.UseCaseExecutor
import com.github.aantoniadis.delivery.usecases.core.product.CreateProductUseCase
import com.github.aantoniadis.delivery.usecases.core.product.GetProductByIdUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

class ProductResourceImp(
    private val useCaseExecutor: UseCaseExecutor,
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val createProductUseCase: CreateProductUseCase
) : ProductsResource {

    override fun getProductByCode(@PathVariable("code") code: String) =
        useCaseExecutor(
            useCase = getProductByIdUseCase,
            requestDto = code,
            requestConverter = { ProductCode(it) },
            responseConverter = { it.toProductDto() })

    override fun createProduct(@RequestBody productDto: ProductDto) =
        useCaseExecutor(
            useCase = createProductUseCase,
            requestDto = productDto,
            requestConverter = { it.toProduct() },
            responseConverter = { _ -> ResponseEntity<Unit>(HttpStatus.CREATED) })

}