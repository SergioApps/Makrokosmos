package com.keltapps.musicalzodiacpiano.song.presentation.list.adapter

import com.google.common.truth.Truth.assertThat
import com.keltapps.musicalzodiacpiano.song.domain.entity.Song
import com.keltapps.musicalzodiacpiano.song.domain.entity.ZodiacSign
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class BlockSongListAdapterTest {

    @Mock
    private lateinit var mockOldSong: Song
    @Mock
    private lateinit var mockNewSong: Song

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun diffCallBackAreItemsTheSame_should_returnTrue_when_titlesMatch() {
        `when`(mockOldSong.title).thenReturn("title")
        `when`(mockNewSong.title).thenReturn("title")

        val result = BlockSongListAdapter.DIFF_CALLBACK.areItemsTheSame(mockOldSong, mockNewSong)

        assertThat(result).isTrue()
    }

    @Test
    fun diffCallBackAreItemsTheSame_should_returnFalse_when_idsNotMatch() {
        `when`(mockOldSong.title).thenReturn("title1")
        `when`(mockNewSong.title).thenReturn("title2")

        val result = BlockSongListAdapter.DIFF_CALLBACK.areItemsTheSame(mockOldSong, mockNewSong)

        assertThat(result).isFalse()
    }

    @Test
    fun diffCallBackAreContentsTheSame_should_returnTrue_when_contentIsTheSame() {
        val oldPost = getPostWithUser()
        val newPost = getPostWithUser()

        val result = BlockSongListAdapter.DIFF_CALLBACK.areContentsTheSame(oldPost, newPost)

        assertThat(result).isTrue()
    }

    @Test
    fun diffCallBackAreContentsTheSame_should_returnFalse_when_contentIsNotTheSame() {
        val oldPost = getPostWithUser()
        val newPost = getOtherPostWithUser()

        val result = BlockSongListAdapter.DIFF_CALLBACK.areContentsTheSame(oldPost, newPost)

        assertThat(result).isFalse()
    }

    private fun getPostWithUser(): Song {
        return Song(
                "id",
                "title",
                "subTitle",
                ZodiacSign.Scorpio(""),
                60
        )
    }

    private fun getOtherPostWithUser(): Song {
        return Song(
                "id",
                "title",
                "subTitle",
                ZodiacSign.Aries(""),
                60
        )
    }
}