package com.keltapps.makrokosmos.song.presentation.list.factory

import com.keltapps.makrokosmos.song.presentation.ZodiacSignViewModel
import com.keltapps.makrokosmos.song.presentation.list.MakrokosmosZodiacSignViewModel
import javax.inject.Inject

class MakrokosmosZodiacSignViewModelFactory @Inject constructor() : ZodiacSignViewModelFactory {
    override fun createViewModel(): ZodiacSignViewModel = MakrokosmosZodiacSignViewModel()
}