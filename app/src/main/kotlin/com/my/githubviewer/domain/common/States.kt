package com.my.githubviewer.domain.common

sealed class State {

    object Loading : State()
    object HasData : State()
    object NoData : State()

    sealed class Failure : State() {

        object NotInternetConnected : Failure()
        object NetworkConnection : Failure()
        object ServerError : Failure()

        abstract class FeatureFailure: Failure()

        data class CustomFailure(val exception: Throwable) : FeatureFailure()

    }
}