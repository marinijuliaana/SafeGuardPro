package com.eclipse.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.eclipse.safeguardpro.R
import com.eclipse.safeguardpro.databinding.FragmentDetalhesBinding
import com.eclipse.safeguardpro.databinding.FragmentEntregaBinding

class DetalhesFragment : Fragment() {
    //criar o binding
    private var _binding: FragmentDetalhesBinding? = null
    private val binding: FragmentDetalhesBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetalhesBinding.inflate(inflater, container, false)
        return binding.root
    }

}