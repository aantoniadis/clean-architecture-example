package com.github.aantoniadis.delivery.usecases.core.gateways

import com.github.aantoniadis.delivery.usecases.core.product.CreateProductUseCase
import com.github.aantoniadis.delivery.usecases.core.product.GetProductByIdUseCase

interface ProductRepository : GetProductByIdUseCase.ProductRepository, CreateProductUseCase.ProductRepository