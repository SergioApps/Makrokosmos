package com.keltapps.makrokosmos.info.presentation.view

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.databinding.FragmentInfoBinding
import com.keltapps.makrokosmos.info.presentation.viewmodel.InfoViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_info.*
import javax.inject.Inject

class InfoFragment : DaggerFragment() {

    private companion object {
        const val IMAGE_ASPECT_RATIO = 1.51
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setImageViewAspectRatio()
    }

    private fun setImageViewAspectRatio() {
        val displayMetrics = DisplayMetrics()
        this.activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        binding.imageViewInfo?.minimumHeight = (displayMetrics.widthPixels * IMAGE_ASPECT_RATIO).toInt()
    }

    override fun onDestroyView() {
        binding.toolbar.setNavigationOnClickListener(null)
        (activity as AppCompatActivity).setSupportActionBar(null)
        super.onDestroyView()
    }
}