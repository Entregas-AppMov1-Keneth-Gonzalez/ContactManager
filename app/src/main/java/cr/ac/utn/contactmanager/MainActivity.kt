package cr.ac.utn.contactmanager

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainPantalla2)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnAddContact: Button = findViewById<Button>(R.id.btnMainAddContact)
        btnAddContact.setOnClickListener(View.OnClickListener { view ->
            val intentAddContact = Intent(this, AddContact::class.java)
            startActivity(intentAddContact)
        })

        val btnViewContactList: Button = findViewById<Button>(R.id.btnMainViewContactList)
        btnViewContactList.setOnClickListener(View.OnClickListener { view ->
            val intentViewContactList = Intent(this, ViewContactList::class.java)
            startActivity(intentViewContactList)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        return true
    }

}