package com.bakikocak.android_mvvm_sample.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bakikocak.android_mvvm_sample.R
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    lateinit var navController: NavController

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        // Navigate to detail fragment
        go_to_detail_btn.setOnClickListener {
            val bundle = bundleOf("movieId" to 1) // dummy id is passed for now.
            navController.navigate(R.id.action_listFragment_to_detailFragment, bundle)
        }
    }
}