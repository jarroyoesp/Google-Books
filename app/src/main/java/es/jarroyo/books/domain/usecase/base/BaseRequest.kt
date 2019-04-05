package es.jarroyo.books.domain.usecase.base

interface BaseRequest {
    fun validate(): Boolean
}
