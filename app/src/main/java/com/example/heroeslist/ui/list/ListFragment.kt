package com.example.heroeslist.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.heroeslist.R
import com.example.heroeslist.data.network.HeroesApi
import com.example.heroeslist.data.repository.HeroesRepository
import com.example.heroeslist.databinding.ListFragmentBinding
import java.util.*

class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private lateinit var factory: ListViewModelFactory
    private lateinit var listFragmentBinding: ListFragmentBinding
    private val args: ListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false)
        return listFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = HeroesApi()
        val repository = HeroesRepository(api)

        factory = ListViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(ListViewModel::class.java)
        (activity as AppCompatActivity).title = requireContext().resources.getString(
            R.string.detail_title_template,
            args.mediaType.value,
            args.heroName
        )

        viewModel.itemList.observe(viewLifecycleOwner, Observer {
            setReceivedData(it)
        })

        setupRecyclerView()
        getList()
    }

    private fun getList() {
        listFragmentBinding.loadingProgressBar.visibility = View.VISIBLE
        viewModel.getList(args.mediaType, args.heroId)
    }

    private fun setupRecyclerView() {
        listFragmentBinding.itemRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = ListAdapter(mutableListOf())

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    val count = viewModel.itemList.value?.count() ?: 0
                    if(!canScrollVertically(1) &&
                            count < (viewModel.totalITems ?: 0) &&
                            !viewModel.isWaitingForRequest) {
                        getList()
                    }
                }
            })
        }
    }

    private fun setReceivedData(list: MutableList<String>) {
        listFragmentBinding.itemRecyclerView.apply {
            (adapter as ListAdapter).updateRecyclerView(list)
        }

        listFragmentBinding.loadingProgressBar.visibility = View.GONE
        listFragmentBinding.emptyContentMessage.apply {
            if (list.isEmpty()) {
                text = requireContext().getString(
                    R.string.empty_list_template, args.mediaType.value.toLowerCase(
                        Locale.getDefault()
                    ), args.heroName
                )

                visibility = View.VISIBLE
            } else {
                visibility = View.GONE
            }
        }

        viewModel.isWaitingForRequest = false
    }
}
