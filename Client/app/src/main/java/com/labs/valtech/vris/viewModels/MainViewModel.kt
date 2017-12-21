package com.labs.valtech.vris.viewModels

/**
 * Created by marvin.brouwer on 21-12-2017.
 */
data class MainViewModel(
        var roomName: String = ""
) {
    val Valid: Boolean
        get() {
            return !roomName.isNullOrEmpty()
        }
}