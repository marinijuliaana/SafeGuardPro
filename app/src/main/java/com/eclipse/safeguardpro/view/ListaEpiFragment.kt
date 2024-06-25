package com.eclipse.safeguardpro.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eclipse.safeguardpro.R
import com.eclipse.safeguardpro.databinding.FragmentListaEpiBinding
import com.eclipse.safeguardpro.service.model.Epi
import com.eclipse.safeguardpro.service.model.Login
import com.eclipse.safeguardpro.view.adapter.EpiAdapter
import com.eclipse.safeguardpro.viewmodel.EmprestimoViewModel
import com.eclipse.safeguardpro.viewmodel.EpiViewModel

class ListaEpiFragment : Fragment() {

    private val viewModel: EpiViewModel by viewModels()
    private val viewModelEmprestimo: EmprestimoViewModel by viewModels()

    //Chamar adapter
    private lateinit var adapter: EpiAdapter

    private val episFuncionario = mutableListOf<Epi>()

    //criar o binding
    private var _binding: FragmentListaEpiBinding? = null
    private val binding: FragmentListaEpiBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaEpiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Quando clicar em algum item da lista
        adapter = EpiAdapter(viewModel.epiList.value) { epi ->
            val epiBundle = Bundle()
            epiBundle.putInt("epiId", epi.id)
            arguments = epiBundle
            findNavController().navigate(R.id.cadastroEpiFragment, arguments)
        }

        // Configura a recycler
        val recycler = binding.rvEpi
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.cadastroEpiFragment)
        }

        viewModel.epi.observe(viewLifecycleOwner) { epi ->
            episFuncionario.add(epi)
//            TODO testar aqui ou na linha 81
//            adapter.updateEpi(episFuncionario)
        }

        viewModel.erro.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Erro $it", Toast.LENGTH_LONG).show()
            Log.e("erro epis", it)
        }

        viewModel.epiList.observe(viewLifecycleOwner) {
            adapter.updateEpi(it)
            Toast.makeText(requireContext(), "Epis: $it", Toast.LENGTH_LONG).show()
        }

        viewModelEmprestimo.emprestimoList.observe(viewLifecycleOwner) { listEntregas ->
            val entregasFuncionario = listEntregas.filter { it.funcionario_id == Login.userId }

            entregasFuncionario.forEach {
                viewModel.getEpi(it.epi_id)
            }

//            TODO testar aqui ou na linha 65
            adapter.updateEpi(episFuncionario)
            Toast.makeText(requireContext(), "Epis: $listEntregas", Toast.LENGTH_LONG).show()
        }

        if (Login.userAdmin){
            viewModel.loadEpis()
        } else {
            viewModelEmprestimo.loadEmprestimos()
        }
    }
}