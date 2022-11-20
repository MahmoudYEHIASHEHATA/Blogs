package com.shahry.feature.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.shahry.base.BaseFragment
import com.shahry.feature.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>() {


    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {

    }
}