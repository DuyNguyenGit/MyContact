package com.duy.mycontact.presentation.contact_info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.duy.mycontact.MainActivity
import com.duy.mycontact.R
import com.duy.mycontact.databinding.FragmentContactInfoBinding
import kotlinx.android.synthetic.main.fragment_contact_info.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactInfoFragment : Fragment() {

    private val TAG: String = ContactInfoFragment::class.java.simpleName
    private var contactId: Int? = null
    private val contactInfoViewModel: ContactInfoViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentContactInfoBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_contact_info, container, false)
        binding.contactInfoViewModel = contactInfoViewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar?.title = getString(R.string.contact)
        arguments?.let {
            contactId = ContactInfoFragmentArgs.fromBundle(it).contactId
            val username: String = ContactInfoFragmentArgs.fromBundle(it).userName
            val email: String = ContactInfoFragmentArgs.fromBundle(it).email
            edt_user_name.setText(username)
            edt_email.setText(email)
            progress_bar.visibility = View.VISIBLE
            contactInfoViewModel.getContactInfo(contactId!!)
        }
        btn_update.setOnClickListener {
            contactInfoViewModel.updateContactInfo(
                contactId, edt_user_name.text.toString(), edt_email.text.toString()
            )
        }
        contactInfoViewModel.contactModel.observe(viewLifecycleOwner, Observer {
            edt_email.setText(it.email)
            edt_user_name.setText(it.first_name)
            Glide.with(this)
                .load(it.avatar)
                .apply(RequestOptions.circleCropTransform())
                .into(img_avatar)
        })
        contactInfoViewModel.isWaiting.observe(viewLifecycleOwner, Observer {
            progress_bar.visibility = if(it) View.VISIBLE else View.GONE
            btn_update.isEnabled = !it
        })
    }

}