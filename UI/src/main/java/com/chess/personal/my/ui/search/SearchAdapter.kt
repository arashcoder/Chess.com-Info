package com.chess.personal.my.ui.search

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chess.personal.my.ui.R
import kotlinx.android.synthetic.main.item_search.view.*
import java.util.ArrayList
import javax.inject.Inject

class SearchAdapter @Inject constructor(
                                        )
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {

        private val FOOTER_COUNT = 1

        private val TYPE_ITEM = 0
        private val TYPE_FOOTER = 1
    }

    lateinit var context: Context
    var isPlayer: Boolean = true
    var listener: SearchResultListener? = null
    var values: ArrayList<String> = ArrayList()
    private var loading: Boolean = false
    private val colors: IntArray by lazy {context.resources.getIntArray(R.array.cool_colors)}// = context.resources.getIntArray(R.array.cool_colors)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_ITEM -> {
                val holder = SearchResultViewHolder.inflate(parent)
//                holder.itemView.btn_fav.setOnLikeListener(object:OnLikeListener{
//                    override fun liked(likeButton: LikeButton?) {
//                        val position = holder.itemView.getTag(R.id.list_position) as Int
//                        listener.onLiked(getSearchResult(position))
//                    }
//
//                    override fun unLiked(likeButton: LikeButton?) {
//                        val position = holder.itemView.getTag(R.id.list_position) as Int
//                        listener.onDisliked(getSearchResult(position))
//                    }
//                })
//                holder.itemView.setOnClickListener { v ->
//                    val position = v.getTag(R.id.list_position) as Int
//                    val holder = v.getTag(R.id.list_view_holder) as SearchResultViewHolder
//                    listener.onClicked(getSearchResult(position))
//                }
                return holder
            }
            //TYPE_FOOTER -> return LoadingFooterViewHolder.inflate(parent)
        }
        throw IllegalStateException("No known viewholder for type " + viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SearchResultViewHolder) {
            holder.bind(values[position], colors[position % colors.size], isPlayer)
            holder.itemView.setTag(R.id.list_position, position)
            holder.itemView.setTag(R.id.list_view_holder, holder)


        } //else if (holder is LoadingFooterViewHolder) {
           // holder.bind(loading)
        //}
    }

    override fun getItemViewType(position: Int): Int {
        if (position == values.size) {
            return TYPE_FOOTER
        }
        return TYPE_ITEM

    }

    override fun getItemCount(): Int {
        return values.size + FOOTER_COUNT
    }

    fun setData(searchResults: Collection<String>?) {
        values.clear()
        addData(searchResults)
    }

    fun addData(searchResults: Collection<String>?) {
        if (searchResults != null) {
            values.addAll(searchResults)
        }
        notifyDataSetChanged()
    }

    fun clearData() {
        values.clear()
        notifyDataSetChanged()
    }

    fun setLoading(loading: Boolean) {
        this.loading = loading
        notifyItemChanged(values.size)
    }

    private fun getSearchResult(position: Int): String {
        return values[position]
    }

}