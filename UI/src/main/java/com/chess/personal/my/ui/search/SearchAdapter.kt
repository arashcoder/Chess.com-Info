package com.chess.personal.my.ui.search

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chess.personal.my.ui.R
import com.like.LikeButton
import com.like.OnLikeListener
import kotlinx.android.synthetic.main.item_search.view.*
import java.util.ArrayList
import javax.inject.Inject

class SearchAdapter @Inject constructor()
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    lateinit var context: Context
    var favorites: List<String> = listOf()
    var listener: SearchResultListener? = null
    var values: ArrayList<String> = ArrayList()
    private val colors: IntArray by lazy {context.resources.getIntArray(R.array.cool_colors)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                val holder = SearchResultViewHolder.inflate(parent)
                holder.itemView.btn_fav.setOnLikeListener(object: OnLikeListener {
                    override fun liked(likeButton: LikeButton?) {
                        val position = holder.itemView.getTag(R.id.list_position) as Int
                        listener?.onBookmarked(getSearchResult(position))
                    }

                    override fun unLiked(likeButton: LikeButton?) {
                        val position = holder.itemView.getTag(R.id.list_position) as Int
                        listener?.onUnbookmarked(getSearchResult(position))
                    }
                })
                holder.itemView.setOnClickListener { v ->
                    val position = v.getTag(R.id.list_position) as Int
                    listener?.onClicked(getSearchResult(position))
                }
                return holder

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SearchResultViewHolder) {
            holder.bind(values[position], colors[position % colors.size], favorites)
            holder.itemView.setTag(R.id.list_position, position)
        }
    }


    override fun getItemCount(): Int {
        return values.size
    }

    fun setData(searchResults: Collection<String>?) {
        values.clear()
        addData(searchResults)
    }

    private fun addData(searchResults: Collection<String>?) {
        if (searchResults != null) {
            values.addAll(searchResults)
        }
        notifyDataSetChanged()
    }

    private fun getSearchResult(position: Int): String {
        return values[position]
    }

}