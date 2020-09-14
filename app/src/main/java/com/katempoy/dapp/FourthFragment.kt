package com.katempoy.dapp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.common.io.BaseEncoding
import com.katempoy.dapp.adapters.GameListRecyclerViewAdapter
import com.katempoy.dapp.models.User
import com.katempoy.dapp.state.api.XORequestHandler
import com.katempoy.dapp.viewmodels.GameViewModel
import com.katempoy.dapp.viewmodels.ViewModelFactory

class FourthFragment :  Fragment() {

    private var requestHandler: XORequestHandler? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fourth, container, false)
        return view;
    };

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity: Activity? = activity
        requestHandler = XORequestHandler(getRestApiUrl(activity, "rest_api_settings","http://192.168.2.7:8008"),
            getPrivateKey(activity))

        view.findViewById<Button>(R.id.add_item).setOnClickListener {
            findNavController().navigate(R.id.action_FourthFragment_to_SixthFragment) }
        }

    }



