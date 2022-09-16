package com.example.movieplayer


enum class StatusEnum {               // There can be three possibility it must be loading ,success or the error
    LOADING,
    SUCCESS,
    ERROR                         // When the error occured there are three possible errorCode specify in enum
}

enum class ErrorCodeEnum {
    NO_DATA,
    NETWORK_ERROR,
    UNKNOWN_ERROR
}

data class LoadingStatus(val status: StatusEnum, val errorCode: ErrorCodeEnum?, val message: String?) {

    companion object {

        fun loading(): LoadingStatus {            // returns the instance of LoadingSrtatus with Status ->Loading
            return LoadingStatus(
                StatusEnum.LOADING,
                null,
                null
            )
        }

        fun success(errorCode: ErrorCodeEnum? = null, msg: String? = null): LoadingStatus {
            return LoadingStatus(
                StatusEnum.SUCCESS,
                errorCode,
                msg
            )
        }

        fun error(errorCode: ErrorCodeEnum, msg: String? = null): LoadingStatus {
            return LoadingStatus(
                StatusEnum.ERROR,
                errorCode,
                msg
            )
        }
    }
}