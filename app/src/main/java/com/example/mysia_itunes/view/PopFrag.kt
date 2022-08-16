package com.example.mysia_itunes.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysia_itunes.databinding.FragmentPopBinding
import com.example.mysia_itunes.model.MusicDetails
import com.example.mysia_itunes.view.adapter.PopAdapter
import com.example.mysia_itunes.viewmodel.MusicViewModel

open class PopFragment: Fragment() {
    private lateinit var binding: FragmentPopBinding
    private lateinit var adapter: PopAdapter

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
        binding = FragmentPopBinding.inflate(
            inflater,
            container,
            false
        )
        initObservables()
        initViews()
        Log.d("binding", "onCreateView: ${binding.root}")
        return binding.root
    }

    open fun initViews() {
        adapter = PopAdapter(emptyList()){
        }
        Log.d("test", "initViews: $adapter")
        binding.popMusic.adapter=adapter
        binding.popMusic.layoutManager= LinearLayoutManager(context)
    }

    open fun updatedAdapter(dataSet: List<MusicDetails>) {
        adapter.updatedList(dataSet)

    }

    open fun initObservables() {
        viewModel.allMusic()
        viewModel.popMusic.observe(viewLifecycleOwner,
            Observer {
                updatedAdapter(it)
            })
    }

}