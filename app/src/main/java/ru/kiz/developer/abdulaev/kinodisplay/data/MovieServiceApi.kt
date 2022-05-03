package ru.kiz.developer.abdulaev.kinodisplay.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.kiz.developer.abdulaev.kinodisplay.data.api_objects.MovieResult

private const val moviesLink = "https://api.nytimes.com/svc/movies/v2/reviews/all.json"

interface MovieServiceApi {
    @GET(moviesLink)
    fun fetchMovies(@Query("api-key") key: String, @Query("offset") offset: Int): Call<MovieResult>
}