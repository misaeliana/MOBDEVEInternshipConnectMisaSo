package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ItemExperienceBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ItemExperienceUpdateBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Experience

class CompanyViewApplicantExperienceAdapter: RecyclerView.Adapter<CompanyViewApplicantExperienceAdapter.CompanyViewApplicantExperienceViewHolder> {
    private var applicantExperienceArrayList = ArrayList<Experience>()
    private lateinit var context: Context

    public constructor(context: Context, internUpdateExperienceArrayList: ArrayList<Experience>) {
        this.context = context
        this.applicantExperienceArrayList = internUpdateExperienceArrayList
    }

    override fun getItemCount(): Int {
        return applicantExperienceArrayList.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CompanyViewApplicantExperienceAdapter.CompanyViewApplicantExperienceViewHolder {
        val itemBinding = ItemExperienceBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent, false)
        return CompanyViewApplicantExperienceAdapter.CompanyViewApplicantExperienceViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CompanyViewApplicantExperienceAdapter.CompanyViewApplicantExperienceViewHolder,
                                  position: Int) {
        holder.bindExperience(applicantExperienceArrayList[position])
    }

    class CompanyViewApplicantExperienceViewHolder(private val itemBinding: ItemExperienceBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindExperience(experience: Experience){
            itemBinding.tvExperienceTitle.text = experience.title
            itemBinding.tvExperienceCompanyName.text = experience.companyName
            itemBinding.tvExperienceDuration.text = experience.startDate +" - " + experience.endDate
        }
    }
}