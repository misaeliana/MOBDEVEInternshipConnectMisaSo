package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao

import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship

interface InternshipsDAO {
    fun addInternship(internship: Internship)
    fun getInternships(): ArrayList<Internship>
}

class InternshipsDAOArrayImpl: InternshipsDAO {
    private var arrayListInternships = ArrayList<Internship>()

    override fun addInternship(internship: Internship) {
        arrayListInternships.add(0, internship)
    }

    override fun getInternships(): ArrayList<Internship> {
        return arrayListInternships
    }

}