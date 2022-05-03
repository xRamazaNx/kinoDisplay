package ru.kiz.developer.abdulaev.kinodisplay.domain

import ru.kiz.developer.abdulaev.kinodisplay.core.Mapper

class ToDomainMapper : Mapper.MovieMapper<Movie> {
    override fun map(name: String, description: String, avatarUrl: String): Movie {
        return Movie(name, description, avatarUrl)
    }
}