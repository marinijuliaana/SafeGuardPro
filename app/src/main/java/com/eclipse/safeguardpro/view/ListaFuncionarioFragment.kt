package com.eclipse.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eclipse.safeguardpro.R
import com.eclipse.safeguardpro.databinding.FragmentListaFuncionarioBinding
import com.eclipse.safeguardpro.databinding.FragmentLoginBinding
import com.eclipse.safeguardpro.view.adapter.FuncionarioAdapter

class ListaFuncionarioFragment : Fragment() {

    //Chamar adapter
    private lateinit var adapter: FuncionarioAdapter

    //criar o binding
    private var _binding: FragmentListaFuncionarioBinding? = null
    private val binding: FragmentListaFuncionarioBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListaFuncionarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Quando clicar em algum item da lista
        adapter = FuncionarioAdapter(listOf()) {

        }

        // Configura a recycler
        val recycler = binding.rvFuncionarios
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.cadastroFragment)
        }
    }
}