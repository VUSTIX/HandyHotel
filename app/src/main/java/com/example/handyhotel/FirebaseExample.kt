package com.example.handyhotel

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FirebaseExample {
    private var mDatabase: DatabaseReference

    init {
        mDatabase = FirebaseDatabase.getInstance().reference
    }

    fun addUser(
        idUser: String,
        firstName: String,
        lastName: String,
        phone: String,
        login: String,
        password: String
    ) {
        val user = User(1, firstName, lastName, phone, "", login, password)

        mDatabase.child("users").child(idUser).setValue(user)
    }

    inner class User(
        var id_role: Int = 0,
        var firstname: String? = null,
        var lastname: String? = null,
        var phone: String? = null,
        var email: String? = null,
        var login: String? = null,
        var password: String? = null
    ) {
        constructor() : this(0)
    }
}
