package com.example.domain.util

sealed class NetworkFailureType {

    object NetworkConnectionError : NetworkFailureType()

    object GenericError : NetworkFailureType()
}