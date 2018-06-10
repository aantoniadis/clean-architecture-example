package com.github.aantoniadis.dataproviders.db.jpa.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@Configuration
@EntityScan(basePackages = ["com.github.aantoniadis.dataproviders.db.jpa.entities"])
@EnableJpaRepositories(basePackages = ["com.github.aantoniadis.dataproviders.db.jpa.repositories"])
class DBConfig