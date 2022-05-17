package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.dao

import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Experience

interface ExperiencesDAO {
    fun addExperience(experience: Experience)
    fun getExperiences(): ArrayList<Experience>
}

class ExperiencesDAOArrayImpl: ExperiencesDAO {
    private var arrayListExperiences = ArrayList<Experience>()

    override fun addExperience(experience: Experience) {
        arrayListExperiences.add(0, experience)
    }

    override fun getExperiences(): ArrayList<Experience> {
        return arrayListExperiences
    }
}