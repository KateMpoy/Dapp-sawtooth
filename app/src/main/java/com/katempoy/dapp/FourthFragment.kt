package com.katempoy.dapp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.common.io.BaseEncoding
import com.katempoy.dapp.adapters.GameListRecyclerViewAdapter
import com.katempoy.dapp.models.User
import com.katempoy.dapp.viewmodels.GameViewModel
import com.katempoy.dapp.viewmodels.ViewModelFactory

class FourthFragment :  Fragment() {

    private var listener: OnListFragmentInteractionListener? = null
    private var model: GameViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activity: Activity? = activity

        model = activity.run {
            ViewModelProviders.of(this@FourthFragment, ViewModelFactory(getRestApiUrl(activity,
                "rest_api_settings_key",
               "http://192.168.2.7:8008"))).get(GameViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fourth, container, false)



        Log.d("model",model.toString())
        return view;
    };



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */


    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: User?)
    }
}