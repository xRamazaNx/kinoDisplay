package ru.kiz.developer.abdulaev.kinodisplay.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import ru.kiz.developer.abdulaev.kinodisplay.R
import ru.kiz.developer.abdulaev.kinodisplay.core.Mapper
import ru.kiz.developer.abdulaev.kinodisplay.databinding.MovieItemBinding
import ru.kiz.developer.abdulaev.kinodisplay.domain.Movie

class MovieAdapter : PagingDataAdapter<Movie, MovieAdapter.MovieHolder>(DiffUtilCallback()) {

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.movie_item, parent, false)
        return MovieHolder(view)
    }

    inner class MovieHolder(view: View) : ViewHolder(view), Mapper.MovieMapper<Unit> {
        private val binding = MovieItemBinding.bind(view)
        fun bind(item: Movie?) {
            item?.map(this)
        }

        override fun map(name: String, description: String, avatarUrl: String) {
            binding.name.text = name
            binding.description.text = description
            val poster = binding.poster
            Glide.with(poster)
                .load(avatarUrl)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(poster)
        }
    }
}