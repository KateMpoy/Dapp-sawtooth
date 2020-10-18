package com.katempoy.dapp.models

class Item (var name: String, var pass: String, var role: String) {
    override fun toString(): String {

        val split = name.split('#')
        val n1 = split[0]
        val n2 = split[1]
        val n3 = split[2]
        val n4 = split[3]
        val n5 = split[4]
        val n6 = split[5]

        return "Item(username=$n1, userpass=$n2, name=$n3, type=$n4, cost=$n5, weight=$n6)"
    }
}