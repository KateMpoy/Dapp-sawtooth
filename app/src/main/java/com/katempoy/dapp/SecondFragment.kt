package com.katempoy.dapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.katempoy.dapp.state.api.XORequestHandler

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var requestHandler: XORequestHandler? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity: Activity? = activity
        requestHandler = XORequestHandler(getRestApiUrl(activity, "rest_api_settings","http://192.168.2.7:8008/blocks"),
            getPrivateKey(activity))

        view.findViewById<Button>(R.id.button_log).setOnClickListener {
//            val editText = view.findViewById<EditText>(R.id.login_name)
//            val editText2 = view.findViewById<EditText>(R.id.login_pass)
//            val editText3 = view.findViewById<EditText>(R.id.login_role)
//            val message = editText.text.toString() + editText2.text.toString() + editText3.text.toString()
//            if (message.isBlank()) {
//                Snackbar.make(view.findViewById(R.id.second_fra), "Please enter register details.", Snackbar.LENGTH_LONG).show()
//            } else {
//                val activity: Activity? = activity
//                val intSpace = (gameBoard.indexOfFirst { it.id == itemId }) + 1
//                requestHandler?.takeSpace(
//                    message,
//                    intSpace.toString(),
//                    view.findViewById(R.id.second_fra),
//                    getRestApiUrl(activity,
//                        "rest_api_settings","http://192.168.2.7:8008")
//                ) { it ->
//                    if (it) {
//                        Snackbar.make(view.findViewById(R.id.second_fra), "Login Successfull", Snackbar.LENGTH_LONG).show()
//                    }
//                }
//            }
        }

    }
}
