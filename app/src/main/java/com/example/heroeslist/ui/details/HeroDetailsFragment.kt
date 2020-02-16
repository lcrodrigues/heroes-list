package com.example.heroeslist.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.heroeslist.R
import com.example.heroeslist.data.model.Hero
import com.example.heroeslist.data.network.HeroesApi
import com.example.heroeslist.data.repository.HeroesRepository
import com.example.heroeslist.databinding.HeroDetailsFragmentBinding

class HeroDetailsFragment : Fragment() {

    private lateinit var viewModel: HeroDetailsViewModel
    private lateinit var factory: HeroDetailsViewModelFactory
    private lateinit var heroDetailsBinding: HeroDetailsFragmentBinding
    private val args: HeroDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

        heroDetailsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.hero_details_fragment,
            container,
            false
        )

        return heroDetailsBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val id = args.heroId
        val api = HeroesApi()
        val repository = HeroesRepository(api)

        factory = HeroDetailsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(HeroDetailsViewModel::class.java)

        viewModel.hero.observe(viewLifecycleOwner, Observer {
            setHeroData(it)
        })

        getDetails(id)
        setupButtons()
    }

    private fun getDetails(id: String) {
        heroDetailsBinding.loadingProgressBar.visibility = View.VISIBLE
        heroDetailsBinding.descriptionCardView.visibility = View.GONE
        heroDetailsBinding.comicsCardView.visibility = View.GONE
        heroDetailsBinding.seriesCardView.visibility = View.GONE
        heroDetailsBinding.eventsCardView.visibility = View.GONE
        heroDetailsBinding.storiesCardView.visibility = View.GONE

        viewModel.getHeroDetails(id)
    }

    private fun setHeroData(hero: Hero) {
        heroDetailsBinding.hero = hero

        heroDetailsBinding.hero?.let {
            if (it.description.isEmpty()) {
                it.description = resources.getString(R.string.no_description_available)
            }
        }

        heroDetailsBinding.loadingProgressBar.visibility = View.GONE
        heroDetailsBinding.descriptionCardView.visibility = View.VISIBLE
        heroDetailsBinding.comicsCardView.visibility = View.VISIBLE
        heroDetailsBinding.seriesCardView.visibility = View.VISIBLE
        heroDetailsBinding.eventsCardView.visibility = View.VISIBLE
        heroDetailsBinding.storiesCardView.visibility = View.VISIBLE
    }

    private fun setupButtons() {
        val id = args.heroId

        heroDetailsBinding.comicsCardView.setOnClickListener {
            val address = "$id/${requireContext().getString(R.string.comics)}"
            val action = HeroDetailsFragmentDirections.requestListOfItems(address)
            findNavController().navigate(action)
        }

        heroDetailsBinding.seriesCardView.setOnClickListener {
            val address = "$id/${requireContext().getString(R.string.series)}"
            val action = HeroDetailsFragmentDirections.requestListOfItems(address)
            findNavController().navigate(action)
        }

        heroDetailsBinding.eventsCardView.setOnClickListener {
            val address = "$id/${requireContext().getString(R.string.events)}"
            val action = HeroDetailsFragmentDirections.requestListOfItems(address)
            findNavController().navigate(action)
        }

        heroDetailsBinding.storiesCardView.setOnClickListener {
            val address = "$id/${requireContext().getString(R.string.stories)}"
            val action = HeroDetailsFragmentDirections.requestListOfItems(address)
            findNavController().navigate(action)
        }
    }
}
