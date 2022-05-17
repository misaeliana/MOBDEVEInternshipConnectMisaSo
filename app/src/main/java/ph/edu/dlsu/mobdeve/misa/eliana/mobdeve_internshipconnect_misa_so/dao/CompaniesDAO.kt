package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao

import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Company

interface CompaniesDAO {
    fun addCompany(company: Company)
    fun getCompanies(): ArrayList<Company>
}

class CompaniesDAOArrayImpl: CompaniesDAO {
    private var arrayListCompanies = ArrayList<Company>()
    override fun addCompany(company: Company) {
        arrayListCompanies.add(0, company)
    }

    override fun getCompanies(): ArrayList<Company> {
        return arrayListCompanies
    }
}
