package com.keltapps.makrokosmos.audio.client.data

import com.keltapps.makrokosmos.audio.client.domain.entity.PlayingState

interface PlayingStateMapper {
    fun map(payingState: Int): PlayingState
}