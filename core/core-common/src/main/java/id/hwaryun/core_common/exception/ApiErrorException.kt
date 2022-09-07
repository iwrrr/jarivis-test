package id.hwaryun.core_common.exception

class ApiErrorException(
    override val message: String? = null,
    val httpCode: Int? = null
) : Exception()