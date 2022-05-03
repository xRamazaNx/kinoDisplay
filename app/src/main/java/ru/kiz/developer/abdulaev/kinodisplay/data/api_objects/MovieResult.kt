package ru.kiz.developer.abdulaev.kinodisplay.data.api_objects

data class MovieResult(
    val copyright: String,
    val has_more: Boolean,
    val num_results: Int,
    val results: List<MovieInfo>,
    val status: String
)