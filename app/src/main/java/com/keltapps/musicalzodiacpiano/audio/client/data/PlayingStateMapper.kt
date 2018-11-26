package com.keltapps.musicalzodiacpiano.audio.client.data

import com.keltapps.musicalzodiacpiano.audio.client.domain.entity.PlayingState

interface PlayingStateMapper {
    fun map(payingState: Int): PlayingState
}