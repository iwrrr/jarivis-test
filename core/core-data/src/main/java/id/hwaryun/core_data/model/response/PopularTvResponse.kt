package id.hwaryun.core_data.model.response

import com.google.gson.annotations.SerializedName
import id.hwaryun.core_data.model.viewparam.PopularTvViewParam

data class PopularTvResponse(

    @SerializedName("imDbRating")
    val imDbRating: String? = null,

    @SerializedName("image")
    val image: String? = null,

    @SerializedName("fullTitle")
    val fullTitle: String? = null,

    @SerializedName("imDbRatingCount")
    val imDbRatingCount: String? = null,

    @SerializedName("year")
    val year: String? = null,

    @SerializedName("rank")
    val rank: String? = null,

    @SerializedName("id")
    val id: String? = null,

    @SerializedName("rankUpDown")
    val rankUpDown: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("crew")
    val crew: String? = null
) {

    fun toViewParam(): PopularTvViewParam = PopularTvViewParam(
        imDbRating = imDbRating,
        image = image,
        fullTitle = fullTitle,
        imDbRatingCount = imDbRatingCount,
        year = year,
        rank = rank,
        id = id,
        rankUpDown = rankUpDown,
        title = title,
        crew = crew
    )
}