package ru.kiz.developer.abdulaev.kinodisplay.domain

import ru.kiz.developer.abdulaev.kinodisplay.core.Mapper

data class Movie(
    private val name: String,
    private val description: String,
    private val avatarUrl: String
) {
    fun <R> map(mapper: Mapper.MovieMapper<R>): R {
        return mapper.map(name, description, avatarUrl)
    }
}