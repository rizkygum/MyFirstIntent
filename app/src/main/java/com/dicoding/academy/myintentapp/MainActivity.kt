package com.dicoding.academy.myintentapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvResult: TextView

    companion object{
        private const val REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveActivity: Button = findViewById(R.id.btn_move_activity)
        btnMoveActivity.setOnClickListener(this)

        val btnMoveActivityWithData: Button = findViewById(R.id.btn_move_activity_data)
        btnMoveActivityWithData.setOnClickListener(this)

        val btnMoveWithObject: Button = findViewById(R.id.btn_move_activity_object)
        btnMoveWithObject.setOnClickListener(this)

        val btnDialNumber: Button = findViewById(R.id.btn_dial_number)
        btnDialNumber.setOnClickListener(this)

        val btnMoveForResult:Button = findViewById(R.id.btn_move_for_result)
        btnMoveForResult.setOnClickListener(this)
        tvResult = findViewById(R.id.tv_result);
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_move_activity -> {
                val moveIntent = Intent(this, MoveActivity::class.java)
                startActivity(moveIntent)
            }

            R.id.btn_move_activity_data ->{
                val moveWithDataIntent = Intent(this, MoveWithDataActivity::class.java)
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Dicoding Academy Cuy")
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 28)
                startActivity(moveWithDataIntent)
            }

            R.id.btn_dial_number -> {
                val phoneNumber = "089626728545"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }

            R.id.btn_move_activity_object -> {
                val person = Person(
                    "DicodingAcademy",
                    5,
                    "academy@dicoding.com",
                    "Bandung"
                )

                //sent kumpulan object parcelable
                //var persons = ArrayList<Person>()


                val moveDataWithObjectIntent = Intent(this@MainActivity, MoveWithObjectActivity::class.java)
                moveDataWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person)
                //moveDataWithObjectIntent.putParcelableArrayListExtra(MoveWithObjectActivity.EXTRA_PERSON, persons)
                startActivity(moveDataWithObjectIntent)
            }

            R.id.btn_move_for_result -> {
                val moveForResultIntent = Intent(this@MainActivity, MoveForResultActivity::class.java)
                startActivityForResult(moveForResultIntent, REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE){
            val selectedValue = data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0)
            tvResult.text = "Hasil : $selectedValue"
        }
    }
}