package com.eclipse.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.eclipse.safeguardpro.R
import com.eclipse.safeguardpro.databinding.FragmentCadastroBinding
import com.eclipse.safeguardpro.databinding.FragmentDetalhesBinding

class CadastroFragment : Fragment() {
    //criar o binding
    private var _binding: FragmentCadastroBinding? = null
    private val binding: FragmentCadastroBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCadastroBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCadastrar.setOnClickListener {
            findNavController().navigate(R.id.menuFragment)
        }
        binding.btnEditar.setOnClickListener {
            findNavController().navigate(R.id.menuFragment)
        }

    }
}
