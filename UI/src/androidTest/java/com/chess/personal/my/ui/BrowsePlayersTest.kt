package com.chess.personal.my.ui

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.contrib.RecyclerViewActions.*
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.chess.personal.my.ui.search.SearchActivity
import com.chess.personal.my.ui.search.SearchResultViewHolder
import com.chess.personal.my.ui.test.TestApplication
import com.chess.personal.my.ui.test.factory.TestDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BrowsePlayersTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<SearchActivity>(SearchActivity::class.java, false,
            false)

    @Test
    fun activityLaunches() {
        stubPlayersRepositoryGetPlayers(Single.just(listOf(
                TestDataFactory.randomUuid())))
        val intent = Intent(InstrumentationRegistry.getTargetContext(), SearchActivity::class.java)
        intent.putExtra(SearchActivity.EXTRA_IS_PLAYER_SEARCH, true)
        activity.launchActivity(intent)
    }

    //@Test
    fun playersDisplay() {
        val players = listOf(TestDataFactory.randomUuid(), TestDataFactory.randomUuid(),
                TestDataFactory.randomUuid(), TestDataFactory.randomUuid())
        stubPlayersRepositoryGetPlayers(Single.just(players))

        val intent = Intent(InstrumentationRegistry.getTargetContext(), SearchActivity::class.java)
        intent.putExtra(SearchActivity.EXTRA_IS_PLAYER_SEARCH, true)
        activity.launchActivity(intent)

        players.forEachIndexed { index, player ->
            Espresso.onView(ViewMatchers.withId(R.id.recycler_search))
                    .perform(RecyclerViewActions.scrollToPosition<SearchResultViewHolder>(
                            index))

            Espresso.onView(ViewMatchers.withId(R.id.recycler_search))
                    .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText(player))))
        }
    }

    private fun stubPlayersRepositoryGetPlayers(observable: Single<List<String>>) {
        whenever(TestApplication.appComponent().playersRepository().getPlayers(any()))
                .thenReturn(observable)
    }

}