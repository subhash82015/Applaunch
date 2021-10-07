package com.example.demo.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "UserDatabase"
        private val TABLE_CONTACTS = "UserTable"
        private val KEY_ID = "id"
        private val KEY_FNAME = "fname"
        private val KEY_LNAME = "lname"
        private val KEY_EMAIL = "email"
        private val KEY_PASSWORD = "password"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //creating table with fields
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FNAME + " TEXT,"+ KEY_LNAME + " TEXT,"+ KEY_PASSWORD + " TEXT,"
                + KEY_EMAIL + " TEXT" + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)
        onCreate(db)
    }
    //method to insert data
    fun addUser(user: UserModelClass):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
       // contentValues.put(KEY_ID, user.userId)
        contentValues.put(KEY_FNAME, user.fname)
        contentValues.put(KEY_LNAME, user.lname)
        contentValues.put(KEY_EMAIL,user.userEmail )
        contentValues.put(KEY_PASSWORD,user.password )
        // Inserting Row
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    //method to read data
    @SuppressLint("Range")
    fun viewUser():List<UserModelClass>{
        val empList:ArrayList<UserModelClass> = ArrayList<UserModelClass>()
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var userid: Int
        var fname: String
        var lname: String
        var password: String
        var userEmail: String
        if (cursor.moveToFirst()) {
            do {
                userid = cursor.getInt(cursor.getColumnIndex("id"))
                fname = cursor.getString(cursor.getColumnIndex("fname"))
                lname = cursor.getString(cursor.getColumnIndex("lname"))
                password = cursor.getString(cursor.getColumnIndex("password"))
                userEmail = cursor.getString(cursor.getColumnIndex("email"))
                val emp= UserModelClass(userId = userid, fname = fname, lname = lname, password = password, userEmail = userEmail)
                empList.add(emp)
            } while (cursor.moveToNext())
        }
        return empList
    }

    fun checkUser(email: String, password: String): Boolean {
        val columns = arrayOf(KEY_ID)
        val db = this.readableDatabase
        val selection = "$KEY_EMAIL = ? AND $KEY_PASSWORD = ?"
        val selectionArgs = arrayOf(email, password)
        val cursor = db.query(TABLE_CONTACTS, //Table to query
            columns, //columns to return
            selection, //columns for the WHERE clause
            selectionArgs, //The values for the WHERE clause
            null,  //group the rows
            null, //filter by row groups
            null) //The sort order
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        if (cursorCount > 0)
            return true
        return false
    }
    //method to delete data
    fun deleteUser(user: Int):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, user) // EmpModelClass UserId
        // Deleting Row
        val success = db.delete(TABLE_CONTACTS,"id="+user,null)
        db.close() // Closing database connection
        return success
    }
}