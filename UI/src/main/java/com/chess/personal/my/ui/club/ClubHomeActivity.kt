package com.chess.personal.my.ui.club

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
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

    private val onMenuItemClickListener = Toolbar.OnMenuItemClickListener { item ->
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

        setupViewModel()
        setupToolbar()
        setupBrowseRecycler()
    }

    private fun setupViewModel() {
        AndroidInjection.inject(this)

        browseViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ClubHomeViewModel::class.java)
    }

    private fun setupToolbar() {
        toolbar.setTitle(R.string.clubs)
        toolbar.inflateMenu(R.menu.search)
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener)
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
        list.layoutManager = LinearLayoutManager(this)
        list.addItemDecoration(DividerItemDecoration(this))
        list.adapter = browseAdapter
    }

    private fun getBookmarkedClubs(): List<String> {
        return browseViewModel.fetchBookmarkedClubsSingle().blockingGet()
    }

    private fun handleDataState(resource: Resource<List<String>>) {
        when (resource.status) {
            ResourceState.SUCCESS -> setupScreenForSuccess(resource.data)
        }
    }

    private fun setupScreenForSuccess(clubs: List<String>?) {
        clubs?.let {
            setData(it)
        }
    }

    private fun setData(data: List<String>){
        browseAdapter.favorites = data
        browseAdapter.values = ArrayList(data)
        browseAdapter.notifyDataSetChanged()
        if(data.isEmpty()){
            empty_view.visibility = View.VISIBLE
        }
        else{
            empty_view.visibility = View.GONE
        }
    }

    private val searchListener = object : SearchResultListener {
        override fun onClicked(searchResult: String) {
            Navigator.navigateToClubProfile(this@ClubHomeActivity, searchResult)
        }

        override fun onBookmarked(clubName: String) {}

        override fun onUnbookmarked(clubName: String) {
            browseViewModel.unbookmarkClub(clubName)
            val bookmarked = getBookmarkedClubs()
            setData(bookmarked)
        }

    }

}
