package com.katempoy.dapp.state

import android.app.Activity
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.common.io.BaseEncoding
import com.katempoy.dapp.R
import retrofit2.converter.gson.GsonConverterFactory
import com.katempoy.dapp.models.User
import com.katempoy.dapp.state.api.*


class XoStateRepository(url: String) {
    private var service: SawtoothRestApi? = null
    var games: MutableLiveData<List<User>> = MutableLiveData()
    var gameFocus: MutableLiveData<User> = MutableLiveData()
    private var restApiURL: String = url

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(restApiURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create<SawtoothRestApi>(SawtoothRestApi::class.java)
    }

    fun checkLogin (name:String, url: String, view: View){


        val split = name.split('#')

        val resp = arrayListOf<User>()
        service?.getState(transactionFamilyPrefix())?.enqueue(object : Callback<StateResponse> {
            override fun onResponse(call: Call<StateResponse>, response: Response<StateResponse>) {
                if (response.body() != null) {

                    response.body()?.data?.map { entry ->
                        resp.add(parseGame(entry.data))
                    }

                } else {
                    Snackbar.make(view.findViewById(R.id.second_fra), "Error in Response", Snackbar.LENGTH_LONG).show()
                }

                resp.forEach { user ->
                    val split2 = user.name.split('#')
                    Log.d("XO.check", split.toString())
                    Log.d("XO.exist", split2.toString())
                    if(split2[0] == split[0] && split2[1]== split[1] ){
                        findNavController(view).navigate(R.id.action_SecondFragment_to_FourthFragment)
                    }
                }
            }
            override fun onFailure(call: Call<StateResponse>, t: Throwable) {
                Log.d("XO.dataFail2", t.toString())
                call.cancel()
            }
        })
    }

    fun getState(update: Boolean, url: String) {
        checkURLChanged(url)
        val resp = arrayListOf<User>()
        if (update) {
            service?.getState(transactionFamilyPrefix())?.enqueue(object : Callback<StateResponse> {
                override fun onResponse(call: Call<StateResponse>, response: Response<StateResponse>) {
                    if (response.body() != null) {
                        response.body()?.data?.map { entry ->

                            resp.add(parseGame(entry.data))
                        }
                        games.value = resp.sortedBy { it.name.toLowerCase() }

                        Log.d("XO.State", "Updated game list")
                    } else {
                        Log.d("XO.State", response.toString())
                    }
                }
                override fun onFailure(call: Call<StateResponse>, t: Throwable) {
                    Log.d("XO.State", t.toString())
                    call.cancel()
                }
            })
        }
    }

    fun getGameState(name: String,url: String) {
        checkURLChanged(url)
        val gameAddress = makeGameAddress(name)
        service?.getState(gameAddress)?.enqueue(object : Callback<StateResponse> {
            override fun onResponse(call: Call<StateResponse>, response: Response<StateResponse>) {
                if (response.body() != null) {
                    val entry: Entry? = response.body()?.data?.get(0)
                    val gameData: User = entry?.data?.let { parseGame(it) }!!
                    gameFocus.value = gameData
                    Log.d("XO.State", "Updated game state")
                } else {
                    Log.d("XO.State", response.toString())
                }
            }
            override fun onFailure(call: Call<StateResponse>, t: Throwable) {
                Log.d("XO.State", t.toString())
                call.cancel()
            }
        })
    }

    private fun parseGame(data: String): User {
        val decoded = String(BaseEncoding.base64().decode(data))
        Log.d("XO.decoded", decoded)
        val split = decoded.split(',')
        return User(split[0], split[1], split[2])
    }

    private fun checkURLChanged(url: String) {
        if (restApiURL != url) {
            restApiURL = url
            buildService()
        }
    }

    private fun buildService() {
        val retrofit = Retrofit.Builder()
            .baseUrl(restApiURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create<SawtoothRestApi>(SawtoothRestApi::class.java)
    }

    private fun parseLogin(data: String) : String {
        val decoded = String(BaseEncoding.base64().decode(data))
        val split = decoded.split(',')
        return split[0] + " " + split[1] + " " + split[2]
    }
}
