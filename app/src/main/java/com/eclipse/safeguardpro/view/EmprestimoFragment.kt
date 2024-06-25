package com.eclipse.safeguardpro.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.eclipse.safeguardpro.databinding.FragmentEmprestimoBinding
import com.eclipse.safeguardpro.service.model.Emprestimo
import com.eclipse.safeguardpro.service.model.Epi
import com.eclipse.safeguardpro.service.model.Funcionario
import com.eclipse.safeguardpro.viewmodel.EmprestimoViewModel
import com.eclipse.safeguardpro.viewmodel.EpiViewModel
import com.eclipse.safeguardpro.viewmodel.FuncionarioViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import okhttp3.internal.wait

class EmprestimoFragment : Fragment() {

    private val viewModel: EmprestimoViewModel by viewModels()
    private val viewModelFuncionario: FuncionarioViewModel by viewModels()
    private val viewModelEpi: EpiViewModel by viewModels()

    private var entrega = ""
    private var _cpf = 0
    private var devolucao = ""
    private var ca = 0
    private var click = false

    private lateinit var epiByCa: Epi
    private lateinit var funcionarioByCpf: Funcionario

    private var _binding: FragmentEmprestimoBinding? = null
    private val binding: FragmentEmprestimoBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmprestimoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //chama a função para exibir o emprestimo
        arguments?.let {
            viewModel.getEmprestimo(it.getInt("emprestimoId"))
        }

        binding.btnEnviar.setOnClickListener {
            val data_entrega = binding.edtDataEntrega.editableText.toString()
            val data_devolucao = binding.edtDataEntrega.editableText.toString()
            val cpf = binding.edtCpf.editableText.toString().toInt()
            val numero_ca = binding.edtCa.editableText.toString().toInt()

            if (data_entrega != "" && data_devolucao != "" && numero_ca != 0 && cpf != 0) {

                viewModelEpi.getEpiByCa(numero_ca)
                viewModelFuncionario.getFuncionarioByCpf(cpf)

                entrega = data_entrega
                devolucao = data_devolucao
                _cpf = cpf
                ca = numero_ca

                click = true
            } else {
                Toast.makeText(requireContext(), "Digite os dados", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnDeletar.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Exclusão de emprestimo")
                .setMessage("você realmente deseja excluir ?")
                .setPositiveButton("sim") { _, _ ->
                    viewModel.delete(viewModel.emprestimo.value?.id ?: 0)
                    findNavController().navigateUp()
                }
                .setNegativeButton("não") { _, _ -> }
                .show()
        }

        viewModel.emprestimo.observe(viewLifecycleOwner) { emprestimo ->
            binding.edtDataEntrega.setText(emprestimo.data_entrega)
            binding.edtDataDevolucao.setText(emprestimo.data_entrega)
            binding.edtCpf.setText(emprestimo.cpf.toString())
            binding.edtCa.setText(emprestimo.numero_ca.toString())
        }

        viewModelEpi.epi.observe(viewLifecycleOwner) {
            epiByCa = it
        }

        viewModelFuncionario.funcionario.observe(viewLifecycleOwner) {
            funcionarioByCpf = it

            if (click) {
                val emprestimo = Emprestimo(
                    data_entrega = entrega,
                    cpf = _cpf,
                    numero_ca = ca,
                    data_devolucao = devolucao,
                    epi_id = epiByCa.id,
                    funcionario_id = funcionarioByCpf.id,
                )

                viewModel.emprestimo.value?.let {
                    emprestimo.id = it.id
                    viewModel.update(emprestimo)
                } ?: run {
                    viewModel.insert(emprestimo)
                }

                binding.edtDataEntrega.editableText.clear()
                binding.edtDataDevolucao.editableText.clear()
                binding.edtCpf.editableText.clear()
                binding.edtCa.editableText.clear()

                findNavController().navigateUp()
            }
        }

        viewModel.erro.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Erro $it", Toast.LENGTH_LONG).show()
            Log.e("erro Emprestimo", it)
        }
    }
}