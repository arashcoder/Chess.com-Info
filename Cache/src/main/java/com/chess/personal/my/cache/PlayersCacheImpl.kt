package com.chess.personal.my.cache

import com.chess.personal.my.cache.db.PlayersDatabase
import com.chess.personal.my.cache.model.Config
import com.chess.personal.my.data.model.PlayerEntity
import com.chess.personal.my.data.repository.PlayersCache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class PlayersCacheImpl @Inject constructor(
        private val projectsDatabase: PlayersDatabase)
        //private val mapper: CachedPla)
    : PlayersCache {

    override fun clearPlayers(): Completable {
        return Completable.complete()
        //return Completable.defer {
            //projectsDatabase.cached().deleteProjects()
            //Completable.complete()
        //}
    }

    override fun savePlayers(projects: List<String>): Completable {
        return Completable.complete()
//        return Completable.defer {
//            projectsDatabase.cachedProjectsDao().insertProjects(
//                    projects.map { mapper.mapToCached(it) })
//            Completable.complete()
//        }
    }

    override fun getPlayers(countryISO: String): Single<List<String>> {
        return Single.just(listOf())
//        return projectsDatabase.cachedProjectsDao().getProjects()
//                .map {
//                    it.map { mapper.mapFromCached(it) }
//                }
    }

    override fun getBookmarkedPlayers(): Single<List<String>> {
        return Single.just(listOf())
//        return projectsDatabase.cachedProjectsDao().getBookmarkedProjects()
//                .map {
//                    it.map { mapper.mapFromCached(it) }
//                }
    }

    override fun setPlayerAsBookmarked(username: String): Completable {
        return Completable.complete()
//        return Completable.defer {
//            projectsDatabase.cachedProjectsDao().setBookmarkStatus(true, projectId)
//            Completable.complete()
//        }
    }

    override fun setPlayerAsNotBookmarked(username: String): Completable {
        return Completable.complete()
//        return Completable.defer {
//            projectsDatabase.cachedProjectsDao().setBookmarkStatus(false, projectId)
//            Completable.complete()
//        }
    }

    override fun arePlayersCached(): Single<Boolean> {
        return Single.just(true)
//        return projectsDatabase.cachedProjectsDao().getProjects().isEmpty
//                .map {
//                    !it
//                }
    }

    override fun setLastCacheTime(lastCache: Long): Completable {
        return Completable.defer {
            projectsDatabase.configDao().insertConfig(Config(lastCacheTime = lastCache))
            Completable.complete()
        }
    }

    override fun isPlayersCacheExpired(): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (60 * 10 * 1000).toLong()
        return projectsDatabase.configDao().getConfig()
                .onErrorReturn { Config(lastCacheTime = 0) }
                .map {
                    currentTime - it.lastCacheTime > expirationTime
                }
    }

}