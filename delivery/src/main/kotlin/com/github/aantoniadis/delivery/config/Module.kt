package com.github.aantoniadis.delivery.config

import com.github.aantoniadis.dataproviders.db.jpa.repositories.DBProductRepository
import com.github.aantoniadis.dataproviders.db.jpa.repositories.JpaProductRepository
import com.github.aantoniadis.delivery.rest.imp.ProductResourceImp
import com.github.aantoniadis.delivery.usecases.core.UseCaseExecutor
import com.github.aantoniadis.delivery.usecases.core.gateways.ProductRepository
import com.github.aantoniadis.delivery.usecases.core.product.CreateProductUseCase
import com.github.aantoniadis.delivery.usecases.core.product.GetProductByIdUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Module {
    @Bean
    fun productsResourceImp(
        useCaseExecutor: UseCaseExecutor,
        getProductByIdUseCase: GetProductByIdUseCase,
        createProductUseCase: CreateProductUseCase
    ) = ProductResourceImp(useCaseExecutor, getProductByIdUseCase, createProductUseCase)

    @Bean
    fun useCaseExecutor() = UseCaseExecutor()

    @Bean
    fun getProductByIdUseCase(productRepository: ProductRepository) = GetProductByIdUseCase(productRepository)

    @Bean
    fun createProductUseCase(productRepository: ProductRepository) = CreateProductUseCase(productRepository)

    @Bean
    fun productRepository(dbProductRepository: DBProductRepository) = JpaProductRepository(dbProductRepository)
}