package com.keltapps.musicalzodiacpiano.info.presentation.viewmodel

import androidx.lifecycle.LiveData

interface InfoViewModel {
    val image: LiveData<Int>
    val title: LiveData<String>
    val body: LiveData<String>
}