package com.eclipse.safeguardpro.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.eclipse.safeguardpro.R
import com.eclipse.safeguardpro.databinding.FragmentCadastroBinding
import com.eclipse.safeguardpro.databinding.FragmentCadastroEpiBinding
import com.eclipse.safeguardpro.service.model.Epi
import com.eclipse.safeguardpro.viewmodel.EmprestimoViewModel
import com.eclipse.safeguardpro.viewmodel.EpiViewModel
import java.time.LocalDateTime

class CadastroEpiFragment : Fragment() {

    private val viewModel: EpiViewModel by viewModels()

    //criar o binding
    private var _binding: FragmentCadastroEpiBinding? = null
    private val binding: FragmentCadastroEpiBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentCadastroEpiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //chama a função para exibir o emprestimo
        arguments?.let {
            viewModel.getEpi(it.getInt("epiId"))
        }

        binding.btnCadastrar.setOnClickListener {
            var nome_equipamento = binding.edtNomeEquipamento.editableText.toString()
            var validade_epi = binding.edtValidadeEpi.editableText.toString()
            var tipo_protecao = binding.edtTipoProtecao.editableText.toString()
            var numero_ca = binding.edtNumeroCa.editableText.toString().toInt()
            var tempo_uso = binding.edtTempoUso.editableText.toString()

            if (nome_equipamento != "" && validade_epi != "" && tipo_protecao != "" && numero_ca != 0 && tempo_uso != ""){
                val epi = Epi (
                    nome_equipamento = nome_equipamento,
                    validade_epi = validade_epi,
                    tipo_protecao = tipo_protecao,
                    numero_ca = numero_ca,
                    tempo_uso = tempo_uso,
                )

                viewModel.epi.value?.let {
                    epi.id = it.id
                    viewModel.update(epi)
                } ?: run {
                    viewModel.insert(epi)
                }

                binding.edtNomeEquipamento.editableText.clear()
                binding.edtValidadeEpi.editableText.clear()
                binding.edtTipoProtecao.editableText.clear()
                binding.edtNumeroCa.editableText.clear()
                binding.edtTempoUso.editableText.clear()

                findNavController().navigateUp()
            }else {
                Toast.makeText(requireContext(), "Digite os dados", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnDeletar.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Exclusão de pessoa")
                .setMessage("você realmente deseja excluir ?")
                .setPositiveButton("sim") { _, _ ->
                    viewModel.delete(viewModel.epi.value?.id ?: 0)
                    findNavController().navigateUp()
                }
                .setNegativeButton("não") { _, _ -> }
                .show()
        }

        viewModel.epi.observe(viewLifecycleOwner) { epi ->
            binding.edtNomeEquipamento.setText(epi.nome_equipamento)
            binding.edtValidadeEpi.setText(epi.validade_epi)
            binding.edtNumeroCa.setText(epi.numero_ca)
            binding.edtTipoProtecao.setText(epi.tipo_protecao)
            binding.edtTempoUso.setText(epi.tempo_uso)

            binding.btnDeletar.visibility = View.VISIBLE
        }

        viewModel.erro.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Erro $it", Toast.LENGTH_LONG).show()
            Log.e("erro Emprestimo", it)
        }

    }
}
