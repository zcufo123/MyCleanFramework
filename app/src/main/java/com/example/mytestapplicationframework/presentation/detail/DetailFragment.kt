package com.example.mytestapplicationframework.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.mytestapplicationframework.data.entities.Entity
import com.example.mytestapplicationframework.databinding.DetailFragmentBinding
import com.example.mytestapplicationframework.utils.Constant
import com.example.mytestapplicationframework.utils.Resource
import com.example.mytestapplicationframework.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var binding: DetailFragmentBinding by autoCleared()
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(Constant.ENTITY_ID)?.let { setupObservers(it) }
    }

    private fun setupObservers(id: Int) {
        viewModel.find(id).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    bindEntity(it.data!!)
                    binding.progressBar.visibility = View.GONE
                    binding.detailCl.visibility = View.VISIBLE
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.detailCl.visibility = View.GONE
                }
            }
        })
    }

    private fun bindEntity(entity: Entity) {
        binding.name.text = entity.name
        binding.species.text = entity.species
        binding.status.text = entity.status
        binding.gender.text = entity.gender
        Glide.with(binding.root)
            .load(entity.image)
            .transform(CircleCrop())
            .into(binding.image)
    }
}
