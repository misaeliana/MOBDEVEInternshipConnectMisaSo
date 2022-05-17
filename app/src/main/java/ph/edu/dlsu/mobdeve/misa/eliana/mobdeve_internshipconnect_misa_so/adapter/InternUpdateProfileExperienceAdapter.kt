package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ItemExperienceUpdateBinding
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Experience

class InternUpdateProfileExperienceAdapter: RecyclerView.Adapter<InternUpdateProfileExperienceAdapter.InternUpdateExperienceViewHolder> {
    private var internUpdateExperienceArrayList = ArrayList<Experience>()
    private lateinit var context: Context

    public constructor(context: Context, internUpdateExperienceArrayList: ArrayList<Experience>) {
        this.context = context
        this.internUpdateExperienceArrayList = internUpdateExperienceArrayList
    }

    override fun getItemCount(): Int {
        return internUpdateExperienceArrayList.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InternUpdateProfileExperienceAdapter.InternUpdateExperienceViewHolder {
        val itemBinding = ItemExperienceUpdateBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent, false)
        return InternUpdateProfileExperienceAdapter.InternUpdateExperienceViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: InternUpdateProfileExperienceAdapter.InternUpdateExperienceViewHolder,
                                  position: Int) {
        holder.bindExperience(internUpdateExperienceArrayList[position])
    }

    class InternUpdateExperienceViewHolder(private val itemBinding: ItemExperienceUpdateBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindExperience(experience: Experience){
            itemBinding.tvExperienceTitle.text = experience.title
            itemBinding.tvExperienceCompanyName.text = experience.companyName
            itemBinding.tvExperienceDuration.text = experience.startDate +" - " + experience.endDate
        }
    }
}