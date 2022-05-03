package ru.kiz.developer.abdulaev.kinodisplay.ui

import androidx.recyclerview.widget.DiffUtil
import ru.kiz.developer.abdulaev.kinodisplay.domain.Movie

class DiffUtilCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        // это не правильно! тут должен быть ид
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}