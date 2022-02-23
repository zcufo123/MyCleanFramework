package com.example.mytestapplicationframework.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytestapplicationframework.R
import com.example.mytestapplicationframework.databinding.MainFragmentBinding
import com.example.mytestapplicationframework.utils.Constant
import com.example.mytestapplicationframework.utils.Resource
import com.example.mytestapplicationframework.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), MainAdapter.MainItemListener {

    private var binding: MainFragmentBinding by autoCleared()
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = MainAdapter(this)
        binding.mainRv.layoutManager = LinearLayoutManager(requireContext())
        binding.mainRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.entities.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClicked(id: Int) {
        findNavController().navigate(
            R.id.action_mainFragment_to_detailFragment,
            bundleOf(Constant.ENTITY_ID to id)
        )
    }
}
