package com.example.heroeslist.ui.heroes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.heroeslist.R
import com.example.heroeslist.data.network.HeroesApi
import com.example.heroeslist.data.repository.HeroesRepository
import kotlinx.android.synthetic.main.heroes_fragment.*


class HeroesFragment : Fragment() {
    private lateinit var viewModel: HeroesViewModel
    private lateinit var factory: HeroesViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.heroes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = HeroesApi()
        val repository = HeroesRepository(api)

        factory = HeroesViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(HeroesViewModel::class.java)
        setupRecyclerView()

        viewModel.heroes.observe(viewLifecycleOwner, Observer { heroes ->
            recyclerViewHeroes.apply {
                (adapter as HeroesAdapter).updateRecyclerView(heroes)
            }

            loadingProgressBar.visibility = View.GONE
            viewModel.isWaitingForRequest = false
        })

        if (viewModel.heroes.value.isNullOrEmpty()) {
            getList()
        }
    }

    private fun getList() {
        loadingProgressBar.visibility = View.VISIBLE
        viewModel.getHeroesList()
    }

    private fun setupRecyclerView() {
        recyclerViewHeroes.apply {
            adapter = HeroesAdapter(mutableListOf(), this@HeroesFragment::onHeroClick)
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    val count = viewModel.heroes.value?.count() ?: 0
                    if (!canScrollVertically(1) &&
                        count < (viewModel.totalItems ?: 0) &&
                        !viewModel.isWaitingForRequest
                    ) {
                        getList()
                    }
                }
            })
        }
    }

    private fun onHeroClick(id: String) {
        val action = HeroesFragmentDirections.startHeroDetailsWithId(id)
        findNavController().navigate(action)
    }
}
