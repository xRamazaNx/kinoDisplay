package ru.kiz.developer.abdulaev.kinodisplay.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import ru.kiz.developer.abdulaev.kinodisplay.domain.Movie
import ru.kiz.developer.abdulaev.kinodisplay.ui.paging.PagingRepository

class MovieViewModel(
    private val pagingRepository: PagingRepository
) : ViewModel() {
    private fun searchMovie(): Flow<PagingData<Movie>> = pagingRepository.pagingDataFlow()
    val pagingDataFlow = flow {
        searchMovie().collect {
            emit(it)
        }
    }.cachedIn(viewModelScope)
}