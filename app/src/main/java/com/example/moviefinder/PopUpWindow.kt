package com.example.moviefinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PopUpWindow.newInstance] factory method to
 * create an instance of this fragment.
 */
class PopUpWindow : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pop_up_window, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dialog = dialog
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val titleTextView: TextView = view.findViewById(R.id.detailsTitle)
        val releaseDateTextView: TextView = view.findViewById(R.id.detailsYear)
        val overviewTextView: TextView = view.findViewById(R.id.detailsSummary)
        val posterImageView: ImageView = view.findViewById(R.id.detailsPoster)

        titleTextView.text = arguments?.getString("movie_title")
        releaseDateTextView.text = arguments?.getString("movie_year")
        overviewTextView.text = arguments?.getString("movie_summary")

        Picasso.get().load("https://image.tmdb.org/t/p/w500" + arguments?.getString("movie_poster")).into(posterImageView)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PopUpWindow.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(movie: MovieAPI.MovieResponse): PopUpWindow{
            val fragment = PopUpWindow()
            val args = Bundle().apply {
                putString("movie_title", movie.title)
                putString("movie_poster", movie.poster_path)
                putString("movie_year", movie.release_date)
                putString("movie_summary", movie.overview)
            }
            fragment.arguments = args
            return fragment
        }
    }
}