package com.route.contactsapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ContactsAdapter(val contactsItem: ArrayList<ContactsItem>) : Adapter<ContactsAdapter.ContactsViewHolder>() {


    class ContactsViewHolder(view : View) : ViewHolder(view){
        var name : TextView = view.findViewById(R.id.name)
        var phone : TextView = view.findViewById(R.id.phone)
        var image : ImageView = view.findViewById(R.id.contact)
        var call : ImageView = view.findViewById(R.id.call)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.contact_item, parent, false)
        return ContactsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contactsItem.size
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val item = contactsItem.get(position)
        holder.name.text = item.name
        holder.phone.text = item.phone

        if(onProfileClickListener != null){
            holder.image.setOnClickListener{
                onProfileClickListener?.onItemClick(item, position)
            }
        }

        if(onCallClickListener != null){
            holder.call.setOnClickListener{
                onCallClickListener?.onItemClick(item, position)
            }
        }



    }

    var onProfileClickListener : OnItemClickListener? = null
    var onCallClickListener : OnItemClickListener? = null

    interface OnItemClickListener{
        fun onItemClick(contactsItem: ContactsItem, position: Int)
    }
}