package com.lateef.apitest

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        downkey.setOnClickListener {
            doGetParameters()
        }

        paybtn.setOnClickListener {
            val amt = editamt.text.toString()
            var amt_amt: Int = amt.toInt()
            doCardTransaction(amt_amt)

        }

        printbtn.setOnClickListener {
            val amt = editamt.text.toString()
           // var amt_amt: Int = amt.toInt()
            print(amt)
        }


    }


    fun doGetParameters() {

        val KEY_EXCHANGE_REQUEST = 10
        val jsonString = "{  \"action\":\"PARAMETER\"  }"
        var myintent= Intent("com.globalaccelerex.utility")

        myintent.putExtra("requestData", jsonString)

        startActivityForResult(myintent, KEY_EXCHANGE_REQUEST)
    }


    fun doCardTransaction(amount: Int) {
        var PURCHASE_INTENT=12
        val jsonString =
            "{ \"transType\": \"PURCHASE\", \"amount\":\"$amount\", \"print\":\"true\" }"
        val intent = Intent("com.globalaccelerex.transaction")
        intent.putExtra("requestData", jsonString)
        startActivityForResult(intent, PURCHASE_INTENT)
    }

    fun print(jsonString: String) {
        val PRINT_REQUEST = 15
        val intent = Intent("com.globalaccelerex.printer")
        intent.putExtra("jsonData", jsonString)
        startActivityForResult(intent, PRINT_REQUEST)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //   Log.d("Status","4")

        if (requestCode==10) { //Parameter Response
            val treeMap = TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER)
            val returnString = data!!.getStringExtra("data")
            Log.d("result", returnString.toString())



            Log.d("para", returnString.toString())
        }
        if (requestCode==12)// Transaction
        {
            val returnString = data!!.getStringExtra("data")
            Log.d("trans_response", returnString.toString())


        }

        if (requestCode==15)// Print
        {
            val returnString = data!!.getStringExtra("data")
            Log.d("print_response", returnString.toString())


        }
    }


}