package com.keltapps.makrokosmos.info.presentation.view

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.databinding.FragmentInfoBinding
import com.keltapps.makrokosmos.info.presentation.viewmodel.InfoViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class InfoFragment : DaggerFragment() {

    @Inject
    internal lateinit var viewModel: InfoViewModel

    private lateinit var binding: FragmentInfoBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_info,
                container,
                false
        )
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        viewModel.initialize(
                InfoFragmentArgs.fromBundle(arguments).infoScreen
        )
        setupActionBar()
        return binding.root
    }

    private fun setupActionBar() {
        with(activity as AppCompatActivity) {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbar.setNavigationOnClickListener { binding.root.findNavController().navigateUp() }
    }
}