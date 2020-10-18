package com.katempoy.dapp.models

import android.app.Application


class User (var name: String, var pass: String, var role: String) {
    override fun toString(): String {

        val split = name.split('#')
        val n1 = split[0]
        val n2 = split[1]
        val n3 = split[2]

        return "User(name=$n1, pass=$n2, role=$n3)"
    }
    
}
