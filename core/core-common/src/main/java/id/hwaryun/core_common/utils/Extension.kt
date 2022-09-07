package id.hwaryun.core_common.utils

import android.content.Context
import id.hwaryun.core_ui.R
import id.hwaryun.core_common.exception.ApiErrorException
import id.hwaryun.core_common.exception.NoInternetConnectionException

fun Context.getErrorMessage(exception: Exception): String {
    return when (exception) {
        is NoInternetConnectionException -> getString(R.string.message_error_no_internet)
        is ApiErrorException -> exception.message.orEmpty()
        else -> getString(R.string.message_error_unknown)
    }
}