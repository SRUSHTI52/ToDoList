package com.example.todolist

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var text: EditText
    lateinit var button: Button
    lateinit var list: ListView
    lateinit var delete : Button

    var itemlist = ArrayList<String>()
    var file = File()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById(R.id.text1)
        button = findViewById(R.id.button)
        list = findViewById(R.id.list)
        delete = findViewById(R.id.delete)
        itemlist = file.readdata(this)

        try {
            itemlist = file.readdata(this)
        } catch (e: Exception) {
            Log.e("MainActivity", "Error reading data", e)
        }

        val arrayAdapter = TodoAdapter(this, itemlist, onEdit = { position ->
            showEditDialog(position)
        }, onDelete = { position ->
            showDeleteDialog(position)
        })

        list.adapter = arrayAdapter

        button.setOnClickListener {
            val itemname: String = text.text.toString()
            if (itemname.isNotEmpty()) {
                itemlist.add(itemname)
                text.setText("")
                file.writedata(itemlist, applicationContext)
                arrayAdapter.notifyDataSetChanged()
            }
        }

        delete.setOnClickListener {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Delete All")
            alert.setMessage("Do you want to delete all items from the list?")
            alert.setCancelable(false)
            alert.setNegativeButton("No") { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            alert.setPositiveButton("Yes") { _, _ ->
                itemlist.clear()
                arrayAdapter.notifyDataSetChanged()
                file.writedata(itemlist, applicationContext)
            }
            alert.create().show()
        }
    }

    private fun showEditDialog(position: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Edit Item")

        val input = EditText(this)
        input.setText(itemlist[position])
        builder.setView(input)

        builder.setPositiveButton("OK") { dialogInterface, _ ->
            val newText = input.text.toString()
            if (newText.isNotEmpty()) {
                itemlist[position] = newText
                file.writedata(itemlist, applicationContext)
                (list.adapter as ArrayAdapter<*>).notifyDataSetChanged()
            }
        }
        builder.setNegativeButton("Cancel") {  dialogInterface, _ ->
            dialogInterface.cancel()
        }
        builder.show()
    }

    private fun showDeleteDialog(position: Int) {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Delete")
        alert.setMessage("Do you want to delete this item from the list?")
        alert.setCancelable(false)
        alert.setNegativeButton("No") { dialogInterface, i ->
            dialogInterface.cancel()
        }
        alert.setPositiveButton("Yes") { dialogInterface, i ->
            itemlist.removeAt(position)
            (list.adapter as ArrayAdapter<*>).notifyDataSetChanged()
            file.writedata(itemlist, applicationContext)
        }
        alert.create().show()
    }
}
