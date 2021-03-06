package com.keltapps.musicalzodiacpiano.song.domain.entity

data class Song(
        val id: String,
        val title: String,
        val subTitle: String?,
        val zodiacSign: ZodiacSign,
        val durationInSeconds: Int
)