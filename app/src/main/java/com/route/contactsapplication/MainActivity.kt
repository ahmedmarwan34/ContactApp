package com.route.contactsapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var userName : EditText
    lateinit var phones : EditText
    lateinit var description : EditText
    lateinit var savedBtn : Button

    lateinit var contactsRecyclerView : RecyclerView
    lateinit var contactsAdapter: ContactsAdapter
    lateinit var contactsItem: ArrayList<ContactsItem>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userName = findViewById(R.id.name)
        phones = findViewById(R.id.phone)
        description = findViewById(R.id.description)
        savedBtn = findViewById(R.id.save_button)

        contactsRecyclerView = findViewById(R.id.contacts_recycler_view)
        contactsItem = ArrayList()
        contactsAdapter = ContactsAdapter(contactsItem)
        contactsRecyclerView.adapter = contactsAdapter

        savedBtn.setOnClickListener(View.OnClickListener {
            var name = userName.text.toString().trim()
            var phone = phones.text.toString().trim()
            var desc = description.text.toString().trim()

            if(name.length < 3){
                var toast = Toast.makeText(this, "Name should be 3 or more characters", Toast.LENGTH_SHORT)
                toast.show()
            }else if(phone.length != 11){
                var toast = Toast.makeText(this, "Phone number should be 11 numbers", Toast.LENGTH_SHORT)
                toast.show()
            }else{
                val newContact = ContactsItem(name, phone)
                contactsItem.add(newContact)
                contactsAdapter.notifyDataSetChanged()
                clearInputFields()
            }
        })

        contactsAdapter.onProfileClickListener = object : ContactsAdapter.OnItemClickListener{
            override fun onItemClick(contactsItem: ContactsItem, position: Int) {
                var intent = Intent(this@MainActivity, DetailsActivity::class.java)
                intent.putExtra("name", contactsItem.name)
                intent.putExtra("phone", contactsItem.phone)
                startActivity(intent)
            }
        }

        contactsAdapter.onCallClickListener = object : ContactsAdapter.OnItemClickListener{
            override fun onItemClick(contactsItem: ContactsItem, position: Int) {
                var dialIntent = Intent(Intent.ACTION_DIAL)
                dialIntent.data = Uri.parse("tele" + contactsItem.phone)
                startActivity(dialIntent)
            }

        }
    }


    fun clearInputFields() {
        userName.setText("")
        phones.setText("")
        description.setText("")
    }

}