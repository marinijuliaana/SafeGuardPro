package com.eclipse.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.eclipse.safeguardpro.R
import com.eclipse.safeguardpro.databinding.FragmentMenuBinding
import com.eclipse.safeguardpro.databinding.FragmentNotaFiscalBinding

class NotaFiscalFragment : Fragment() {
    //criar o binding
    private var _binding: FragmentNotaFiscalBinding? = null
    private val binding: FragmentNotaFiscalBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotaFiscalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnEmitir.setOnClickListener {
            findNavController().navigate(R.id.menuFragment)
        }
    }
}