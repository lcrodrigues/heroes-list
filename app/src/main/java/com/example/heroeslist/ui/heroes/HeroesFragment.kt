package com.example.heroeslist.ui.heroes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.heroeslist.R
import com.example.heroeslist.data.network.HeroesApi
import com.example.heroeslist.data.repository.HeroesRepository
import kotlinx.android.synthetic.main.heroes_fragment.*


class HeroesFragment : Fragment() {

    companion object {
        fun newInstance() = HeroesFragment()
    }

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

        val repository = HeroesRepository()

        factory = HeroesViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(HeroesViewModel::class.java)

        viewModel.heroes.observe(viewLifecycleOwner, Observer { heroes ->
            recyclerViewHeroes.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = HeroesAdapter(heroes)
            }
        })

        viewModel.getHeroesList()
    }

}
