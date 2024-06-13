package com.eclipse.safeguardpro.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eclipse.safeguardpro.databinding.ListItemEpiBinding
import com.eclipse.safeguardpro.service.model.Epi

class EpiAdapter(
    epis: List<Epi>?,
    private val clickListListener: (Epi) -> Unit
) : RecyclerView.Adapter<EpiAdapter.EpiViewHolder>() {

    //criar uma lista vazia de pessoas
    private var epiList: List<Epi> = arrayListOf()

    class EpiViewHolder(private val binding: ListItemEpiBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //carrega as informações da pessoa na lista
        fun bind(epi: Epi, clickListListener: (Epi) -> Unit) {

            //configura o click de algum item da lista
            binding.root.setOnClickListener {
                clickListListener(epi)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EpiViewHolder {
        //configura o binding da lista
        val listRelatorioEpiBinding =
            ListItemEpiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpiViewHolder(listRelatorioEpiBinding)
    }

    override fun getItemCount(): Int {
        //retorna a quantidade de itens
        return epiList.count()
    }

    override fun onBindViewHolder(holder: EpiViewHolder, position: Int) {
        holder.bind(epiList[position], clickListListener)
    }

    // carrega a lista de pessoas para ser exibidos
    fun updateEpi(list: List<Epi>) {
        epiList = list
        notifyDataSetChanged()
    }
}