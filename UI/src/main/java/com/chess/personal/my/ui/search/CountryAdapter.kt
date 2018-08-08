package com.chess.personal.my.ui.search

import android.content.Context
import android.support.annotation.NonNull
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.chess.personal.my.ui.R
import com.jwang123.flagkit.FlagKit
import kotlinx.android.synthetic.main.item_country.view.*
import javax.inject.Inject

class CountryAdapter @Inject constructor
(private val mContext: Context) : ArrayAdapter<String>(mContext, R.layout.item_country) {

    private val mInflater: LayoutInflater = LayoutInflater.from(mContext)
     var items: List<String> = listOf()

    override fun getCount(): Int {
        return items.size
    }

    override fun  getDropDownView(position: Int, convertView: View?,
                                  @NonNull parent: ViewGroup): View {
        return createItemView(position, convertView, parent)
    }

    @NonNull
    override fun getView(position: Int, convertView: View?, @NonNull parent: ViewGroup): View {
        return createItemView(position, convertView, parent)
    }

    private fun createItemView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = mInflater.inflate(R.layout.item_country, parent, false)
        val ISO = items[position]

        view.ISO.text = ISO
        try {
            val flag = FlagKit.drawableWithFlag(context, ISO.toLowerCase())
            view.flag.setImageDrawable(flag)
        }
        catch(ex: Exception){
            ex.printStackTrace()
        }

        return view
    }
}