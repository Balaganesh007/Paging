package com.example.paging.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paging.R
import com.example.paging.adapter.CharacterAdapter
import com.example.paging.databinding.FirstFragmentBinding
import com.example.paging.network.ObjectRickAndMortyApi
import kotlinx.android.synthetic.main.first_fragment.*
import kotlinx.coroutines.launch

class FirstFragment : Fragment() {


    private lateinit var viewModel: FirstViewModel
    private lateinit var binding: FirstFragmentBinding
    private lateinit var characterAdapter:CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.first_fragment, container, false)
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this,FirstViewModelFactory(ObjectRickAndMortyApi)).get(FirstViewModel::class.java)
        setupList()
        setupView()
    }

    private fun setupList(){
        characterAdapter = CharacterAdapter()
        //binding.recyclerview.adapter = characterAdapter

    }
    private fun setupView(){
        lifecycleScope.launch {
            viewModel.listData.collect {
                characterAdapter.submitData(it)
            }
        }
    }

}