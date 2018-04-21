package com.keltapps.makrokosmos.presentation.songList.model

abstract class CDListItem(val type: Int) {

    companion object {
        const val TYPE_TITLE = 0
        const val TYPE_SONG = 1
    }
}