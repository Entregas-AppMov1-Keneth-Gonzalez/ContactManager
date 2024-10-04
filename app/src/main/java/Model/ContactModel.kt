package Model

import Entities.Contact
import android.content.res.Resources
import cr.ac.utn.contactmanager.R

class ContactModel {
    companion object {
        private var contactList = mutableListOf<Contact>()

        fun addContact(contact: Contact) {
            contactList.add(contact)
        }

        fun getContacts() = contactList.toList()

        fun getContact(id: String) : Contact {
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

        fun getContactNames(): List<String> {
            val names = mutableListOf<String>()
            contactList.forEach{i-> names.add(i.fullName)}
            return names.toList()
        }

        fun remoContact(id: String) {
            try {
                contactList.remove(getContact(id))
            }catch (e: Exception){
                throw e
            }
        }
    }
}