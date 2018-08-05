package com.chess.personal.my.ui.club

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import com.chess.personal.my.presentation.ClubHomeViewModel
import com.chess.personal.my.presentation.state.Resource
import com.chess.personal.my.presentation.state.ResourceState
import com.chess.personal.my.ui.R
import com.chess.personal.my.ui.injection.ViewModelFactory
import com.chess.personal.my.ui.search.BaseActivity
import com.chess.personal.my.ui.search.SearchAdapter
import com.chess.personal.my.ui.search.SearchResultListener
import com.chess.personal.my.ui.util.Navigator
import com.chess.personal.my.ui.view.DividerItemDecoration
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_club_home.*
import javax.inject.Inject

class ClubHomeActivity : BaseActivity() {

    @Inject
    lateinit var browseAdapter: SearchAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var browseViewModel: ClubHomeViewModel

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ClubHomeActivity::class.java)
        }
    }

    val onMenuItemClickListener = Toolbar.OnMenuItemClickListener { item ->
        when (item.itemId) {
            R.id.action_search -> {
                Navigator.navigateToSearch(this@ClubHomeActivity, false)
                return@OnMenuItemClickListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_club_home)

        AndroidInjection.inject(this)

        browseViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ClubHomeViewModel::class.java)

        toolbar.setTitle(R.string.clubs)
        toolbar.inflateMenu(R.menu.search)
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener)

        setupBrowseRecycler()
    }

    override fun onStart() {
        super.onStart()
        browseViewModel.getClubs().observe(this,
                Observer<Resource<List<String>>> {
                    it?.let {
                        handleDataState(it)
                    }
                })
        browseViewModel.fetchBookmarkedClubs()
    }

    private fun setupBrowseRecycler() {
        browseAdapter.listener = searchListener
        browseAdapter.context = this
        browseAdapter.favorites = browseViewModel.fetchBookmarkedClubsSingle().blockingGet()
        list.layoutManager = LinearLayoutManager(this)
        list.addItemDecoration(DividerItemDecoration(this))
        list.adapter = browseAdapter
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
                list.visibility = View.GONE
            }
        }
    }

    private fun setupScreenForSuccess(projects: List<String>?) {
        //progress.visibility = View.GONE
        projects?.let {
            browseAdapter.values = ArrayList(it)
            browseAdapter.notifyDataSetChanged()
            list.visibility = View.VISIBLE
        } ?: run {

        }
    }

    private val searchListener = object : SearchResultListener {
        override fun onClicked(searchResult: String) {
            Navigator.navigateToClubProfile(this@ClubHomeActivity, searchResult)
        }

        override fun onBookmarked(clubName: String) {
            browseViewModel.bookmarkClub(clubName)
        }

        override fun onUnbookmarked(clubName: String) {
            browseViewModel.unbookmarkClub(clubName)
        }

    }

}
