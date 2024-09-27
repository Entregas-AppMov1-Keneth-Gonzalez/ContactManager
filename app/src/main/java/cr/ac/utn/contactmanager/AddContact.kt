package cr.ac.utn.contactmanager

import Util.util
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddContact : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_contact)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnAddContacttoHome: Button = findViewById<Button>(R.id.btnAddContacttoHome)
        btnAddContacttoHome.setOnClickListener(View.OnClickListener { view ->
            val intentAddContacttoHome = Intent(this, MainActivity::class.java)
            startActivity(intentAddContacttoHome)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.crud_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_Save -> {

                return true
            }
            R.id.menu_Delete -> {

                return true
            }
            R.id.menu_Cancel -> {

                return true
            } else -> super.onOptionsItemSelected(item)
        }

    }
}