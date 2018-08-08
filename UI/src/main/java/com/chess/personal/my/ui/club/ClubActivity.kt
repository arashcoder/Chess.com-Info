package com.chess.personal.my.ui.club

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.chess.personal.my.presentation.ClubProfileViewModel
import com.chess.personal.my.presentation.model.ClubView
import com.chess.personal.my.presentation.state.Resource
import com.chess.personal.my.presentation.state.ResourceState
import com.chess.personal.my.ui.R
import com.chess.personal.my.ui.injection.ViewModelFactory
import com.chess.personal.my.ui.mapper.ClubViewMapper
import com.chess.personal.my.ui.model.Club
import com.chess.personal.my.ui.search.BaseActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_club.*
import javax.inject.Inject

class ClubActivity : BaseActivity() {

    companion object {
        private val EXTRA_CLUB_NAME = "extra_club_name"
        fun newIntent(context: Context, clubName: String): Intent {
            val intent = Intent(context, ClubActivity::class.java)
            intent.putExtra(EXTRA_CLUB_NAME, clubName)
            return intent
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var mapper: ClubViewMapper
    private lateinit var clubProfileViewModel: ClubProfileViewModel

    var club: Club = Club()
    var clubName: String = ""
    var adapter: ClubPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_club)

        clubName = intent.getStringExtra(EXTRA_CLUB_NAME)
        setupToolbar()
        setupViewModel(clubName)
    }

    private fun setupToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun setupViewModel(clubName: String) {
        AndroidInjection.inject(this)
        clubProfileViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ClubProfileViewModel::class.java)
        clubProfileViewModel.getClub().observe(this,
                Observer<Resource<ClubView>> {
                    it?.let {
                        handleDataState(it)
                    }
                })
        clubProfileViewModel.fetchClubProfile(clubName)
    }


    private fun handleDataState(resource: Resource<ClubView>) {
        when (resource.status) {
            ResourceState.LOADING -> progress.visibility = View.VISIBLE
            ResourceState.SUCCESS -> setupScreenForSuccess(mapper.mapToView(resource.data!!))
            ResourceState.ERROR -> setupScreenForError()
        }
    }

    private fun setupScreenForSuccess(club: Club?) {
        progress.visibility = View.GONE
        club?.let {
            bindClub(club)
        } ?: run {

        }
    }

    private fun setupScreenForError(){
        progress.visibility = View.GONE
        Snackbar.make(root, getString(R.string.connection_failed), Snackbar.LENGTH_LONG)
                .show()
    }

    private fun bindClub(club: Club ){
        this.club = club
        toolbar.title = club.name
        setupTabs()
    }

    private fun setupTabs() {
        adapter = ClubPagerAdapter(this, supportFragmentManager)
        pager.adapter = adapter
        tabs.setupWithViewPager(pager)
    }

}
