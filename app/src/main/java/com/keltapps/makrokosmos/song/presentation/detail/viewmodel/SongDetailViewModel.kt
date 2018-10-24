package com.keltapps.makrokosmos.song.presentation.detail.viewmodel

import com.keltapps.makrokosmos.song.presentation.ZodiacSignViewModel

interface SongDetailViewModel {
    fun initialize(songId: String)
    val zodiacSignViewModel: ZodiacSignViewModel
}