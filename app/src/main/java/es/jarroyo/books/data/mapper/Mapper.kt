package es.jarroyo.books.data.mapper

import es.jarroyo.books.data.exception.MapperException


internal interface Mapper<in I, out O> {

    @Throws(MapperException::class)
    fun map(input: I): O
}
