package com.example.heroeslist.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.heroeslist.R
import com.example.heroeslist.data.types.MediaType
import com.example.heroeslist.data.model.hero.Hero
import com.example.heroeslist.data.network.HeroesApi
import com.example.heroeslist.data.repository.HeroesRepository
import com.example.heroeslist.databinding.HeroDetailsFragmentBinding
import com.example.heroeslist.util.isConnected
import com.example.heroeslist.util.showSnackbarConnection

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
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).title = args.heroName 

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
            setReceivedData(it)
        })

        if(isConnected()) {
            getDetails(id)
        } else {
            showSnackbarConnection(requireView(), requireContext())
        }

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

    private fun setReceivedData(hero: Hero) {
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
        val name = args.heroName

        heroDetailsBinding.comicsCardView.setOnClickListener {
            val action = HeroDetailsFragmentDirections.requestListOfItems(MediaType.Comics, id, name)
            findNavController().navigate(action)
        }

        heroDetailsBinding.seriesCardView.setOnClickListener {
            val action = HeroDetailsFragmentDirections.requestListOfItems(MediaType.Series, id, name)
            findNavController().navigate(action)
        }

        heroDetailsBinding.eventsCardView.setOnClickListener {
            val action = HeroDetailsFragmentDirections.requestListOfItems(MediaType.Events, id, name)
            findNavController().navigate(action)
        }

        heroDetailsBinding.storiesCardView.setOnClickListener {
            val action = HeroDetailsFragmentDirections.requestListOfItems(MediaType.Stories, id, name)
            findNavController().navigate(action)
        }
    }
}
