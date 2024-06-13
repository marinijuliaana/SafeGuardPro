package com.eclipse.safeguardpro.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.eclipse.safeguardpro.service.model.Funcionario
import com.eclipse.safeguardpro.service.repository.FuncionarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FuncionarioViewModel(application: Application) : AndroidViewModel(application) {

    //chama o repository
    private val repository = FuncionarioRepository(application)

    //lista de pessoas
    private val mFuncionarioList = MutableLiveData<List<Funcionario>>()
    val funcionarioList: LiveData<List<Funcionario>> = mFuncionarioList

    private val mFuncionario = MutableLiveData<Funcionario>()
    val funcionario: LiveData<Funcionario> = mFuncionario

    //chama um unico funcionario
    private val mCreatedFuncionario = MutableLiveData<Funcionario>()
    val createdfuncionario: LiveData<Funcionario> = mCreatedFuncionario

    private val mDeletedFuncionario = MutableLiveData<Boolean>()
    val deleteFuncionario: LiveData<Boolean> = mDeletedFuncionario

    //erro
    private val mErro = MutableLiveData<String>()
    val erro: LiveData<String> = mErro

    fun loadFuncionarios() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mFuncionarioList.postValue(repository.getFuncionarios())
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun insert(funcionario: Funcionario) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val createdfuncionario = repository.insertFuncionario(funcionario)
                mCreatedFuncionario.postValue(createdfuncionario)
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun update(funcionario: Funcionario) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val createdFuncionario = repository.updateFuncionario(funcionario.id, funcionario)
                mCreatedFuncionario.postValue(createdFuncionario)
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun getFuncionario(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mFuncionario.postValue(repository.getFuncionario(id))
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun getFuncionarioByCpf(cpf: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mFuncionario.postValue(repository.getFuncionarioByCpf(cpf))
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mDeletedFuncionario.postValue(repository.deleteFuncionario(id))
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }
}