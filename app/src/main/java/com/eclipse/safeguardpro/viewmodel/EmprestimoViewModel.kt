package com.eclipse.safeguardpro.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.eclipse.safeguardpro.service.model.Emprestimo
import com.eclipse.safeguardpro.service.model.Funcionario
import com.eclipse.safeguardpro.service.repository.EmprestimoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmprestimoViewModel (application: Application) : AndroidViewModel(application){

    //chama o repository
    private val repository = EmprestimoRepository(application)

    //lista de pessoas
    private val mEmprestimoList = MutableLiveData<List<Emprestimo>>()
    val emprestimoList: LiveData<List<Emprestimo>> = mEmprestimoList

    //chama um unico emprestimo
    private val mCreatedEmprestimo = MutableLiveData<Emprestimo>()
    val createdEmprestimo: LiveData<Emprestimo> = mCreatedEmprestimo

    private val mEmprestimo = MutableLiveData<Emprestimo>()
    val emprestimo: LiveData<Emprestimo> = mEmprestimo

    private val mDeletedEmprestimo = MutableLiveData<Boolean>()
    val deleteEmprestimo: LiveData<Boolean> = mDeletedEmprestimo

    //erro
    private val mErro = MutableLiveData<String>()
    val erro: LiveData<String> = mErro


    fun loadEmprestimos() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mEmprestimoList.postValue(repository.getEmprestimos())
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun insert(emprestimo: Emprestimo) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val createdEmprestimo = repository.insertEmprestimo(emprestimo)
                mCreatedEmprestimo.postValue(createdEmprestimo)
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun update(emprestimo: Emprestimo) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val createdEmprestimo = repository.updateEmprestimo(emprestimo.id, emprestimo)
                mCreatedEmprestimo.postValue(createdEmprestimo)
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun getEmprestimo(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mCreatedEmprestimo.postValue(repository.getEmprestimo(id))
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch(Dispatchers.IO){
            try {
                mDeletedEmprestimo.postValue(repository.deleteEmprestimo(id))
            }catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }
}