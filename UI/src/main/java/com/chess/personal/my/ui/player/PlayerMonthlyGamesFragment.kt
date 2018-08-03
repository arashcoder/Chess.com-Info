package com.chess.personal.my.ui.player


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chess.personal.my.presentation.PlayerAllGamesViewModel
import com.chess.personal.my.presentation.PlayerMonthlyGamesViewModel
import com.chess.personal.my.presentation.model.GameView
import com.chess.personal.my.presentation.state.Resource
import com.chess.personal.my.presentation.state.ResourceState

import com.chess.personal.my.ui.R
import com.chess.personal.my.ui.fragment.BaseFragment
import com.chess.personal.my.ui.injection.ViewModelFactory
import com.chess.personal.my.ui.mapper.GameViewMapper
import com.chess.personal.my.ui.model.Game
import com.chess.personal.my.ui.util.Navigator
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_player_monthly_games.*
import javax.inject.Inject


class PlayerMonthlyGamesFragment : BaseFragment() {

    @Inject
    lateinit
    var browseAdapter: PlayerMonthlyGamesAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var browseViewModel: PlayerMonthlyGamesViewModel

    @Inject lateinit var mapper: GameViewMapper

    companion object {

        private val EXTRA_USERNAME = "extra_username"
        private val EXTRA_MONTH = "extra_month"
        private val EXTRA_YEAR = "extra_year"

        fun newInstance(username: String, month: String, year: String, gameListener: GamesListFragmentListener): PlayerMonthlyGamesFragment {
            val args = Bundle()
            args.putString(EXTRA_USERNAME, username)
            args.putString(EXTRA_MONTH, month)
            args.putString(EXTRA_YEAR, year)

            val fragment = PlayerMonthlyGamesFragment()
            fragment.arguments = args
            fragment.navListener = gameListener
            return fragment
        }
    }

    var username: String = ""
    var month: String = ""
    var year: String = ""
    var navListener: GamesListFragmentListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(EXTRA_USERNAME)
            month = it.getString(EXTRA_MONTH)
            year = it.getString(EXTRA_YEAR)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_monthly_games, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AndroidSupportInjection.inject(this)
        browseViewModel = ViewModelProviders.of(baseActivity, viewModelFactory)
                .get(PlayerMonthlyGamesViewModel::class.java)

        setupBrowseRecycler()
    }

    private fun setupBrowseRecycler() {
        browseAdapter.listener = listener
        browseAdapter.context = baseActivity
        games_list.layoutManager = LinearLayoutManager(baseActivity)
        games_list.adapter = browseAdapter
    }

    override fun onStart() {
        super.onStart()
        browseViewModel.getGames().observe(this,
                Observer<Resource<List<GameView>>> {
                    it?.let {
                        handleDataState(it)
                    }
                })
        browseViewModel.fetchMonthlyGames(username, year, month)
    }

    private fun handleDataState(resource: Resource<List<GameView>>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                setupScreenForSuccess(
                        resource.data?.map {
                        mapper.mapToView(it)
                        }
                )
            }
            ResourceState.LOADING -> {
                //progress.visibility = View.VISIBLE
                //recycler_search.visibility = View.GONE
            }
        }
    }

    private fun setupScreenForSuccess(projects: List<Game>?) {
        //progress.visibility = View.GONE
        projects?.let {
            browseAdapter.values = ArrayList(it)
            browseAdapter.notifyDataSetChanged()
            //recycler_search.visibility = View.VISIBLE
        } ?: run {

        }
    }

    private val listener = object : PlayerMonthlyGamesListener {
        override fun onClicked(game: Game) {
            Navigator.navigateToUrl(baseActivity, game.url)
        }


    }

    fun backPressed() {
        navListener?.onGoToNextFragment("","")
    }


}
