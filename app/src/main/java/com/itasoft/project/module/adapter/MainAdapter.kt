package com.itasoft.project.module.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itasoft.project.Utils.formatDate
import com.itasoft.project.Utils.getTimeAgo
import com.itasoft.project.Utils.isDateExpired
import com.itasoft.project.data.model.StokBarangItem
import com.itasofttest.project.R
import kotlinx.android.extensions.LayoutContainer
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainAdapter (private var items:MutableList<StokBarangItem>, private val context: Context) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vh = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_stok, parent, false))

        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view=holder.itemView
        val data=items[position]

        view.let {
            it.visibility = View.VISIBLE

            (it.findViewById(R.id.tv_title) as TextView).text = data.cNamaBarang
            (it.findViewById(R.id.tv_expired) as TextView).text = data.cExpired.orEmpty().formatDate("yyyy-MM-dd","dd/MM/yyyy")

            if(data.cExpired.orEmpty().isDateExpired()){
                (it.findViewById(R.id.tv_expired) as TextView).setTextColor(context.resources.getColor(R.color.red))
            }else{
                (it.findViewById(R.id.tv_expired) as TextView).setTextColor(context.resources.getColor(R.color.black))
            }

        }

//        view.setOnClickListener {
//            val intent = Intent(context,DetailActivity::class.java)
//            intent.putExtra("stok_id",data.id)
//            context.startActivity(intent)
//        }

    }

    fun addItems(list:MutableList<StokBarangItem>){
        items.addAll(list)
        notifyDataSetChanged()
    }



    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {


    }
}