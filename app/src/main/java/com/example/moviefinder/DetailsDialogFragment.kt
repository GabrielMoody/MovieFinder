//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.fragment.app.DialogFragment
//import com.example.moviefinder.MovieAPI
//import com.example.moviefinder.R
//import com.squareup.picasso.Picasso
//
//class MovieDetailsFragment : DialogFragment() {
//
//    companion object {
//        private const val ARG_MOVIE = "arg_movie"
//
//        fun newInstance(movie: MovieAPI.MovieResponse): MovieDetailsFragment {
//            val fragment = MovieDetailsFragment()
//            val args = Bundle()
//            args.putParcelable(ARG_MOVIE, movie)
//            fragment.arguments = args
//            return fragment
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.movie_details_popup, container, false)
//
//        val movie = arguments?.getParcelable<MovieAPI.MovieResponse>("movie")
//
//        val titleTextView: TextView = view.findViewById(R.id.detailsTitle)
//        val releaseDateTextView: TextView = view.findViewById(R.id.detailsYear)
//        val overviewTextView: TextView = view.findViewById(R.id.detailsSummary)
//        val posterImageView: ImageView = view.findViewById(R.id.detailsPoster)
//
//        titleTextView.text = movie?.title
//        releaseDateTextView.text = movie?.release_date
//        overviewTextView.text = movie?.overview
//
//        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movie?.poster_path).into(posterImageView)
//
//        return view
//    }
//}
