package com.keltapps.makrokosmos.menu.presentation.view

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.databinding.FragmentMenuBinding
import com.keltapps.makrokosmos.menu.presentation.viewmodel.MenuViewModel
import com.keltapps.makrokosmos.navigation.Navigator
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_menu.*
import javax.inject.Inject

class MenuFragment : DaggerFragment() {

    private companion object {
        const val STATE_SCROLL = "stateScroll"
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scrollView.scrollY = savedInstanceState?.getInt(STATE_SCROLL) ?: 0
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_SCROLL, scrollView.scrollY)
    }
}
