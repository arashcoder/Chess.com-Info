package com.chess.personal.my.ui.player

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.chess.personal.my.presentation.HomeViewModel
import com.chess.personal.my.presentation.PlayerProfileViewModel
import com.chess.personal.my.presentation.model.PlayerView
import com.chess.personal.my.presentation.state.Resource
import com.chess.personal.my.presentation.state.ResourceState
import com.chess.personal.my.ui.R
import com.chess.personal.my.ui.injection.ViewModelFactory
import com.chess.personal.my.ui.mapper.PlayerViewMapper
import com.chess.personal.my.ui.model.Player
import com.chess.personal.my.ui.search.BaseActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_player.*
import javax.inject.Inject

class PlayerActivity : BaseActivity() {

    companion object {
        private val EXTRA_USERNAME = "extra_username"
        fun newIntent(context: Context, username: String): Intent {
            val intent = Intent(context, PlayerActivity::class.java)
            intent.putExtra(EXTRA_USERNAME, username)
            return intent
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var mapper: PlayerViewMapper
    private lateinit var playerProfileViewModel: PlayerProfileViewModel

    var player: Player = Player()
    var adapter: PlayerPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        setupToolbar()
        setupViewModel(username)
    }

    private fun setupViewModel(username: String){
        AndroidInjection.inject(this)
        playerProfileViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(PlayerProfileViewModel::class.java)
        playerProfileViewModel.getPlayer().observe(this,
                Observer<Resource<PlayerView>> {
                    it?.let {
                        handleDataState(it)
                    }
                })
        playerProfileViewModel.fetchPlayerProfile(username)
    }

    private fun setupToolbar(){
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }


    private fun handleDataState(resource: Resource<PlayerView>) {
        when (resource.status) {
            ResourceState.LOADING -> progress.visibility = View.VISIBLE
            ResourceState.SUCCESS -> setupScreenForSuccess(mapper.mapToView(resource.data!!))
            ResourceState.ERROR -> setupScreenForError()
        }
    }

    private fun setupScreenForSuccess(player: Player?) {
        progress.visibility = View.GONE
        player?.let {
            bindPlayer(player)
        } ?: run {

        }
    }

    private fun setupScreenForError(){
        progress.visibility = View.GONE
        Snackbar.make(root, getString(R.string.connection_failed), Snackbar.LENGTH_LONG)
                .show()
    }


    private fun bindPlayer(player: Player ){
        this.player = player
        toolbar.title = player.username
        toolbar.subtitle = player.name
        setupTabs()
    }

    fun setupTabs() {
        adapter = PlayerPagerAdapter(this, supportFragmentManager)
        pager.adapter = adapter
        tabs.setupWithViewPager(pager)
    }

    override fun onBackPressed() {
        if (pager.currentItem == 1) {
            if (adapter?.getItem(1) is PlayerMonthlyGamesFragment) {
                (adapter?.getItem(1) as PlayerMonthlyGamesFragment).backPressed()
            } else if (adapter?.getItem(1) is PlayerAllGamesFragment) {
                finish()
            }
        }
        else {
            super.onBackPressed()
        }
    }
}
