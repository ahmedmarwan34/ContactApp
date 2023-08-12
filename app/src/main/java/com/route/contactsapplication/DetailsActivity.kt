package com.route.contactsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {
    lateinit var user : TextView
    lateinit var phone : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        user = findViewById(R.id.name)
        phone = findViewById(R.id.phone)

        var strUser : String? = intent.getStringExtra("name")
        var strPhone : String? = intent.getStringExtra("phone")

        user.setText(strUser)
        phone.setText(strPhone)
    }
}