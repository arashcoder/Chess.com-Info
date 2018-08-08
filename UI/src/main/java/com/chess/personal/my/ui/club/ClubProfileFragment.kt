package com.chess.personal.my.ui.club


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chess.personal.my.remote.converter.DateAdapter
import com.chess.personal.my.ui.ChessDotComApp

import com.chess.personal.my.ui.R
import com.chess.personal.my.ui.fragment.BaseFragment
import com.chess.personal.my.ui.model.Club
import com.chess.personal.my.ui.util.CircleTransformation
import kotlinx.android.synthetic.main.fragment_club_profile.*


class ClubProfileFragment : BaseFragment() {

    var club: Club? = null

    companion object {

        fun newInstance(): ClubProfileFragment {
            return ClubProfileFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_club_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        club = (activity as ClubActivity).club
        bindPlayer(club)
    }


    private fun bindPlayer(club: Club?) {
        if (club == null) {
            return
        }
        name.text = club.name
        country.text = club.countryUrl.split('/').last()
        created.text = DateAdapter.format.format(club.dateCreated)
        lastActivity.text = DateAdapter.format.format(club.lastActivity)
        visibility.text = club.visibility
        if(!club.avatar.isNullOrEmpty()) {
            ChessDotComApp.get().picasso
                    .load(club.avatar)
                    .placeholder(R.drawable.ic_account_circle_48dp)
                    .transform(CircleTransformation())
                    .into(avatar)
        }
    }

}