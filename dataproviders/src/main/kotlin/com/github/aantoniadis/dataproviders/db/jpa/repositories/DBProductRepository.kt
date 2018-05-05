package com.github.aantoniadis.dataproviders.db.jpa.repositories

import com.github.aantoniadis.dataproviders.db.jpa.entities.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository

interface DBProductRepository : JpaRepository<ProductEntity, String>