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
import com.eclipse.safeguardpro.databinding.FragmentCadastroBinding
import com.eclipse.safeguardpro.service.model.Funcionario
import com.eclipse.safeguardpro.service.model.Login
import com.eclipse.safeguardpro.viewmodel.FuncionarioViewModel

class CadastroFragment : Fragment() {

    private val viewModel: FuncionarioViewModel by viewModels()

    //criar o binding
    private var _binding: FragmentCadastroBinding? = null
    private val binding: FragmentCadastroBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCadastroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //chama a função para exibir o emprestimo
        arguments?.let {
            viewModel.getFuncionario(it.getInt("funcionarioId"))
        }

        binding.btnCadastrar.setOnClickListener {
            val nome = binding.edtNome.editableText.toString()
            val senha = binding.edtSenha.editableText.toString()
            val cargo = binding.edtCargo.editableText.toString()
            val cpf = binding.edtCpf.editableText.toString().toInt()
//            TODO Add o checkbox no xml
//            val admin = binding.chkAdmin.isChecked()

            if (nome != "" && senha != "" && cargo != "" && cpf != 0) {
                val funcionario = Funcionario(
                    nome = nome,
                    cpf = cpf,
                    cargo = cargo,
                    senha = senha
                )

                viewModel.funcionario.value?.let {
                    funcionario.id = it.id
                    viewModel.update(funcionario)
                } ?: run {
                    viewModel.insert(funcionario)
                }

                binding.edtNome.editableText.clear()
                binding.edtCpf.editableText.clear()
                binding.edtCargo.editableText.clear()
                binding.edtSenha.editableText.clear()

                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), "Digite os dados", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnDeletar.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Exclusão de pessoa")
                .setMessage("você realmente deseja excluir ?")
                .setPositiveButton("sim") { _, _ ->
                    viewModel.delete(viewModel.funcionario.value?.id ?: 0)
                    findNavController().navigateUp()
                }
                .setNegativeButton("não") { _, _ -> }
                .show()
        }

        viewModel.funcionario.observe(viewLifecycleOwner) { funcionario ->
            if (Login.userAdmin) {
                binding.edtNome.setText(funcionario.nome)
                binding.edtCargo.setText(funcionario.cargo)
                binding.edtCpf.setText(funcionario.cpf)
                binding.edtSenha.setText(funcionario.senha)

                binding.btnDeletar.visibility = View.VISIBLE
            } else {
                binding.edtNome.setText(funcionario.nome)
                binding.edtNome.isClickable = false
                binding.edtCargo.setText(funcionario.cargo)
                binding.edtCargo.isClickable = false
                binding.edtCpf.setText(funcionario.cpf)
                binding.edtCpf.isClickable = false
                binding.edtSenha.setText(funcionario.senha)
                binding.edtSenha.isClickable = false

                binding.btnCadastrar.visibility = View.GONE
            }
        }

        viewModel.erro.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Erro $it", Toast.LENGTH_LONG).show()
            Log.e("erro Emprestimo", it)
        }
    }
}