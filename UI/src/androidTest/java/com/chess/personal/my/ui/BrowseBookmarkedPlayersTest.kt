package com.chess.personal.my.ui

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.chess.personal.my.domain.model.Player
import com.chess.personal.my.ui.player.PlayerHomeActivity
import com.chess.personal.my.ui.search.SearchAdapter
import com.chess.personal.my.ui.search.SearchResultViewHolder
import com.chess.personal.my.ui.test.TestApplication
import com.chess.personal.my.ui.test.factory.PlayerDataFactory
import com.chess.personal.my.ui.test.factory.TestDataFactory
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BrowseBookmarkedPlayersTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<PlayerHomeActivity>(PlayerHomeActivity::class.java, false,
            false)

    @Test
    fun activityLaunches() {
        stubPlayersRepositoryGetBookmarkedPlayers(Single.just(listOf(
                TestDataFactory.randomUuid())))
        activity.launchActivity(null)
    }

    @Test
    fun bookmarkedPlayersDisplay() {
        val players = listOf(TestDataFactory.randomUuid(), TestDataFactory.randomUuid(),
                TestDataFactory.randomUuid(), TestDataFactory.randomUuid())
        stubPlayersRepositoryGetBookmarkedPlayers(Single.just(players))

        activity.launchActivity(null)

        players.forEachIndexed { index, player ->
            onView(withId(R.id.list))
                    .perform(RecyclerViewActions.scrollToPosition<SearchResultViewHolder>(
                            index))

            onView(withId(R.id.list))
                    .check(matches(hasDescendant(withText(player))))
        }
    }

    private fun stubPlayersRepositoryGetBookmarkedPlayers(observable: Single<List<String>>) {
        whenever(TestApplication.appComponent().playersRepository().getBookmarkedPlayers())
                .thenReturn(observable)
    }

}