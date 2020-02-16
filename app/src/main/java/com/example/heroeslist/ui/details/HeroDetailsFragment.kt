package com.example.heroeslist.ui.details

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.heroeslist.R

class HeroDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = HeroDetailsFragment()
    }

    private lateinit var viewModel: HeroDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.hero_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HeroDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
