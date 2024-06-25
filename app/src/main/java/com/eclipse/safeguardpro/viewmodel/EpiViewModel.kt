package com.eclipse.safeguardpro.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.eclipse.safeguardpro.service.model.Epi
import com.eclipse.safeguardpro.service.repository.EpiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EpiViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = EpiRepository(application)

    private val mEpiList = MutableLiveData<List<Epi>>()
    val epiList: LiveData<List<Epi>> = mEpiList

    private val mEpi = MutableLiveData<Epi>()
    val epi: LiveData<Epi> = mEpi

    private val mCreatedEpi = MutableLiveData<Epi>()
    val createdEpi: LiveData<Epi> = mCreatedEpi

    private val mDeletedEpi = MutableLiveData<Boolean>()
    val deleteEpi: LiveData<Boolean> = mDeletedEpi

    //erro
    private val mErro = MutableLiveData<String>()
    val erro: LiveData<String> = mErro

    fun loadEpis() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mEpiList.postValue(repository.getEpis())
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun insert(epi: Epi) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val createdEpi = repository.insertEpi(epi)
                mCreatedEpi.postValue(createdEpi)
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun update(epi: Epi) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val createdEpi = repository.updateEpi(epi.id, epi)
                mCreatedEpi.postValue(createdEpi)
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun getEpi(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        try {
            mEpi.postValue(repository.getEpi(id))
        } catch (e: Exception) {
            mErro.postValue(e.message)
        }
    }


    fun getEpiByCa(ca: Int) = viewModelScope.launch(Dispatchers.IO) {
        try {
            mEpi.postValue(repository.getEpiByCa(ca))
        } catch (e: Exception) {
            mErro.postValue(e.message)
        }
    }


    fun delete(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mDeletedEpi.postValue(repository.deleteEpi(id))
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }
}