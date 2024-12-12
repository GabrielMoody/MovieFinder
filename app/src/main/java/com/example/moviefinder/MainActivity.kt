package com.example.moviefinder

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val url = "https://api.themoviedb.org/3/"

    private lateinit var recyclerView: RecyclerView
    private lateinit var search: EditText
    private lateinit var button: Button
    private lateinit var text: TextView
    private lateinit var filter: Spinner

    private var movieList: List<MovieAPI.MovieResponse> = emptyList()


    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(url)
        .build()
        .create(MovieAPI.MovieAPIService::class.java)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        search = findViewById(R.id.editText)
        button = findViewById(R.id.button)
        text = findViewById(R.id.trending)
        filter = findViewById(R.id.filterSpinner)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 3)

        getTrendingMovies()

        val items = listOf("", "A-Z", "Z-A", "Rating")

        filter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = items[position]
                filterMovies(selectedItem)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("Nothing selected")
            }
        }

        button.setOnClickListener{
            searchMovie(search.text.toString())
        }
    }

    private fun filterMovies(filterType: String) {
        if (movieList.isEmpty()) return

        val sortedList = when (filterType) {
            "A-Z" -> movieList.sortedBy { it.title }
            "Z-A" -> movieList.sortedByDescending { it.title }
            "Rating" -> movieList.sortedByDescending { it.popularity }
            else -> movieList
        }

        recyclerView.adapter = MovieAdapter(sortedList)
    }

    private fun getTrendingMovies() {
        val response = retrofit.getTrendingMovies()

        response.enqueue(object : Callback<MovieAPI.Movies> {
            override fun onResponse(
                call: Call<MovieAPI.Movies>,
                response: Response<MovieAPI.Movies>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()!!
                    movieList = responseBody.results!!

                    recyclerView.adapter = MovieAdapter(movieList)

                    Log.d("api", movieList.toString())
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Something happened!. Please try again",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d("error", response.toString())
                }
            }

            override fun onFailure(call: Call<MovieAPI.Movies>, t: Throwable) {
               Log.d("error", t.toString())
            }
        })
    }

    private fun searchMovie(query: String) {
        val response = retrofit.searchMovie(query)

        response.enqueue(object : Callback<MovieAPI.Movies> {
            override fun onResponse(
                call: Call<MovieAPI.Movies>,
                response: Response<MovieAPI.Movies>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()!!
                    movieList = responseBody.results!!
                    recyclerView.adapter = MovieAdapter(movieList)
                    text.visibility = View.INVISIBLE

                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Something happened!. Please try again",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<MovieAPI.Movies>, t: Throwable) {
                Log.d("error", t.toString())
            }
        })
    }
}
