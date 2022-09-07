package id.hwaryun.core_data.model.response

import com.google.gson.annotations.SerializedName
import id.hwaryun.core_data.model.viewparam.MovieViewParam

data class MovieResponse(

    @SerializedName("image")
    val image: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("id")
    val id: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("resultType")
    val resultType: String? = null
) {

    fun toViewParam(): MovieViewParam = MovieViewParam(
        image = image,
        description = description,
        id = id,
        title = title,
        resultType = resultType
    )
}