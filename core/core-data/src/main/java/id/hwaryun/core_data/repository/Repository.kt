package id.hwaryun.core_data.repository

import com.google.gson.Gson
import com.google.gson.JsonParseException
import id.hwaryun.core_common.base.BaseRepository
import id.hwaryun.core_data.model.response.BaseResponse
import okhttp3.ResponseBody
import javax.inject.Inject

open class Repository : BaseRepository() {

    @Inject
    lateinit var gson: Gson

    override fun <T> getErrorMessageFromApi(response: T): String {
        val responseBody = response as ResponseBody
        return try {
            val body = gson.fromJson(responseBody.string(), BaseResponse::class.java)
            body.errorMessage ?: "Error Api"
        } catch (e: JsonParseException) {
            "Error Api"
        }
    }
}