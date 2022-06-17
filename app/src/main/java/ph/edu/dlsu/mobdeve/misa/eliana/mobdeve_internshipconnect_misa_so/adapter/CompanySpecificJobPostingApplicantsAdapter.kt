package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.company.CompanyViewApplicantInformation
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ItemApplicantBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Intern

class CompanySpecificJobPostingApplicantsAdapter: RecyclerView.Adapter<CompanySpecificJobPostingApplicantsAdapter.CompanySpecificJobPostingApplicantViewHolder> {
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
    ): CompanySpecificJobPostingApplicantViewHolder {
        val itemBinding = ItemApplicantBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent, false)
        return CompanySpecificJobPostingApplicantViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CompanySpecificJobPostingApplicantViewHolder,
                                  position: Int) {
        holder.bindApplicant(applicantsArrayList[position])
    }

    inner class CompanySpecificJobPostingApplicantViewHolder(private val itemBinding: ItemApplicantBinding) : RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

        var intern = Intern()

        init {
            itemView.setOnClickListener(this)
        }

        fun bindApplicant(intern: Intern){
            println(intern.name)
            this.intern = intern
            itemBinding.tvName.text = intern.name
            itemBinding.tvSchool.text = intern.school
        }

        override fun onClick(p0: View?) {
            var goToApplicantInformation = Intent(context, CompanyViewApplicantInformation::class.java)
            var bundle = Bundle()

            bundle.putString("name", intern.name)
            bundle.putString("email", intern.email)
            bundle.putString("number", intern.number.toString())
            bundle.putString("about", intern.about)
            bundle.putString("school", intern.school)
            bundle.putString("course", intern.course)
            bundle.putString("gradYear", intern.gradYear.toString())

            goToApplicantInformation.putExtras(bundle)
            goToApplicantInformation.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(goToApplicantInformation)
        }
    }
}