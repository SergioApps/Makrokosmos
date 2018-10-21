package com.keltapps.makrokosmos.menu.presentation.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.databinding.FragmentMenuBinding
import com.keltapps.makrokosmos.menu.presentation.viewmodel.MenuViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MenuFragment : DaggerFragment() {

    @Inject
    internal lateinit var viewModel: MenuViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMenuBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_menu,
                container,
                false
        )
        binding.viewModel = viewModel
        return binding.root
    }
}
