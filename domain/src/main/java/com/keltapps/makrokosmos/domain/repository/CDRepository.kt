package com.keltapps.makrokosmos.domain.repository

import com.keltapps.makrokosmos.domain.model.CD

interface CDRepository {
    fun getCD(): CD
}