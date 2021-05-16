package com.duy.mycontact.presentation.contact_list.datasource

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.duy.mycontact.R
import com.duy.mycontact.data.contact_list.Contact

class ContactListAdapter(private val listener: ContactListAdapterInteraction) :
    PagedListAdapter<Contact, ContactListAdapter.ContactListViewHolder>(contactDiffCallback) {

    lateinit var context: Context

    interface ContactListAdapterInteraction {
        fun onUserItemClick(contact: Contact)
    }

    inner class ContactListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contactItem: LinearLayout = itemView.findViewById(R.id.contact_item)
        val imgAvatar: ImageView = itemView.findViewById(R.id.img_avatar)
        val tvName: AppCompatTextView = itemView.findViewById(R.id.tv_name)
        val tvEmail: AppCompatTextView = itemView.findViewById(R.id.tv_email)
    }

    override fun onBindViewHolder(holder: ContactListViewHolder, position: Int) {
        val contact = getItem(position)
        contact?.let {
            Glide.with(context)
                .load(it.avatar)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.imgAvatar)

            holder.tvName.text = "${it.first_name} ${it.last_name}"
            holder.tvEmail.text = "${it.email}"

            holder.contactItem.setOnClickListener {
                listener.onUserItemClick(contact)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.layout_contact_item, parent, false)
        return ContactListViewHolder(view)
    }

    companion object {
        val contactDiffCallback = object : DiffUtil.ItemCallback<Contact>() {
            override fun areItemsTheSame(
                oldItem: Contact,
                newItem: Contact
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Contact,
                newItem: Contact
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}