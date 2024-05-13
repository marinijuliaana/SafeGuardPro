package com.eclipse.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eclipse.safeguardpro.R
import com.eclipse.safeguardpro.databinding.FragmentNotaFiscalBinding
import com.eclipse.safeguardpro.databinding.FragmentRelatorioEpiBinding
import com.eclipse.safeguardpro.view.adapter.EpiAdapter
import com.eclipse.safeguardpro.view.adapter.FuncionarioAdapter

class RelatorioEpiFragment : Fragment() {

    //Chamar adapter
    private lateinit var adapter: EpiAdapter

    //criar o binding
    private var _binding: FragmentRelatorioEpiBinding? = null
    private val binding: FragmentRelatorioEpiBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRelatorioEpiBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Quando clicar em algum item da lista
        adapter = EpiAdapter(listOf()) {

        }

        // Configura a recycler
        val recycler = binding.rvEpi
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.cadastroEpiFragment)
        }
    }
}