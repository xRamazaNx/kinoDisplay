package ru.kiz.developer.abdulaev.kinodisplay.data

import ru.kiz.developer.abdulaev.kinodisplay.core.FetchMoviesException
import ru.kiz.developer.abdulaev.kinodisplay.core.Mapper
import ru.kiz.developer.abdulaev.kinodisplay.data.api_objects.MovieInfo

sealed class Response {
    abstract fun <R> map(mapper: Mapper.MovieMapper<R>): List<R>

    class Successful(private val movieList: List<MovieInfo>) : Response() {
        override fun <R> map(mapper: Mapper.MovieMapper<R>): List<R> {
            return movieList.fold(mutableListOf()) { list, m ->
                list.add(
                    mapper.map(
                        m.display_title,
                        m.summary_short,
                        m.multimedia.src
                    )
                )
                list
            }
        }
    }

    class Error(private val errorMessage: String) : Response() {
        override fun <R> map(mapper: Mapper.MovieMapper<R>): List<R> {
            throw FetchMoviesException(errorMessage)
        }
    }
}