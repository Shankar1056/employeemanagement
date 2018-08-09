package comcater.employeemanagement.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import comcater.employeemanagement.R
import comcater.employeemanagement.model.AssignWorkDataModel

class AssignTaskAdapter(private val context: Context, private val myCartModels: ArrayList<AssignWorkDataModel>) : RecyclerView.Adapter<AssignTaskAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var taskIdTV: TextView
        var dateValueTV: TextView
        var assignTaskTV: TextView
        var completionDateTV: TextView

        init {
            taskIdTV = view.findViewById<View>(R.id.taskIdTV) as TextView
            dateValueTV = view.findViewById<View>(R.id.dateValueTV) as TextView
            assignTaskTV = view.findViewById<View>(R.id.assignTaskTV) as TextView
            completionDateTV = view.findViewById<View>(R.id.completionDateTV) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.tasklist_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val sa = myCartModels!![position]

        holder.taskIdTV.text = sa.id
        holder.dateValueTV.text = sa.assign_date
        holder.assignTaskTV.text = sa.target_value
        holder.completionDateTV.text = sa.target_period
    }

    override fun getItemCount(): Int {
        return myCartModels!!.size
    }

}
