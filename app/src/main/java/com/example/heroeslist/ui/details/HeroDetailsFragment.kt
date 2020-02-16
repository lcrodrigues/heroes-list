package com.example.heroeslist.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.heroeslist.R
import com.example.heroeslist.data.network.HeroesApi
import com.example.heroeslist.data.repository.HeroesRepository

class HeroDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = HeroDetailsFragment()
    }

    private lateinit var viewModel: HeroDetailsViewModel
    private lateinit var factory: HeroDetailsViewModelFactory
    val args: HeroDetailsFragmentArgs by navArgs()

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
        return inflater.inflate(R.layout.hero_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val id = args.heroId
        val api = HeroesApi()
        val repository = HeroesRepository(api)

        factory = HeroDetailsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(HeroDetailsViewModel::class.java)

        viewModel.hero.observe(viewLifecycleOwner, Observer {

        })

        viewModel.getHeroDetails(id)
    }


}
