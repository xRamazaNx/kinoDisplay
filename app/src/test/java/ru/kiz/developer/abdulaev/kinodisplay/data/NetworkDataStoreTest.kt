package ru.kiz.developer.abdulaev.kinodisplay.data

import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kiz.developer.abdulaev.kinodisplay.domain.Interactor

class NetworkDataStoreTest {

    private val apiKey = "cu5ANoYlrOH5FPMDFoVNxSFKnqwuk8AI"

    @Test
    fun fetchMovies() {
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://developer.nytimes.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val movieServiceApi = retrofit.create(MovieServiceApi::class.java)

        val store = NetworkDataStore.Base(apiKey, movieServiceApi)
        val interactor = Interactor.Base(store)
        val firstMovies = interactor.nextMovies(0)
        val nextMovies = interactor.nextMovies(firstMovies.size)

        assert(firstMovies.size == nextMovies.size)

        var isDiff = false
        firstMovies.forEachIndexed { index, movie ->
            if (movie != nextMovies[index]){
                isDiff = true
                return@forEachIndexed
            }
        }
        assert(isDiff)
    }
}