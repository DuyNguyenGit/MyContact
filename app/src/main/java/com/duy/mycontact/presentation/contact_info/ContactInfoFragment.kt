package com.duy.mycontact.presentation.contact_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.duy.mycontact.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactInfoFragment : Fragment() {

    private val contactInfoViewModel: ContactInfoViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ContactDetailFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_contact_info, container, false)
        binding.userDetailVM = contactInfoViewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            val username: String = ContactDetailFragmentArgs.fromBundle(it).username
            contactInfoViewModel.getContactInfo(username)
        }
    }

}