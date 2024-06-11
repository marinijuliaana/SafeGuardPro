package com.eclipse.safeguardpro.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eclipse.safeguardpro.databinding.ListItemEmprestimoBinding
import com.eclipse.safeguardpro.databinding.ListItemFuncionarioBinding
import com.eclipse.safeguardpro.service.model.Emprestimo
import com.eclipse.safeguardpro.service.model.Funcionario

class EmprestimoAdapter (
    emprestimo: List<Emprestimo>?,
    private val clickListListener: (Emprestimo) -> Unit
):
    RecyclerView.Adapter<EmprestimoAdapter.EmprestimoViewHolder>() {

    //criar uma lista vazia de pessoas
    private var emprestimoList: List<Emprestimo> = arrayListOf()

    class EmprestimoViewHolder(private val binding: ListItemEmprestimoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //carrega as informações da pessoa na lista
        fun bind(emprestimo: Emprestimo, clickListListener: (Emprestimo) -> Unit) {

            //configura o click de algum item da lista
            binding.root.setOnClickListener {
                clickListListener(emprestimo)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EmprestimoAdapter.EmprestimoViewHolder {
        //configura o binding da lista
        val listItemEmprestimoBinding =
            ListItemEmprestimoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmprestimoAdapter.EmprestimoViewHolder(listItemEmprestimoBinding)
    }

    override fun getItemCount(): Int {
        //retorna a quantidade de itens
        return emprestimoList.count()
    }

    override fun onBindViewHolder(holder: EmprestimoAdapter.EmprestimoViewHolder, position: Int) {
        holder.bind(emprestimoList[position], clickListListener)
    }

    // carrega a lista de pessoas para ser exibidos
    fun updateEmprestimo(list: List<Emprestimo>) {
        emprestimoList = list
        notifyDataSetChanged()
    }

}
