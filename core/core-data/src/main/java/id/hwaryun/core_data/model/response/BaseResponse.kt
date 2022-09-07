package id.hwaryun.core_data.model.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(

	@SerializedName("errorMessage")
	val errorMessage: String? = null,

	@SerializedName("items")
	val items: List<T>? = null
)