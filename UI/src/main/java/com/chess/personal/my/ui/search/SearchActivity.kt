package com.chess.personal.my.ui.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import com.chess.personal.my.presentation.SearchViewModel
import com.chess.personal.my.presentation.state.Resource
import com.chess.personal.my.presentation.state.ResourceState
import com.chess.personal.my.ui.R
import com.chess.personal.my.ui.injection.ViewModelFactory
import com.chess.personal.my.ui.util.Navigator
import com.chess.personal.my.ui.view.DividerItemDecoration
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_search.*
import java.util.*
import javax.inject.Inject

class SearchActivity : BaseActivity() {

    @Inject lateinit var browseAdapter: SearchAdapter
    @Inject lateinit var countryAdapter: CountryAdapter
    //@Inject lateinit var mapper: ProjectViewMapper
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var searchViewModel: SearchViewModel

    companion object {

        val EXTRA_IS_PLAYER_SEARCH = "extra_is_player_search"
        fun newIntent(context: Context, isPlayer: Boolean): Intent {
            val intent = Intent(context, SearchActivity::class.java)
            intent.putExtra(SearchActivity.EXTRA_IS_PLAYER_SEARCH, isPlayer)
            return intent
        }
    }
    var isPlayerSearch: Boolean = true
    var countryISOCodes: List<String> = Locale.getISOCountries().toList()
    var searchTerm: String = ""
    var countryCode: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        AndroidInjection.inject(this)

        searchViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(SearchViewModel::class.java)

        isPlayerSearch = intent.getBooleanExtra(SearchActivity.EXTRA_IS_PLAYER_SEARCH, true)

        setupCountryAdapter()

        clear.setOnClickListener{
            clear.animate().alpha(0.0f).withEndAction {
                clear.visibility = View.GONE
                search.text.clear()
                //teleprinter.showKeyboard(search)
            }
        }

        search.setOnEditorActionListener{ textView: TextView, i: Int, keyEvent: KeyEvent? ->
            if (!search.text.isNullOrEmpty()) {
                searchTerm = search.text.toString()

                val selectedCountry = countryISOCodes[spinnerCountry.selectedItemPosition]
                countryCode = selectedCountry//.toUpperCase()
                if(isPlayerSearch) {
                    searchViewModel.fetchPlayers(countryCode)
                }
                else{
                    searchViewModel.fetchClubs(countryCode)
                }
            }
            return@setOnEditorActionListener true
        }

        search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isEmpty()) {
                    //clear.fadeOut()
                } else {
                    clear.visibility = View.VISIBLE
                    clear.animate().alpha(1.0f)
                }
            }

        })

        setupBrowseRecycler()
    }

    private fun setupCountryAdapter() {
        countryAdapter.items = countryISOCodes
        spinnerCountry.adapter = countryAdapter
    }

    override fun onStart() {
        super.onStart()
        searchViewModel.getLiveData().observe(this,
                Observer<Resource<List<String>>> {
                    it?.let {
                        handleDataState(it)
                    }
                })

    }

    private fun setupBrowseRecycler() {
        browseAdapter.listener = searchListener
        browseAdapter.context = this
        browseAdapter.favorites = if(isPlayerSearch) searchViewModel.fetchBookmarkedPlayers().blockingGet()
                            else searchViewModel.fetchBookmarkedClubs().blockingGet()
        recycler_search.layoutManager = LinearLayoutManager(this)
        recycler_search.addItemDecoration(DividerItemDecoration(this))
        recycler_search.adapter = browseAdapter
    }

    private fun handleDataState(resource: Resource<List<String>>) {
        when (resource.status) {
            ResourceState.LOADING -> {
                progress.visibility = View.VISIBLE
                recycler_search.visibility = View.GONE
            }
            ResourceState.SUCCESS -> setupScreenForSuccess(resource.data)
            ResourceState.ERROR -> setupScreenForError()
        }
    }

    private fun setupScreenForSuccess(projects: List<String>?) {
        progress.visibility = View.GONE
        projects?.let {
            var matchedResults: List<String>?
            matchedResults = if(isPlayerSearch) {
                projects.filter { it.contains(searchTerm, ignoreCase = true) }
            } else{
                val clubNames = projects.map { it.split('/').last()}
                clubNames?.filter { it.contains(searchTerm, ignoreCase = true) }
            }

            browseAdapter.values = ArrayList(matchedResults)
            browseAdapter.notifyDataSetChanged()
            recycler_search.visibility = View.VISIBLE
        } ?: run {

        }
    }

    private fun setupScreenForError(){
        progress.visibility = View.GONE
        Snackbar.make(root, getString(R.string.connection_failed), Snackbar.LENGTH_LONG)
                .show()
    }

    private val searchListener = object : SearchResultListener {
        override fun onClicked(searchResult: String) {
            if(isPlayerSearch){
                Navigator.navigateToPlayerProfile(this@SearchActivity, searchResult)
            }
            else{
                Navigator.navigateToClubProfile(this@SearchActivity, searchResult)
            }

        }

        override fun onBookmarked(searchResult: String) {
            if(isPlayerSearch){
                searchViewModel.bookmarkPlayer(searchResult)
            }
            else{
                searchViewModel.bookmarkClub(searchResult)
            }
        }

        override fun onUnbookmarked(searchResult: String) {
            if(isPlayerSearch){
                searchViewModel.unbookmarkPlayer(searchResult)
            }
            else{
                searchViewModel.unbookmarkClub(searchResult)
            }
        }

    }
}
