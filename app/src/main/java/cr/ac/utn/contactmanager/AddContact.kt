package cr.ac.utn.contactmanager

import Entities.Contact
import Model.ContactModel
import Util.EXTRA_MESSAGE_CONTACT_ID
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddContact : AppCompatActivity() {

    private lateinit var txtId: EditText
    private lateinit var txtName: EditText
    private lateinit var txtLastName: EditText
    private lateinit var txtPhone: EditText
    private lateinit var txtEmail: EditText
    private lateinit var txtAddress: EditText
    private lateinit var contactModel: ContactModel
    private var isEditionMode: Boolean = false
    private lateinit var menuitemDelete: MenuItem

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

        contactModel = ContactModel(this)
        txtId = findViewById<EditText>(R.id.txtContact_ID)
        txtName = findViewById<EditText>(R.id.txtContact_Name)
        txtLastName = findViewById<EditText>(R.id.txtContact_LastName)
        txtPhone = findViewById<EditText>(R.id.txtContact_Phone)
        txtEmail = findViewById<EditText>(R.id.txtContact_Email)
        txtAddress = findViewById<EditText>(R.id.txtContact_Address)

        val contactInfo = intent.getStringExtra(EXTRA_MESSAGE_CONTACT_ID)
        if (contactInfo != null && contactInfo != "") loadContact(contactInfo.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.crud_menu, menu)

        menuitemDelete = menu!!.findItem(R.id.menu_Delete)
        if(isEditionMode){
            //menu?.findItem(R.id.menu_Delete)?.isVisible = true
            menuitemDelete.isVisible = true
        }else{
            //menu?.findItem(R.id.menu_Delete)?.isVisible = false
            menuitemDelete.isVisible = false
        }
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_Save -> {
                saveContact()
                return true
            }
            R.id.menu_Delete -> {
            deleteContact()
                return true
            }
            R.id.menu_Cancel -> {
            cleanScreen()
                return true
            } else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveContact(){
        try{
            val contact = Contact()
            contact.id = txtId.text.toString()
            contact.name = txtName.text.toString()
            contact.lastName = txtLastName.text.toString()
            contact.phone = txtPhone.text.toString()?.toInt()!!
            contact.email = txtEmail.text.toString()
            contact.address = txtAddress.text.toString()

            if(dataValidation(contact)){
                if(!isEditionMode){
                    contactModel.addContact(contact)
                    val intentAddContacttoHome = Intent(this, MainActivity::class.java)
                    startActivity(intentAddContacttoHome)
                }else{
                    contactModel.updateContact(contact)
                    cleanScreen()
                    Toast.makeText(this, "The information was saved succesfully", Toast.LENGTH_LONG).show()
                    val intentAddContacttoHome = Intent(this, MainActivity::class.java)
                    startActivity(intentAddContacttoHome)
                }
            }else{
                Toast.makeText(this, "Missing data, please check all required information", Toast.LENGTH_LONG).show()
            }
        }catch (e: Exception){
            Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun dataValidation(contact: Contact): Boolean{
        return contact.id.isNotEmpty() && contact.name.isNotEmpty() && contact.lastName.isNotEmpty() &&
                (contact.phone != null && contact.phone > 0) && contact.email.isNotEmpty() && contact.address.isNotEmpty()
    }

    private fun deleteContact(){
        //try{

        //}catch (e: Exception){
            //Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
        //}
    }

    private fun cleanScreen(){
        txtId.setText("")
        txtName.setText("")
        txtLastName.setText("")
        txtPhone.setText("")
        txtEmail.setText("")
        txtAddress.setText("")
        invalidateOptionsMenu()
    }

    private fun loadContact(contactInfo: String){
        try{
            val contact = contactModel.getContactByFullName(contactInfo)
            txtId.setText(contact.id)
            txtName.setText(contact.name)
            txtLastName.setText(contact.lastName)
            txtPhone.setText(contact.phone.toString())
            txtEmail.setText(contact.email)
            txtAddress.setText(contact.address)
            isEditionMode = true
            txtId.isEnabled = false
        }catch (e: Exception){
            Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
        }
    }
}