package com.keltapps.musicalzodiacpiano.song.domain.entity

sealed class Element {
    object Fire : Element()
    object Earth : Element()
    object Air : Element()
    object Water : Element()
}