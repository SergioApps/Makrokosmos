package com.keltapps.musicalzodiacpiano.song.presentation.list.factory

import com.keltapps.musicalzodiacpiano.song.presentation.ZodiacSignViewModel
import com.keltapps.musicalzodiacpiano.song.presentation.list.MakrokosmosZodiacSignViewModel
import javax.inject.Inject

class MakrokosmosZodiacSignViewModelFactory @Inject constructor() : ZodiacSignViewModelFactory {
    override fun createViewModel(): ZodiacSignViewModel = MakrokosmosZodiacSignViewModel()
}