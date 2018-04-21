package com.keltapps.makrokosmos.presentation.songList.model

import com.keltapps.makrokosmos.domain.entity.Song

class SongListItem(val song: Song) : CDListItem(CDListItem.TYPE_SONG)