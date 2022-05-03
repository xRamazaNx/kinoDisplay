package ru.kiz.developer.abdulaev.kinodisplay.ui.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kiz.developer.abdulaev.kinodisplay.core.FetchMoviesException
import ru.kiz.developer.abdulaev.kinodisplay.domain.Interactor
import ru.kiz.developer.abdulaev.kinodisplay.domain.Movie

class MoviePagingSource(
    private val interactor: Interactor
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)
                ?.prevKey
                ?.plus(20)
                ?: state
                    .closestPageToPosition(anchorPosition)
                    ?.nextKey
                    ?.minus(20)
        }

    }

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Movie> = withContext(Dispatchers.IO) {
        val position = params.key ?: 0
        try {
            val response = interactor.nextMovies(position)
            LoadResult.Page(
                data = response,
                prevKey = if (position == 0) null else position - 1,
                nextKey = position + 20
            )
        } catch (ex: FetchMoviesException) {
            LoadResult.Error(ex)
        }
    }
}