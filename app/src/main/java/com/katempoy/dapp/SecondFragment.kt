package com.katempoy.dapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.common.io.BaseEncoding
import com.google.gson.Gson
import com.katempoy.dapp.models.User
import com.katempoy.dapp.state.XoStateRepository
import com.katempoy.dapp.state.api.Entry
import com.katempoy.dapp.state.api.SawtoothRestApi
import com.katempoy.dapp.state.api.StateResponse
import com.katempoy.dapp.state.api.XORequestHandler
import com.katempoy.dapp.state.makeGameAddress
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {




    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_log).setOnClickListener {
            Log.d("clicked", R.id.button_log.toString()  )
            val activity: Activity? = activity
            val restApiURL: String = getRestApiUrl(activity, "rest_api_settings","http://192.168.2.7:8008")
            val stateRepository: XoStateRepository = XoStateRepository(restApiURL)

            val editText = view.findViewById<EditText>(R.id.login_name)
            val editText2 = view.findViewById<EditText>(R.id.login_pass)
            val editText3 = view.findViewById<EditText>(R.id.login_role)
            val x = editText.text.toString()+ "#" + editText2.text.toString()+ "#" +editText3.text.toString()

            stateRepository.checkLogin(x, restApiURL, view)

        }
    }


}
