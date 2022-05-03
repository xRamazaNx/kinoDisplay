package ru.kiz.developer.abdulaev.kinodisplay.ui.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.kiz.developer.abdulaev.kinodisplay.domain.Interactor
import ru.kiz.developer.abdulaev.kinodisplay.domain.Movie

class PagingRepository(
    private val interactor: Interactor
) {
    fun pagingDataFlow(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(interactor) }
        ).flow
    }
}