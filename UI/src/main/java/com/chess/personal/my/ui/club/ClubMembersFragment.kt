package com.chess.personal.my.ui.club

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chess.personal.my.presentation.ClubMembersViewModel
import com.chess.personal.my.presentation.model.ClubMemberView
import com.chess.personal.my.presentation.state.Resource
import com.chess.personal.my.presentation.state.ResourceState

import com.chess.personal.my.ui.R
import com.chess.personal.my.ui.fragment.BaseFragment
import com.chess.personal.my.ui.injection.ViewModelFactory
import com.chess.personal.my.ui.mapper.ClubMemberViewMapper
import com.chess.personal.my.ui.model.ClubMember
import com.chess.personal.my.ui.util.Navigator
import com.chess.personal.my.ui.view.DividerItemDecoration
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_club_members.*
import javax.inject.Inject


class ClubMembersFragment : BaseFragment() {
    @Inject
    lateinit
    var browseAdapter: ClubMembersAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var browseViewModel: ClubMembersViewModel

    @Inject lateinit var mapper: ClubMemberViewMapper

    companion object {

        private val EXTRA_CLUB_NAME = "extra_club_name"

        fun newInstance(clubName: String): ClubMembersFragment {
            val args = Bundle()
            args.putString(EXTRA_CLUB_NAME, clubName)

            val fragment = ClubMembersFragment()
            fragment.arguments = args
            return fragment
        }
    }

    var clubName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clubName = arguments?.getString(EXTRA_CLUB_NAME)!!
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_club_members, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AndroidSupportInjection.inject(this)
        browseViewModel = ViewModelProviders.of(baseActivity, viewModelFactory)
                .get(ClubMembersViewModel::class.java)

        setupBrowseRecycler()

    }

    private fun setupBrowseRecycler() {
        browseAdapter.listener = allGamesListener
        browseAdapter.context = baseActivity
        members_list.layoutManager = LinearLayoutManager(baseActivity)
        members_list.addItemDecoration(DividerItemDecoration(baseActivity))
        members_list.adapter = browseAdapter
    }

    override fun onStart() {
        super.onStart()
        browseViewModel.getClubMembers().observe(this,
                Observer<Resource<List<ClubMemberView>>> {
                    it?.let {
                        handleDataState(it)
                    }
                })
        browseViewModel.fetchClubMembers(clubName)
    }

    private fun handleDataState(resource: Resource<List<ClubMemberView>>) {
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

    private fun setupScreenForSuccess(projects: List<ClubMember>?) {
        //progress.visibility = View.GONE
        projects?.let {
            browseAdapter.values = ArrayList(it)
            browseAdapter.notifyDataSetChanged()
            //recycler_search.visibility = View.VISIBLE
        } ?: run {

        }
    }

    private val allGamesListener = object : ClubMembersListener {
        override fun onClicked(member: ClubMember) {
            Navigator.navigateToPlayerProfile(baseActivity, member.username)
        }


    }


}