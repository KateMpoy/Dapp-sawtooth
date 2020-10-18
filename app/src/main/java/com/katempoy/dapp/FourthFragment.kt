package com.katempoy.dapp

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.katempoy.dapp.User2.Companion.gg
import com.katempoy.dapp.state.XoStateRepository
import com.katempoy.dapp.state.api.XORequestHandler
import com.katempoy.dapp.User2.Companion.global

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
        val restApiURL: String = getRestApiUrl(activity, "rest_api_settings","http://192.168.2.7:8008")
        val stateRepository: XoStateRepository = XoStateRepository(restApiURL)

        requestHandler = XORequestHandler(getRestApiUrl(activity, "rest_api_settings","http://192.168.2.7:8008"),
            getPrivateKey(activity))

        view.findViewById<Button>(R.id.add_item).setOnClickListener {
            findNavController().navigate(R.id.action_FourthFragment_to_SixthFragment) }

        var listView = view.findViewById<ListView>(R.id.item_list)
        val recipeList = gg
        val listItems = arrayOfNulls<String>(recipeList.size)

        //val listItems = arrayOfNulls<String>(recipeList.size)
        //Log.d("XO.list/Item", gg[0].length.toString())

        for (i in 0 until recipeList.size) {
            val recipe = recipeList[i]
           val name = recipe.split(',')
            Log.d("XO.list/Item",name[2])
            listItems[i] = name[2]
        }

        val adapter =
            activity?.let { ArrayAdapter(it, android.R.layout.simple_list_item_1, listItems) }
        listView.adapter = adapter
    }


    // 2
//    val listItems = arrayOfNulls<String>(recipeList.size)
//// 3
//    for (i in 0 until recipeList.size) {
//        val recipe = recipeList[i]
//        listItems[i] = recipe.title
//    }
//    // 4
//    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
//    listView.adapter = adapter

    }



