package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao

import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Intern

interface InternsDAO {
    fun addIntern(intern: Intern)
    fun getInterns(): ArrayList<Intern>
}

class InternsDAOArrayImpl: InternsDAO {
    private var arrayListInterns = ArrayList<Intern>()

    override fun addIntern(intern: Intern) {
        arrayListInterns.add(0, intern)
    }

    override fun getInterns(): ArrayList<Intern> {
        return arrayListInterns
    }


}