package com.keltapps.makrokosmos.song.domain.entity

data class Song(
        val title: String,
        val subTitle: String?,
        val zodiacSign: ZodiacSign
)
//val durationInSeconds: Int) TODO add duration