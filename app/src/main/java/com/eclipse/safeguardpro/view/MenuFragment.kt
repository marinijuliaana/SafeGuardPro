package com.eclipse.safeguardpro.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.eclipse.safeguardpro.R
import com.eclipse.safeguardpro.databinding.ActivityMainBinding
import com.eclipse.safeguardpro.databinding.FragmentCadastroEpiBinding
import com.eclipse.safeguardpro.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    //criar o binding
    private var _binding: FragmentMenuBinding? = null
    private val binding: FragmentMenuBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCadastrarEpi.setOnClickListener {
            findNavController().navigate(R.id.cadastroEpiFragment)
        }

        binding.btnCadastrarFuncionario.setOnClickListener {
            findNavController().navigate(R.id.cadastroFragment)
        }

        binding.btnRelatorioEpi.setOnClickListener {
            findNavController().navigate(R.id.relatorioEpiFragment)
        }

        binding.btnRelatorioFuncionario.setOnClickListener {
            findNavController().navigate(R.id.listaFuncionarioFragment)
        }

        binding.btnEntrega.setOnClickListener {
            findNavController().navigate(R.id.listaFuncionarioFragment)
        }
    }
}