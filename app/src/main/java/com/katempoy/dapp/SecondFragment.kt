package com.katempoy.dapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.katempoy.dapp.state.XoStateRepository

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

            val activity: Activity? = activity
            val restApiURL: String = getRestApiUrl(activity, "rest_api_settings","http://192.168.2.7:8008")
            val stateRepository: XoStateRepository = XoStateRepository(restApiURL)

            val editText = view.findViewById<EditText>(R.id.login_name)
            val editText2 = view.findViewById<EditText>(R.id.login_pass)
            val x = editText.text.toString()+ "#" + editText2.text.toString()

            stateRepository.checkLogin(x, restApiURL, view)

        }
    }


}
