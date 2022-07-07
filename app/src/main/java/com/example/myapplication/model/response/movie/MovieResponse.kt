package com.example.myapplication.model.response.movie

import androidx.annotation.Keep
import com.efg.valu.sales.model.response.BaseModel
import com.google.gson.annotations.SerializedName

@Keep
data class MovieResponse(
    @SerializedName("description")
    val description: String? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("page")
    val page: Int? = null,

    @SerializedName("results")
    val results: ArrayList<MovieResultsResponse>? = null
) : BaseModel() {
    override fun getErrorValue(): ErrorPayload? {
        TODO("Not yet implemented")
    }
}

data class MovieResultsResponse(
    @SerializedName("adult")
    val adult: String? = null,

    @SerializedName("backdrop_path")
    val backdrop_path: String? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("media_type")
    val media_type: String? = null,

    @SerializedName("original_language")
    val original_language: String? = null,

    @SerializedName("original_title")
    val original_title: String? = null,

    @SerializedName("overview")
    val overview: String? = null,

    @SerializedName("popularity")
    val popularity: Double? = null,

    @SerializedName("poster_path")
    val poster_path: String? = null,

    @SerializedName("release_date")
    val release_date: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("video")
    val video: Boolean? = null,

    @SerializedName("vote_average")
    val vote_average: Double? = null,

    @SerializedName("vote_count")
    val vote_count: Int? = null
)
