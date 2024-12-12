package com.example.moviefinder

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MovieAdapter(private var movieList: List<MovieAPI.MovieResponse>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val moviePoster: ImageView = itemView.findViewById(R.id.posterImageView)
        val title: TextView = itemView.findViewById(R.id.title)
        val button: Button = itemView.findViewById(R.id.show_details_movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.title.text = movie.title
        holder.button.setOnClickListener{
            val popUp = PopUpWindow.newInstance(movie)
            popUp.show((holder.itemView.context as AppCompatActivity).supportFragmentManager, "PopUpWindow")
        }
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movie.poster_path).into(holder.moviePoster)
    }

    override fun getItemCount(): Int = movieList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateMovies(newMovies: List<MovieAPI.MovieResponse>) {
        movieList = newMovies
        notifyDataSetChanged()
    }
}

