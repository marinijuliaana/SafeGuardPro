package com.eclipse.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.eclipse.safeguardpro.R
import com.eclipse.safeguardpro.databinding.FragmentLoginBinding
import com.eclipse.safeguardpro.service.model.Login
import com.eclipse.safeguardpro.viewmodel.FuncionarioViewModel

class LoginFragment : Fragment() {

    private val viewModelFuncionario: FuncionarioViewModel by viewModels()

    //criar o binding
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var senha = ""
        var cpf = ""

        binding.btnlogar.setOnClickListener {
            cpf = binding.edtcpf.editableText.toString()
            senha = binding.edtsenha.editableText.toString()

            if ((cpf.isBlank() || cpf.isEmpty()) || (senha.isBlank() || senha.isEmpty())) {
                Toast.makeText(requireContext(), "Preencha os campos", Toast.LENGTH_LONG).show()
            } else {
                viewModelFuncionario.getFuncionarioByCpf(cpf.toInt())
            }
        }

        viewModelFuncionario.funcionario.observe(viewLifecycleOwner) {
            if (it.senha == senha && it.cpf == cpf.toInt()){
                Login.userConected(it.id, it.cpf, it.admin)
                Toast.makeText(requireContext(), "Login $it", Toast.LENGTH_LONG).show()
                Toast.makeText(requireContext(), "Login Obj ${Login.userId} - ${Login.userCpf} - ${Login.userAdmin}", Toast.LENGTH_LONG).show()

                findNavController().navigate(R.id.menuFragment)
            } else {
                Toast.makeText(requireContext(), "Usuario ou senha inválidos", Toast.LENGTH_LONG).show()
            }
        }
    }
}