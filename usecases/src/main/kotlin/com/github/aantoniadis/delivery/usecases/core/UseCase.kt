package com.github.aantoniadis.delivery.usecases.core

import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

interface UseCase<in Request, out Response> {
    fun execute(request: Request): Response
}

class UseCaseExecutor {

    operator fun <RequestDto, ResponseDto, Request, Response> invoke(
        useCase: UseCase<Request, Response>,
        requestDto: RequestDto,
        requestConverter: (RequestDto) -> Request,
        responseConverter: (Response) -> ResponseDto
    ): CompletionStage<ResponseDto> =
        CompletableFuture
            .supplyAsync { requestConverter(requestDto) }
            .thenApplyAsync { useCase.execute(it) }
            .thenApplyAsync { responseConverter(it) }

    operator fun <RequestDto, Request> invoke(
        useCase: UseCase<Request, Unit>,
        requestDto: RequestDto,
        requestConverter: (RequestDto) -> Request
    ) =
        invoke(useCase, requestDto, requestConverter, {})

    operator fun <Request> invoke(
        useCase: UseCase<Request, Unit>,
        requestDto: Request
    ) =
        invoke(useCase, requestDto, { it })

    operator fun invoke(useCase: UseCase<Unit, Unit>) =
        invoke(useCase, Unit, { })

    operator fun <ResponseDto, Response> invoke(
        useCase: UseCase<Unit, Response>,
        responseConverter: (Response) -> ResponseDto
    ) =
        invoke(useCase, Unit, { }, responseConverter)

}