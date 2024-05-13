package com.eclipse.safeguardpro.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eclipse.safeguardpro.databinding.ListItemFuncionarioBinding
import com.eclipse.safeguardpro.service.model.Funcionario

class FuncionarioAdapter(
    funcionario: List<Funcionario>?,
    private val clickListListener: (Funcionario) -> Unit
) :
    RecyclerView.Adapter<FuncionarioAdapter.FuncionarioViewHolder>() {

    //criar uma lista vazia de pessoas
    private var funcionarioList: List<Funcionario> = arrayListOf()

    class FuncionarioViewHolder(private val binding: ListItemFuncionarioBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //carrega as informações da pessoa na lista
        fun bind(funcionario: Funcionario, clickListListener: (Funcionario) -> Unit) {

            //configura o click de algum item da lista
            binding.root.setOnClickListener {
                clickListListener(funcionario)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FuncionarioViewHolder {
        //configura o binding da lista
        val listItemFunciorioBinding =
            ListItemFuncionarioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FuncionarioViewHolder(listItemFunciorioBinding)
    }

    override fun getItemCount(): Int {
        //retorna a quantidade de itens
        return funcionarioList.count()
    }

    override fun onBindViewHolder(holder: FuncionarioViewHolder, position: Int) {
        holder.bind(funcionarioList[position], clickListListener)
    }

    // carrega a lista de pessoas para ser exibidos
    fun updateFuncionario(list: List<Funcionario>) {
        funcionarioList = list
        notifyDataSetChanged()
    }


}
