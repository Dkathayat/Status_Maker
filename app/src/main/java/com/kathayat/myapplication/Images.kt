package com.kathayat.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kathayat.myapplication.databinding.FragmentImagesBinding



class Images : Fragment() {

    private var binding: FragmentImagesBinding? = null
    private val binding get() = binding!!

    val recyclerView = FindViewByID<RecyclerView>(R.id.imageRecycler)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        FragmentImagesBinding.inflate(inflater, container, false).also { binding = it }


        return binding.root
    }
}

