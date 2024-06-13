package com.eclipse.safeguardpro.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eclipse.safeguardpro.R
import com.eclipse.safeguardpro.databinding.FragmentListaEmprestimoBinding
import com.eclipse.safeguardpro.databinding.FragmentListaFuncionarioBinding
import com.eclipse.safeguardpro.view.adapter.EmprestimoAdapter
import com.eclipse.safeguardpro.view.adapter.FuncionarioAdapter
import com.eclipse.safeguardpro.viewmodel.EmprestimoViewModel
import com.eclipse.safeguardpro.viewmodel.FuncionarioViewModel

class ListaEmprestimoFragment : Fragment() {

    private val viewModel: EmprestimoViewModel by viewModels()

    //Chamar adapter
    private lateinit var adapter: EmprestimoAdapter

    //criar o binding
    private var _binding: FragmentListaEmprestimoBinding? = null
    private val binding: FragmentListaEmprestimoBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaEmprestimoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Quando clicar em algum item da lista
        adapter = EmprestimoAdapter(viewModel.emprestimoList.value) { emprestimo ->
            val emprestimoBundle = Bundle()
            emprestimoBundle.putInt("emprestimoId", emprestimo.id)
            arguments = emprestimoBundle
            findNavController().navigate(R.id.emprestimoFragment, arguments)
        }

        // Configura a recycler
        val recycler = binding.rvEmprestimos
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.emprestimoFragment)
        }

        viewModel.emprestimoList.observe(viewLifecycleOwner) {
            adapter.updateEmprestimo(it)
        }

        viewModel.erro.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Erro $it", Toast.LENGTH_LONG).show()
            Log.e("erro Emprestimos", it)
        }

        viewModel.loadEmprestimos()
    }
}