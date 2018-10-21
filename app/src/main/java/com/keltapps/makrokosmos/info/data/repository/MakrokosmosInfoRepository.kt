package com.keltapps.makrokosmos.info.data.repository

import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.base.resourceprovider.ResourceProvider
import com.keltapps.makrokosmos.info.domain.entity.Info
import com.keltapps.makrokosmos.info.domain.repository.InfoRepository
import io.reactivex.Observable
import javax.inject.Inject

class MakrokosmosInfoRepository @Inject constructor(
        private val resourceProvider: ResourceProvider
) : InfoRepository {
    override fun getAboutInfo(): Observable<Info> {
        return Observable.fromCallable {
            Info(
                    R.drawable.about,
                    resourceProvider.getString(R.string.infoAbout_title),
                    resourceProvider.getString(R.string.infoAbout_body)
            )
        }
    }

    override fun getAuthorInfo(): Observable<Info> {
        return Observable.fromCallable {
            Info(
                    R.drawable.author,
                    resourceProvider.getString(R.string.infoAuthor_title),
                    resourceProvider.getString(R.string.infoAuthor_body)
            )
        }
    }

    override fun getInterpreterInfo(): Observable<Info> {
        return Observable.fromCallable {
            Info(
                    R.drawable.interpreter,
                    resourceProvider.getString(R.string.infoInterpreter_title),
                    resourceProvider.getString(R.string.infoInterpreter_body)
            )
        }
    }
}