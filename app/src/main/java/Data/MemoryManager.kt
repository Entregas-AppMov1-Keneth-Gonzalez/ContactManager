package Data

import Entities.Contact
import Interfaces.IDBManager
import android.content.res.Resources
import cr.ac.utn.contactmanager.R

object MemoryManager: IDBManager {
    private var contactList= mutableListOf<Contact>()

    override fun add(contact: Contact) {
        contactList.add(contact)
    }

    override fun update(contact: Contact) {
        remove(contact.id)
        contactList.add(contact)
    }

    override fun remove(id: String) {
        contactList.removeIf {it.id.trim() == id.trim()}
    }

    override fun getAll(): List<Contact> = contactList.toList()

    override fun getById(id: String): Contact? {
        try{
            var result = contactList.filter { (it.id == id) }
            if (!result.any()){
                throw Exception(Resources.getSystem().getString(R.string.msgContactNotFound))
            }else{
                return result[0]
            }
        }catch (e: Exception){
            throw e
        }
    }

    override fun getByFullName(fullName: String): Contact? {
        try{
            var result = contactList.filter { (it.fullName == fullName) }
            if (!result.any()){
                throw Exception(Resources.getSystem().getString(R.string.msgContactNotFound))
            }else{
                return result[0]
            }
        }catch (e: Exception){
            throw e
        }
    }

}