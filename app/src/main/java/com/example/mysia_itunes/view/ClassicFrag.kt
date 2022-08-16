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
import com.example.mysia_itunes.databinding.FragmentClassicBinding
import com.example.mysia_itunes.model.MusicDetails
import com.example.mysia_itunes.view.adapter.ClassicAdapter
import com.example.mysia_itunes.viewmodel.MusicViewModel

open class ClassicFragment : Fragment() {
    private lateinit var binding: FragmentClassicBinding
    private lateinit var adapter: ClassicAdapter

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
        binding = FragmentClassicBinding.inflate(
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
        adapter = ClassicAdapter(emptyList()){
        }
        Log.d("test", "initViews: $adapter")
        binding.classicMusic.adapter=adapter
        binding.classicMusic.layoutManager= LinearLayoutManager(context)
    }

    open fun updatedAdapter(dataSet: List<MusicDetails>) {
        adapter.updatedList(dataSet)

    }

    open fun initObservables() {
        viewModel.allMusic()
        viewModel.classicMusic.observe(viewLifecycleOwner,
            Observer {
                updatedAdapter(it)
            })
    }

}