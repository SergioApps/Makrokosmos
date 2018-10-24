package com.keltapps.makrokosmos.menu.presentation.view

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.ActivityNavigator
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.databinding.FragmentMenuBinding
import com.keltapps.makrokosmos.menu.presentation.viewmodel.MenuViewModel
import com.keltapps.makrokosmos.navigation.Navigator
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MenuFragment : DaggerFragment() {

    @Inject
    internal lateinit var viewModel: MenuViewModel
    @Inject
    internal lateinit var navigator: Navigator

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
        viewModel.openMakrokosmos.observe(this, Observer { navigator.openMakrokosmos(findNavController()) })
        viewModel.openAbout.observe(this, Observer { navigator.openAboutInfo(findNavController()) })
        viewModel.openAuthor.observe(this, Observer { navigator.openAuthorInfo(findNavController()) })
        viewModel.openInterpreter.observe(this, Observer { navigator.openInterpreterInfo(findNavController()) })
        return binding.root
    }
}
