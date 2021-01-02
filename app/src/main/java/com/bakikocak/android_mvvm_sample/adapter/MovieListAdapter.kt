package com.bakikocak.android_mvvm_sample.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bakikocak.android_mvvm_sample.R
import com.bakikocak.android_mvvm_sample.data.model.Movie
import com.bakikocak.android_mvvm_sample.utils.IMAGE_BASE_URL
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_item_layout.view.*

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.movie_item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var movie = differ.currentList[position]

        holder.itemView.apply {

            Glide.with(this).load(IMAGE_BASE_URL + movie.poster_path).into(movie_poster_iv)
            movie_title_tv.text = movie.title

            setOnClickListener {
                onItemClickListener?.let { it(movie) }
            }
        }

    }

    override fun getItemCount(): Int = differ.currentList.size

    private var onItemClickListener: ((Movie) -> Unit)? = null

    fun setOnItemClickListener(listener: (Movie) -> Unit) {
        onItemClickListener = listener
    }

}