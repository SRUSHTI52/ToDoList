package com.example.todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class TodoAdapter(context: Context, private val items: List<String>, private val onEdit: (Int) -> Unit, private val onDelete: (Int) -> Unit) : ArrayAdapter<String>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false)

        val item = getItem(position)

        val itemText = view.findViewById<TextView>(R.id.text)
        val itemCheckbox = view.findViewById<CheckBox>(R.id.checkbox)
        val editButton = view.findViewById<ImageView>(R.id.edit)
        val deleteButton = view.findViewById<ImageView>(R.id.del)

        itemText.text = item

        itemCheckbox.setOnCheckedChangeListener { _, isChecked ->
        }

        editButton.setOnClickListener {
            onEdit(position)
        }

        deleteButton.setOnClickListener {
            onDelete(position)
        }

        return view
    }
}
