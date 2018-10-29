package com.keltapps.makrokosmos.audio.client.domain.entity

sealed class PlayingState {
    object Stopped : PlayingState()
    object Playing : PlayingState()
    object Paused : PlayingState()
}