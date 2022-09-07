package id.hwaryun.core_data.model.response

import com.google.gson.annotations.SerializedName
import id.hwaryun.core_data.model.viewparam.MovieDetailViewParam

data class MovieDetailResponse(

    @SerializedName("keywords")
    val keywords: String? = null,

    @SerializedName("year")
    val year: String? = null,

    @SerializedName("directors")
    val directors: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("tvEpisodeInfo")
    val tvEpisodeInfo: Any? = null,

    @SerializedName("imDbRating")
    val imDbRating: String? = null,

    @SerializedName("trailer")
    val trailer: Any? = null,

    @SerializedName("runtimeStr")
    val runtimeStr: String? = null,

    @SerializedName("plotLocal")
    val plotLocal: String? = null,

    @SerializedName("companies")
    val companies: String? = null,

    @SerializedName("plot")
    val plot: String? = null,

    @SerializedName("genres")
    val genres: String? = null,

    @SerializedName("ratings")
    val ratings: Any? = null,

    @SerializedName("imDbRatingVotes")
    val imDbRatingVotes: String? = null,

    @SerializedName("tvSeriesInfo")
    val tvSeriesInfo: Any? = null,

    @SerializedName("id")
    val id: String? = null,

    @SerializedName("wikipedia")
    val wikipedia: Any? = null,

    @SerializedName("fullCast")
    val fullCast: Any? = null,

    @SerializedName("image")
    val image: String? = null,

    @SerializedName("fullTitle")
    val fullTitle: String? = null,

    @SerializedName("images")
    val images: Any? = null,

    @SerializedName("runtimeMins")
    val runtimeMins: String? = null,

    @SerializedName("languages")
    val languages: String? = null,

    @SerializedName("releaseDate")
    val releaseDate: String? = null,

    @SerializedName("posters")
    val posters: Any? = null,

    @SerializedName("errorMessage")
    val errorMessage: Any? = null,

    @SerializedName("metacriticRating")
    val metacriticRating: String? = null,

    @SerializedName("writers")
    val writers: String? = null,

    @SerializedName("stars")
    val stars: String? = null,

    @SerializedName("countries")
    val countries: String? = null,

    @SerializedName("plotLocalIsRtl")
    val plotLocalIsRtl: Boolean? = null,

    @SerializedName("originalTitle")
    val originalTitle: String? = null,

    @SerializedName("awards")
    val awards: String? = null,

    @SerializedName("tagline")
    val tagline: Any? = null,

    @SerializedName("contentRating")
    val contentRating: String? = null,
) {

    fun toViewParam(): MovieDetailViewParam = MovieDetailViewParam(
        keywords = keywords,
        year = year,
        directors = directors,
        title = title,
        type = type,
        tvEpisodeInfo = tvEpisodeInfo,
        imDbRating = imDbRating,
        trailer = trailer,
        runtimeStr = runtimeStr,
        plotLocal = plotLocal,
        companies = companies,
        plot = plot,
        genres = genres,
        ratings = ratings,
        imDbRatingVotes = imDbRatingVotes,
        tvSeriesInfo = tvSeriesInfo,
        id = id,
        wikipedia = wikipedia,
        fullCast = fullCast,
        image = image,
        fullTitle = fullTitle,
        images = images,
        runtimeMins = runtimeMins,
        languages = languages,
        releaseDate = releaseDate,
        posters = posters,
        errorMessage = errorMessage,
        metacriticRating = metacriticRating,
        writers = writers,
        stars = stars,
        countries = countries,
        plotLocalIsRtl = plotLocalIsRtl,
        originalTitle = originalTitle,
        awards = awards,
        tagline = tagline,
        contentRating = contentRating,
    )
}