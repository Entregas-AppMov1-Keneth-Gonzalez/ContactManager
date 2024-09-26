package cr.ac.utn.contactmanager

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainPantalla2)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnMensaje: Button = findViewById<Button>(R.id.btnMainMensaje)
        btnMensaje.setOnClickListener(View.OnClickListener { view ->
            Toast.makeText(this, "Mensaje desplegado".toString(), Toast.LENGTH_LONG).show()
        })

        val btnIrPantalla2: Button = findViewById<Button>(R.id.btnPantalla2)
        btnIrPantalla2.setOnClickListener(View.OnClickListener { view ->
            val intentIrPantalla2 = Intent(this, MainActivity2::class.java)
            startActivity(intentIrPantalla2)
        })

    }
}