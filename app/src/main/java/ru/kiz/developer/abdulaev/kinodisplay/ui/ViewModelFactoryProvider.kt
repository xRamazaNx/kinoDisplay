package ru.kiz.developer.abdulaev.kinodisplay.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.kiz.developer.abdulaev.kinodisplay.App
import ru.kiz.developer.abdulaev.kinodisplay.data.NetworkDataStore
import ru.kiz.developer.abdulaev.kinodisplay.domain.Interactor
import ru.kiz.developer.abdulaev.kinodisplay.ui.paging.PagingRepository

interface ViewModelFactoryProvider {
    fun movieViewModelFactory(apiKey: String): ViewModelProvider.Factory

    class Base(
        private val app: App
    ) : ViewModelFactoryProvider {
        override fun movieViewModelFactory(apiKey: String): ViewModelProvider.Factory {
            return MovieViewModelFactory(
                PagingRepository(
                    Interactor.Base(
                        NetworkDataStore.Base(
                            apiKey,
                            app.movieServiceApi
                        )
                    )
                )
            )
        }

    }
}

@Suppress("UNCHECKED_CAST")
private class MovieViewModelFactory(
    private val pagingRepository: PagingRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(pagingRepository) as T
    }
}
