package com.chess.personal.my.ui.player


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
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
import dagger.android.AndroidInjection
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

    var username: String? = ""
    var navListener: GamesListFragmentListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        username = arguments?.getString(EXTRA_USERNAME)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_all_games, container, false)
    }

    //override fun onAttach(context: Context) {
        //AndroidSupportInjection.inject(this)
       // super.onAttach(context)
   // }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // AndroidInjection.inject(baseActivity)
        AndroidSupportInjection.inject(this)
        browseViewModel = ViewModelProviders.of(baseActivity, viewModelFactory)
                .get(PlayerAllGamesViewModel::class.java)

        setupBrowseRecycler()

//        adapterGames = GameDateAdapter(baseActivity, object : GameDateAdapter.Listener {
//            override fun onDownloadPgn(monthlyGamesUrl: String) {
//                val parts = monthlyGamesUrl.split('/')
//                val size = parts.size
//                val username = parts[size-4]
//                val year = parts[size-2]
//                val month = parts[size-1]
//                Navigator.navigateToUrl(baseActivity, "${ChessService.API_ROOT_URL}player/$username/games/$year/$month/pgn" )
//            }
//
//            override fun onClicked(monthlyGamesUrl: String) {
//                val urlParts = monthlyGamesUrl.split('/')
//                val month = urlParts[urlParts.size-1]
//                val year = urlParts[urlParts.size-2]
//                listener?.onGoToNextFragment(year, month)
////                val feedFragment = PlayerMonthlyGamesFragment.newInstance(searchTerm!!, month, year)
////                baseActivity.supportFragmentManager.beginTransaction()
////                        .replace(R.id.all_player_fragment, feedFragment, TAG_ALL_GAMES_FRAGMENT).addToBackStack(null)
////                        .commit()
//
//            }
//        })
//        game_date_list.layoutManager = LinearLayoutManager(activity)
//        game_date_list.addItemDecoration(DividerItemDecoration(baseActivity))
//        game_date_list.adapter = adapterGames
//
//        if (activity is PlayerActivity) {
//            loadData()
//        } else {
//            throw IllegalStateException("Incorrect parent activity")
//        }
    }

    private fun setupBrowseRecycler() {
        browseAdapter.listener = allGamesListener
        browseAdapter.context = baseActivity
        game_date_list.layoutManager = LinearLayoutManager(baseActivity)
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
        browseViewModel.fetchAllGames("erik")
    }

    private fun handleDataState(resource: Resource<List<String>>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                setupScreenForSuccess(
                        //resource.data?.map {
                        //mapper.mapToView(it)
                        //}
                        resource.data
                )
            }
            ResourceState.LOADING -> {
                //progress.visibility = View.VISIBLE
                //recycler_search.visibility = View.GONE
            }
        }
    }

    private fun setupScreenForSuccess(projects: List<String>?) {
        //progress.visibility = View.GONE
        projects?.let {
            browseAdapter.values = ArrayList(it)
            browseAdapter.notifyDataSetChanged()
            //recycler_search.visibility = View.VISIBLE
        } ?: run {

        }
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