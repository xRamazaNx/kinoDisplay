package ru.kiz.developer.abdulaev.kinodisplay.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.kiz.developer.abdulaev.kinodisplay.app
import ru.kiz.developer.abdulaev.kinodisplay.databinding.ActivityMoviesBinding
import ru.kiz.developer.abdulaev.kinodisplay.ui.MovieAdapter
import ru.kiz.developer.abdulaev.kinodisplay.ui.MovieViewModel

class MovieViewerActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMoviesBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MovieViewModel> {
        app().viewModelFactoryProvider.movieViewModelFactory("Put the api-key here")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val recycler = binding.recycler
        recycler.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        recycler.addItemDecoration(DividerItemDecoration(this, VERTICAL))
        val movieAdapter = MovieAdapter()
        recycler.adapter = movieAdapter

        movieAdapter.addLoadStateListener {
            if (it.refresh is LoadState.Error)
                Toast.makeText(this, "Error loading movies", Toast.LENGTH_SHORT).show()
        }

        lifecycleScope.launch {
            viewModel.pagingDataFlow.collectLatest { pagingData ->
                movieAdapter.submitData(pagingData)
            }
        }
    }
}