package com.katempoy.dapp

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.katempoy.dapp.state.XoStateRepository
import com.katempoy.dapp.User2.Companion.global
import com.katempoy.dapp.state.api.XORequestHandler


class SixthFragment :  Fragment() {
    private var requestHandler: XORequestHandler? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sixth, container, false)
        return view;
    };

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity: Activity? = activity
        requestHandler = XORequestHandler(getRestApiUrl(activity, "rest_api_settings","http://192.168.2.7:8008"),
            getPrivateKey(activity))
        view.findViewById<Button>(R.id.register_item_button).setOnClickListener {

            Log.d("User details", global.toString())
            val activity: Activity? = activity
            val restApiURL: String =
                getRestApiUrl(activity, "rest_api_settings", "http://192.168.2.7:8008")
            val stateRepository: XoStateRepository = XoStateRepository(restApiURL)

            val editText = view.findViewById<EditText>(R.id.item_name)
            val editText2 = view.findViewById<EditText>(R.id.item_type)
            val editText3 = view.findViewById<EditText>(R.id.item_cost)
            val editText4 = view.findViewById<EditText>(R.id.item_weight)
            val x = global!![0]+ "#" +  global!![1] + "#" + editText.text.toString() + "#" + editText2.text.toString()  + "#" + editText3.text.toString() + "#" + editText4.text.toString()

            if (x.isBlank()) {
                Snackbar.make(view.findViewById(R.id.sixth_fra), "Please enter item details.", Snackbar.LENGTH_LONG).show()
            } else {
                requestHandler?.createItem(x, view.findViewById(R.id.sixth_fra),
                    getRestApiUrl(activity,"rest_api_settings","http://192.168.2.7:8008")
                ) { it ->
                    if (it) {
                        findNavController().navigate(R.id.action_SixthFragment_to_FourthFragment)
                    }
                }
            }

        }
    }
}