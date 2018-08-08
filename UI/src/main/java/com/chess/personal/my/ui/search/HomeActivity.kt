package com.chess.personal.my.ui.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.chess.personal.my.presentation.HomeViewModel
import com.chess.personal.my.presentation.model.PuzzleView
import com.chess.personal.my.presentation.state.Resource
import com.chess.personal.my.presentation.state.ResourceState
import com.chess.personal.my.remote.converter.DateAdapter
import com.chess.personal.my.ui.ChessDotComApp
import com.chess.personal.my.ui.R
import com.chess.personal.my.ui.injection.ViewModelFactory
import com.chess.personal.my.ui.mapper.PuzzleViewMapper
import com.chess.personal.my.ui.model.Puzzle
import com.chess.personal.my.ui.util.IntentUtil
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.item_puzzle.view.*
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var mapper: PuzzleViewMapper
    private lateinit var homeViewModel: HomeViewModel

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        toolbar.setTitle(R.string.home)

        AndroidInjection.inject(this)
        homeViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(HomeViewModel::class.java)

        setupPuzzles()
    }

    private fun setupPuzzles() {
        homeViewModel.getPuzzle().observe(this,
                Observer<Resource<PuzzleView>> {
                    it?.let {
                        handleDataState(it)
                    }
                })
        homeViewModel.getRandomPuzzle().observe(this,
                Observer<Resource<PuzzleView>>{
                    it?.let {
                        handleDataState(it)
                    }
                })
        homeViewModel.fetchDailyPuzzle()
        homeViewModel.fetchRandomPuzzle()
    }

    private fun handleDataState(resource: Resource<PuzzleView>) {
        when (resource.status) {
            ResourceState.LOADING -> { }
            ResourceState.SUCCESS -> setupScreenForSuccess(mapper.mapToView(resource.data!!))
            ResourceState.ERROR -> setupScreenForError()
        }
    }

    private fun setupScreenForSuccess(puzzle: Puzzle?) {
        puzzle?.let {
            if(puzzle.isDaily) {
                progress_daily.visibility = View.GONE
                bindPuzzle(daily_puzzle, puzzle, puzzle.isDaily)
            }
            else {
                progress_random.visibility = View.GONE
                bindPuzzle(random_puzzle, puzzle, puzzle.isDaily)
            }
        }
    }
    private fun setupScreenForError(){
        progress_daily.visibility = View.GONE
        progress_random.visibility = View.GONE
        Snackbar.make(root, getString(R.string.connection_failed), Snackbar.LENGTH_LONG)
                .show()
    }

    private fun bindPuzzle(rootView: View, puzzle: Puzzle, isDaily: Boolean ){
        val header = if (isDaily) "Daily Puzzle: " else "Random Puzzle: "
        rootView.primary_text.text = "$header${puzzle.title}"
        rootView.sub_text.text = DateAdapter.format.format(puzzle.datePublished)
        rootView.setOnClickListener { IntentUtil.openPage(this, puzzle.url) }
        ChessDotComApp.get().picasso
                .load(puzzle.imageUrl)
                .into(rootView.img_puzzle)
    }
}
