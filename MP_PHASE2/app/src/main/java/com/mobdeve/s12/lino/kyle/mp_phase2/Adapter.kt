package com.mobdeve.s12.lino.kyle.mp_phase2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mobdeve.s12.lino.kyle.mp_phase2.databinding.ItemRowSaveBinding
import com.mobdeve.s12.lino.kyle.mp_phase2.model.Item


class Adapter (private val context: Context, private var userList: ArrayList<Item?>?, val onClickDelete: (Int) -> Unit) : RecyclerView.Adapter<Adapter.ViewHolder>(){

    inner class ViewHolder(private val itemBinding: ItemRowSaveBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bindUser(user: Item, position: Int) {
            itemBinding.textgamesave.text = user.textname
            var buttondelete = itemBinding.buttondelete
            var buttonview = itemBinding.buttonview
            var buttonedit = itemBinding.buttonedit


            //var viewInfo:String = "name: " + user.textname + "price: " + user.textprice + "Description: " + user.textdescription
            buttonview.setOnClickListener{
                val gotoViewItemActivity = Intent(context, ViewItemActivity::class.java)
                gotoViewItemActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

                val bundle = Bundle()
                bundle.putString("name", user.textname)
                bundle.putString("price", user.textprice)
                bundle.putString("description", user.textdescription)

                gotoViewItemActivity.putExtras(bundle)

                context.startActivity(gotoViewItemActivity)
            }

            buttonedit.setOnClickListener{
                val gotoViewItemActivity = Intent(context, ViewItemActivity::class.java)
                gotoViewItemActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

                val bundle = Bundle()
                bundle.putString("name", user.textname)
                bundle.putString("price", user.textprice)
                bundle.putString("description", user.textdescription)

                gotoViewItemActivity.putExtras(bundle)

                context.startActivity(gotoViewItemActivity)
            }

            buttondelete.setOnClickListener{ onClickDelete(position) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemRowSaveBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }
    override fun getItemCount() = userList!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindUser(userList!![position]!!, position)
    }

    fun deleteFunc(position: Int) {
        userList!!.removeAt(position)
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }

    private var list = userList!!

    fun setItem(items: List<Item?>) {
        list = items as ArrayList<Item?>
        notifyDataSetChanged()
    }
}

