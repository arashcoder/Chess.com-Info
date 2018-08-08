package com.chess.personal.my.ui.player


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chess.personal.my.presentation.PlayerAllGamesViewModel
import com.chess.personal.my.presentation.state.Resource
import com.chess.personal.my.presentation.state.ResourceState

import com.chess.personal.my.ui.R
import com.chess.personal.my.ui.fragment.BaseFragment
import com.chess.personal.my.ui.injection.ViewModelFactory
import com.chess.personal.my.ui.util.Navigator
import com.chess.personal.my.ui.view.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_player_all_games.*
import javax.inject.Inject
import dagger.android.support.AndroidSupportInjection




class PlayerAllGamesFragment : BaseFragment() {

    @Inject
    lateinit
    var browseAdapter: PlayerAllGamesAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var browseViewModel: PlayerAllGamesViewModel

    companion object {

        private val EXTRA_USERNAME = "extra_username"

        fun newInstance(username: String, navListener: GamesListFragmentListener): PlayerAllGamesFragment {
            val args = Bundle()
            args.putString(EXTRA_USERNAME, username)

            val fragment = PlayerAllGamesFragment()
            fragment.arguments = args
            fragment.navListener = navListener
            return fragment
        }
    }

    var username: String = ""
    var navListener: GamesListFragmentListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        username = arguments?.getString(EXTRA_USERNAME)!!

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_all_games, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupBrowseRecycler()
    }

    private fun setupViewModel(){
        AndroidSupportInjection.inject(this)
        browseViewModel = ViewModelProviders.of(baseActivity, viewModelFactory)
                .get(PlayerAllGamesViewModel::class.java)
    }

    private fun setupBrowseRecycler() {
        browseAdapter.listener = allGamesListener
        browseAdapter.context = baseActivity
        game_date_list.layoutManager = LinearLayoutManager(baseActivity)
        game_date_list.addItemDecoration(DividerItemDecoration(baseActivity))
        game_date_list.adapter = browseAdapter
    }

    override fun onStart() {
        super.onStart()
        browseViewModel.getGames().observe(this,
                Observer<Resource<List<String>>> {
                    it?.let {
                        handleDataState(it)
                    }
                })
        browseViewModel.fetchAllGames(username)
    }

    private fun handleDataState(resource: Resource<List<String>>) {
        when (resource.status) {
            ResourceState.LOADING -> { progress.visibility = View.VISIBLE }
            ResourceState.SUCCESS -> setupScreenForSuccess(resource.data)
            ResourceState.ERROR -> setupScreenForError()
        }
    }

    private fun setupScreenForSuccess(games: List<String>?) {
        progress.visibility = View.GONE
        games?.let {
            browseAdapter.values = ArrayList(it)
            browseAdapter.notifyDataSetChanged()
            if(it.isEmpty()){
                empty_view.visibility = View.VISIBLE
            }
            else{
                empty_view.visibility = View.GONE
            }
        } ?: run {

        }
    }

    private fun setupScreenForError(){
        progress.visibility = View.GONE
        Snackbar.make(all_player_fragment, getString(R.string.connection_failed), Snackbar.LENGTH_LONG)
                .show()
    }

    private val allGamesListener = object : PlayerAllGamesListener {
        override fun onDownloadPgn(monthlyGameUrl: String) {
            val parts = monthlyGameUrl.split('/')
            val size = parts.size
            val username = parts[size-4]
            val year = parts[size-2]
            val month = parts[size-1]
            Navigator.navigateToUrl(baseActivity, "https://api.chess.com/pub/player/$username/games/$year/$month/pgn" )
        }

        override fun onClicked(monthlyGameUrl: String) {
            val urlParts = monthlyGameUrl.split('/')
            val month = urlParts[urlParts.size-1]
            val year = urlParts[urlParts.size-2]
            navListener?.onGoToNextFragment(year, month)
        }


    }


}
