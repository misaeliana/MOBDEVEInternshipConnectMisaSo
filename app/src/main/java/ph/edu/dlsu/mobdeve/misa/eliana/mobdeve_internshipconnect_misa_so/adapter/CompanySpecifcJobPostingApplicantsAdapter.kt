package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ItemApplicantBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ItemCompanyInternshipBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Intern

class CompanySpecifcJobPostingApplicantsAdapter: RecyclerView.Adapter<CompanySpecifcJobPostingApplicantsAdapter.CompanySpecifcJobPostingApplicantViewHolder> {
    private var applicantsArrayList = ArrayList<Intern>()
    private lateinit var context: Context

    public constructor(context: Context, applicantsArrayList:ArrayList<Intern>) {
        this.context = context
        this.applicantsArrayList = applicantsArrayList
    }

    override fun getItemCount(): Int {
        return applicantsArrayList.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CompanySpecifcJobPostingApplicantsAdapter.CompanySpecifcJobPostingApplicantViewHolder {
        val itemBinding = ItemApplicantBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent, false)
        return CompanySpecifcJobPostingApplicantsAdapter.CompanySpecifcJobPostingApplicantViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CompanySpecifcJobPostingApplicantsAdapter.CompanySpecifcJobPostingApplicantViewHolder,
                                  position: Int) {
        holder.bindApplicant(applicantsArrayList[position])
    }

    class CompanySpecifcJobPostingApplicantViewHolder(private val itemBinding: ItemApplicantBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindApplicant(intern: Intern){
            itemBinding.tvName.text = intern.name
            itemBinding.tvSchool.text = "School"
        }
    }
}