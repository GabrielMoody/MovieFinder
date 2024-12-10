package com.example.moviefinder

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.http.Headers

class MovieAPI {

    @Parcelize
    data class MovieResponse(
        var adult: Boolean,
        var backdrop_path: String,
        var genre_ids: ArrayList<Int> = arrayListOf(),
        var id: Int,
        var original_language: String,
        var original_title: String,
        var overview: String,
        var popularity: Double,
        var poster_path: String,
        var release_date: String,
        var title: String,
        var video: Boolean,
        var vote_average: Double,
        var vote_count: Int
    ) : Parcelable

    @Parcelize
    data class Movies(
        val page: Int?,
        val results: List<MovieResponse>?
    ) : Parcelable

    interface MovieAPIService{
        @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlNWIwM2NkNmE1ZWM0MjI2OWE2NGM4ZTVmMWU0MzVhMyIsIm5iZiI6MTczMjg1OTY5Mi42NzQwMSwic3ViIjoiNjc0OTU2YTU2YTNmNWQwNWE3ZGM3MjVmIiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.fZpeth7JeWz-Yt7EX7BzKryR0vAvecxMuvl_BzDQsbs")
        @GET("search/movie")
        fun searchMovie(@Query("query") name: String): Call<Movies>

        @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlNWIwM2NkNmE1ZWM0MjI2OWE2NGM4ZTVmMWU0MzVhMyIsIm5iZiI6MTczMjg1OTY5Mi42NzQwMSwic3ViIjoiNjc0OTU2YTU2YTNmNWQwNWE3ZGM3MjVmIiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.fZpeth7JeWz-Yt7EX7BzKryR0vAvecxMuvl_BzDQsbs")
        @GET("trending/movie/day")
        fun getTrendingMovies(): Call<Movies>
    }
}