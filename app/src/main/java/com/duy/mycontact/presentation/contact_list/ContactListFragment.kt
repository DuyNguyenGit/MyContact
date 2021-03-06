package com.duy.mycontact.presentation.contact_list

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.duy.mycontact.MainActivity
import com.duy.mycontact.R
import com.duy.mycontact.data.common.Contact
import com.duy.mycontact.databinding.FragmentContactListBinding
import com.duy.mycontact.presentation.contact_list.datasource.ContactListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A fragment representing a list of Items.
 */
class ContactListFragment : Fragment(), ContactListAdapter.ContactListAdapterInteraction {

    private val contactListViewModel: ContactListViewModel by viewModel()
    private lateinit var itemViewer: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentContactListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_contact_list, container, false)

        binding.contactListViewModel = contactListViewModel
        binding.lifecycleOwner = this
        itemViewer = binding.root.findViewById(R.id.recycler_view)
        initAdapterAndObserve()
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val displayName = ContactListFragmentArgs.fromBundle(it).displayName
            (requireActivity() as MainActivity).supportActionBar?.title =
                getString(R.string.welcome, displayName)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_filter, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filter_name -> {
                updateData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateData() {
        contactListViewModel.updateQuery()
    }

    private fun initAdapterAndObserve() {
        val adapter = ContactListAdapter(this)
        itemViewer.layoutManager = LinearLayoutManager(context)
        itemViewer.adapter = adapter
        contactListViewModel.setUpObservers(this)

        contactListViewModel.contactListLiveData.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    override fun onUserItemClick(contact: Contact) {
        val direction =
            ContactListFragmentDirections.actionContactListFragmentToContactInfoFragment(
                contact.id, contact.first_name, contact.email
            )
        findNavController().navigate(direction)
    }
}