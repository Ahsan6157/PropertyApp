package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.propertyapp.databinding.ActivityAllUsersBinding
import com.example.propertyapp.databinding.UsersListViewItemsBinding
import models.Person

open class ListViewAdapter(private val context: Context,private val list:List<Person>): BaseAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: UsersListViewItemsBinding
        val view: View

        if (convertView == null) {
            // Inflate the layout and create a binding object
             binding = UsersListViewItemsBinding.inflate(
                LayoutInflater.from(parent!!.context),
                parent,
                false
            )
            view = binding.root
            view.tag = binding
        } else {
            // Reuse the existing binding
            binding = convertView.tag as UsersListViewItemsBinding
            view = convertView
        }

        // Bind data to the layout
        binding.tvUserName.text = list[position].name
        binding.tvUserEmail.text = list[position].email

        return view
    }
}