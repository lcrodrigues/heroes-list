package com.example.heroeslist.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.heroeslist.R
import com.example.heroeslist.data.network.HeroesApi
import com.example.heroeslist.data.repository.HeroesRepository
import com.example.heroeslist.databinding.ListFragmentBinding
class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private lateinit var factory: ListViewModelFactory
    private lateinit var listFragmentBinding: ListFragmentBinding
    private val args: ListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false)
        return listFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = HeroesApi()
        val repository = HeroesRepository(api)

        factory = ListViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(ListViewModel::class.java)

        viewModel.itemList.observe(viewLifecycleOwner, Observer {
            listFragmentBinding.itemRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                adapter = ListAdapter(it)
            }

            listFragmentBinding.loadingProgressBar.visibility = View.GONE
        })

        getList()
    }

    private fun getList() {
        listFragmentBinding.loadingProgressBar.visibility = View.VISIBLE
        viewModel.getList(args.mediaType, args.heroId)
    }
}
