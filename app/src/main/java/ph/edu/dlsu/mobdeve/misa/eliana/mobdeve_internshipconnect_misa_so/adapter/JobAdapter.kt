package ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.model.Internship
import ph.edu.dlsu.mobdeve.misa.eliana.mobdeve_internshipconnect_misa_so.databinding.ItemJobListingBinding

class JobAdapter:RecyclerView.Adapter<JobAdapter.JobViewHolder> {

    private var jobArrayList = ArrayList<Internship>()
    private lateinit var context: Context

    public constructor(context: Context, jobArrayList:ArrayList<Internship>) {
        this.context = context
        this.jobArrayList = jobArrayList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): JobAdapter.JobViewHolder {
        val itemBinding = ItemJobListingBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.bindJob(jobArrayList[position])

    }

    override fun getItemCount(): Int {
        return jobArrayList.size
    }

    class JobViewHolder(private val itemBinding: ItemJobListingBinding):RecyclerView.ViewHolder(itemBinding.root){

        fun bindJob(internship: Internship) {
            itemBinding.tvPositionTitle.text = internship.title
            itemBinding.tvJobFunction.text = internship.function
            itemBinding.tvJobType.text = internship.type
        }
    }
}