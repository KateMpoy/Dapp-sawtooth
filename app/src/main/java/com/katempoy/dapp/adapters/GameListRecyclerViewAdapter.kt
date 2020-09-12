package com.katempoy.dapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.katempoy.dapp.FourthFragment.OnListFragmentInteractionListener
import com.katempoy.dapp.R
import com.katempoy.dapp.models.User
import com.katempoy.dapp.FourthFragment
import kotlinx.android.synthetic.main.fragment_fourth.view.item_game_number
import kotlinx.android.synthetic.main.fragment_fourth.view.item_game_status

/**
 * [RecyclerView.Adapter] that can display a [Game] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class GameListRecyclerViewAdapter(
    private var mValues: List<User>,
    private val mListener: FourthFragment.OnListFragmentInteractionListener?
) : RecyclerView.Adapter<GameListRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as User
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_fourth, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.name
       // holder.mContentView.text = item.gameState

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    fun updateData(games: List<User>) {
        mValues = games
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_game_number
        val mContentView: TextView = mView.item_game_status

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
