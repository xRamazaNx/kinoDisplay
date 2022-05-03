package ru.kiz.developer.abdulaev.kinodisplay.data

interface NetworkDataStore {
    fun fetchMovies(nextFrom: Int): Response

    class Base(
        private val apiKey: String,
        private val api: MovieServiceApi
    ) : NetworkDataStore {
        override fun fetchMovies(nextFrom: Int): Response {
            val response = api.fetchMovies(apiKey, nextFrom).execute()
            return if (response.isSuccessful)
                Response.Successful(response.body()?.results ?: emptyList())
            else
                Response.Error(response.message())
        }
    }
}