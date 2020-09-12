package com.katempoy.dapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.katempoy.dapp.state.api.XORequestHandler
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ThirdFragment :  Fragment() {

    private var requestHandler: XORequestHandler? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity: Activity? = activity
        requestHandler = XORequestHandler(getRestApiUrl(activity, "rest_api_settings","http://192.168.2.7:8008"),
            getPrivateKey(activity))

                view.findViewById<Button>(R.id.button_reg).setOnClickListener {
                    val editText = view.findViewById<EditText>(R.id.reg_name)
                    val editText2 = view.findViewById<EditText>(R.id.reg_Password)
                    val editText3 = view.findViewById<EditText>(R.id.reg_role)
                    val message = editText.text.toString()+ "#" + editText2.text.toString()+ "#" +editText3.text.toString()
                    Log.d("XO.message", message)
                    if (message.isBlank()) {
                        Snackbar.make(view.findViewById(R.id.third_fra), "Please enter register details.", Snackbar.LENGTH_LONG).show()
                    } else {
                        requestHandler?.createGame(message, view.findViewById(R.id.third_fra),
                            getRestApiUrl(activity,"rest_api_settings","http://192.168.2.7:8008")
                        ) { it ->
                            if (it) {
                                findNavController().navigate(R.id.action_ThirdFragment_to_FourthFragment)
                            }
                        }
                    }
        }

    }
}