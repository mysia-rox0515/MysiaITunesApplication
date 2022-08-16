package com.example.mysia_itunes.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysia_itunes.databinding.FragmentRockBinding
import com.example.mysia_itunes.model.MusicDetails
import com.example.mysia_itunes.view.adapter.RockAdapter
import com.example.mysia_itunes.viewmodel.MusicViewModel

open class RockFragment: Fragment() {
    private lateinit var binding: FragmentRockBinding
    private lateinit var adapter: RockAdapter

    private val viewModel: MusicViewModel by lazy {
        ViewModelProvider(
            this

        )[MusicViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentRockBinding.inflate(
            inflater,
            container,
            false
        )
        initObservables()
        initViews()
        return binding.root
    }
    open fun initObservables() {
        viewModel?.allMusic()
        viewModel?.rockMusic?.observe(viewLifecycleOwner, Observer {
            updatedAdapter(it)
        })

    }

    open fun initViews() {
        adapter = RockAdapter(emptyList()){
        }
        Log.d("test", "initViews: $adapter")
        binding.rockMusic.adapter=adapter
        binding.rockMusic.layoutManager= LinearLayoutManager(context)
    }


    open fun updatedAdapter(dataSet: List<MusicDetails>) {
        adapter.updatedList(dataSet)

    }



}