package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao

import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship2

interface InternshipsDAO {
    fun addInternship(internship: Internship2)
    fun getInternships(): ArrayList<Internship2>
}

class InternshipsDAOArrayImpl: InternshipsDAO {
    private var arrayListInternships = ArrayList<Internship2>()

    override fun addInternship(internship: Internship2) {
        arrayListInternships.add(0, internship)
    }

    override fun getInternships(): ArrayList<Internship2> {
        return arrayListInternships
    }

}