package id.hwaryun.core_data.model.response

import com.google.gson.annotations.SerializedName
import id.hwaryun.core_data.model.viewparam.BoxOfficeViewParam

data class BoxOfficeResponse(

	@SerializedName("image")
	val image: String? = null,

	@SerializedName("weeks")
	val weeks: String? = null,

	@SerializedName("gross")
	val gross: String? = null,

	@SerializedName("weekend")
	val weekend: String? = null,

	@SerializedName("rank")
	val rank: String? = null,

	@SerializedName("id")
	val id: String? = null,

	@SerializedName("title")
	val title: String? = null
) {

	fun toViewParam(): BoxOfficeViewParam = BoxOfficeViewParam(
		image = image,
		weeks = weeks,
		gross = gross,
		weekend = weekend,
		rank = rank,
		id = id,
		title = title
	)
}