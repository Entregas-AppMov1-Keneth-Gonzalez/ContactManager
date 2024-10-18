package cr.ac.utn.contactmanager

import Util.util
import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
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
            util.openActivity(this, AddContact::class.java)
        })

        val btnViewContactList: Button = findViewById<Button>(R.id.btnMainViewContactList)
        btnViewContactList.setOnClickListener(View.OnClickListener { view ->
            util.openActivity(this, ViewContactList::class.java)
        })

        val btnMainDialog: Button = findViewById<Button>(R.id.btnMainDialog)
        btnMainDialog.setOnClickListener(View.OnClickListener {
            DisplayDialog()
        })

    }

    private fun DisplayDialog(){
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("Desea cerrar la aplicacion?").setCancelable(false).setPositiveButton("Si", DialogInterface.OnClickListener{
            dialog, id -> finish()
        })
            .setNegativeButton("No", DialogInterface.OnClickListener{
            dialog, id -> dialog.cancel()
            })

        val alert = dialogBuilder.create()
        alert.setTitle("Titulo del dialog")
        alert.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_AddContact -> {
                util.openActivity(this, AddContact::class.java)
                return true
            }
            R.id.menu_ContactList -> {
                util.openActivity(this, ViewContactList::class.java)
                return true
            } else -> super.onOptionsItemSelected(item)
        }

    }

}