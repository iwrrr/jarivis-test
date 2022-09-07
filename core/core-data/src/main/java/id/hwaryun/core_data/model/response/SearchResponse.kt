package id.hwaryun.core_data.model.response

import com.google.gson.annotations.SerializedName

data class SearchResponse<T>(

    @SerializedName("expression")
    val expression: String? = null,

    @SerializedName("searchType")
    val searchType: String? = null,

    @SerializedName("errorMessage")
    val errorMessage: String? = null,

    @SerializedName("results")
    val results: List<T>? = null
)