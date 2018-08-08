package com.chess.personal.my.ui.player


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chess.personal.my.remote.converter.DateAdapter
import com.chess.personal.my.ui.ChessDotComApp

import com.chess.personal.my.ui.R
import com.chess.personal.my.ui.fragment.BaseFragment
import com.chess.personal.my.ui.model.Player
import com.chess.personal.my.ui.util.CircleTransformation
import kotlinx.android.synthetic.main.fragment_player_profile.*


class PlayerProfileFragment : BaseFragment() {

    var player: Player? = null

    companion object {

        fun newInstance(): PlayerProfileFragment {
            return PlayerProfileFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        player = (activity as PlayerActivity).player
        bindPlayer(player)
    }


    private fun bindPlayer(player: Player?) {
        if (player == null) {
            return
        }

        name.text = player.name
        location.text = "${player.location} ${player.country.split('/').last()}"
        followers.text = "Followers:\n${player.followersCount}"
        status.text = "Status:\n${player.status}"
        joined.text = DateAdapter.format.format(player.dateJoined)
        lastLogin.text = DateAdapter.format.format(player.dateLastOnline)

        if(!player.avatar.isNullOrEmpty()) {
            ChessDotComApp.get().picasso
                    .load(player.avatar)
                    .placeholder(R.drawable.ic_account_circle_48dp)
                    .transform(CircleTransformation())
                    .into(avatar)
        }

    }

}
