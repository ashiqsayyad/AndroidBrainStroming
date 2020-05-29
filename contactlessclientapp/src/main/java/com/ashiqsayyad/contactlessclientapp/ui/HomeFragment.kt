package com.ashiqsayyad.contactlessclientapp.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider

import com.ashiqsayyad.contactlessclientapp.R
import com.ashiqsayyad.contactlessclientapp.databinding.HomeFragmentBinding
import com.ashiqsayyad.contactlessclientapp.viewmodels.HomeViewModel

//https://developer.android.com/topic/libraries/architecture/viewmodel
//https://developer.android.com/kotlin/ktx
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }
    //way1
   // private lateinit var viewModel: HomeViewModel or by lazy

    //below are defined in     implementation 'androidx.fragment:fragment-ktx:1.2.4'
    //way2  to have owner as fragment
    // private val viewModel by viewModels<TasksViewModel>()
    //shared view model between fragments for same activity
    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var viewDataBinding: HomeFragmentBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //return inflater.inflate(R.layout.home_fragment, container, false)
        viewDataBinding = HomeFragmentBinding.inflate(inflater, container, false).apply {
            homeviewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //way 1
        //viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

    }

}
