package com.keltapps.makrokosmos.presentation.songList.model

import com.keltapps.makrokosmos.domain.entity.Song

sealed class CDListItem

class SongListItem(val song: Song) : CDListItem()

class TitleListItem(val title: String) : CDListItem()