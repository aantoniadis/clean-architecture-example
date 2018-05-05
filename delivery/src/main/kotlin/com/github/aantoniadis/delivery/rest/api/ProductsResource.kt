package com.github.aantoniadis.delivery.rest.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CompletionStage

@RestController
@RequestMapping("/products")
interface ProductsResource {
    @GetMapping("/{code}")
    fun getProductByCode(@PathVariable("code") code: String): CompletionStage<ProductDto>

    @PostMapping
    fun createProduct(@RequestBody productDto: ProductDto): CompletionStage<ResponseEntity<Unit>>
}