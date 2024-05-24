package com.example.todolist
import android.content.Context
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class File {

    val filename = "listname.dat"

    fun writedata(item:ArrayList<String>, context:Context)
    {
        var fos : FileOutputStream = context.openFileOutput(filename, Context.MODE_PRIVATE )
        var oas = ObjectOutputStream(fos)
        oas.writeObject(item)
        oas.close()
    }

    fun readdata(context : Context):ArrayList<String>
    {
        var itemlist : ArrayList<String>
     try{
        var fis : FileInputStream = context.openFileInput(filename)
        var ois = ObjectInputStream(fis)
        itemlist = ois.readObject() as ArrayList<String>
       }
     catch (e:FileNotFoundException)
     {
         itemlist = ArrayList()
     }
        return itemlist
    }
}
