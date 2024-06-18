package com.bagibagi.app.data.repo

import com.bagibagi.app.data.api.ApiService
import com.bagibagi.app.data.model.OrganizationModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OrgRepository private constructor(private val  apiService : ApiService) {
    fun getAllOrg() : Flow<List<OrganizationModel>> = flow{
        val response = apiService.getAllOrganizations()
        val listOrganizationItem = response.map {
            OrganizationModel(
                it.id,
                it.nama,
                it.desc,
                it.kontak,
                it.alamat,
                it.linkFoto
            )
        }
        emit(listOrganizationItem)
    }
    companion object {
        fun getInstance(apiService: ApiService): OrgRepository =
            OrgRepository(apiService)
    }
}