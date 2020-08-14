package com.example.jetpack.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.jetpack.R
import kotlinx.android.synthetic.main.fragment_home_pager.*


class HomePagerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageView.setOnClickListener {
            val navController = it.findNavController()
            navController.navigate(R.id.action_homePagerFragment_to_fragment1)
        }
    }
}