package com.github.aantoniadis.delivery.rest.api


data class ErrorDto(
    val errorCode: ErrorCodeDto?,
    val message: String?
)

enum class ErrorCodeDto {
    NOT_FOUND,
    VALIDATION_ERROR
}
