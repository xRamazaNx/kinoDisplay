package ru.kiz.developer.abdulaev.kinodisplay.domain

import ru.kiz.developer.abdulaev.kinodisplay.data.NetworkDataStore

interface Interactor {
    fun nextMovies(nextFrom:Int): List<Movie>

    class Base(
        private val store: NetworkDataStore
    ) : Interactor {
        override fun nextMovies(nextFrom: Int): List<Movie> {
            return store.fetchMovies(nextFrom).map(ToDomainMapper())
        }
    }
}