package com.chess.personal.my.ui.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chess.personal.my.presentation.SearchViewModel
import com.chess.personal.my.presentation.state.Resource
import com.chess.personal.my.presentation.state.ResourceState
import com.chess.personal.my.ui.R
import com.chess.personal.my.ui.injection.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

class SearchActivity : BaseActivity() {

    @Inject lateinit var browseAdapter: SearchAdapter
    //@Inject lateinit var mapper: ProjectViewMapper
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var browseViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        AndroidInjection.inject(this)

        browseViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(SearchViewModel::class.java)

        setupBrowseRecycler()
    }

    override fun onStart() {
        super.onStart()
        browseViewModel.getLiveData().observe(this,
                Observer<Resource<List<String>>> {
                    it?.let {
                        handleDataState(it)
                    }
                })
        browseViewModel.fetchPlayers("IR")
    }

    private fun setupBrowseRecycler() {
        browseAdapter.listener = searchListener
        browseAdapter.context = this
        recycler_search.layoutManager = LinearLayoutManager(this)
        recycler_search.adapter = browseAdapter
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
                progress.visibility = View.VISIBLE
                recycler_search.visibility = View.GONE
            }
        }
    }

    private fun setupScreenForSuccess(projects: List<String>?) {
        progress.visibility = View.GONE
        projects?.let {
            browseAdapter.values = ArrayList(it)
            browseAdapter.notifyDataSetChanged()
            recycler_search.visibility = View.VISIBLE
        } ?: run {

        }
    }

    private val searchListener = object : SearchResultListener {
        override fun onClicked(searchResult: String) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onBookmarked(username: String) {
            browseViewModel.bookmarkPlayer(username)
        }

        override fun onUnbookmarked(username: String) {
            browseViewModel.unbookmarkPlayer(username)
        }

    }
}
