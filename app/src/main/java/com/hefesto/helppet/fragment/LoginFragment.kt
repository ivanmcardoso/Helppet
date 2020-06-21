package com.hefesto.helppet.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hefesto.helppet.R
import com.hefesto.helppet.activity.MainActivity
import com.hefesto.helppet.config.FirebaseConfig
import com.hefesto.helppet.utils.isFilled
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_login, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btLoginSubmit.setOnClickListener {
            if (tiLoginUserName.text.isFilled && tiLoginPassword.text.isFilled){
                pbLogin.visibility = View.VISIBLE
                FirebaseConfig.getFireBaseAuth().signInWithEmailAndPassword(
                    tiLoginUserName.text.toString(),
                    tiLoginPassword.text.toString()).apply {
                    addOnSuccessListener {
                        pbLogin.visibility = View.GONE
                        startActivity(Intent(context, MainActivity::class.java))
                        Toast.makeText(context, "Sucesso", Toast.LENGTH_SHORT).show()
                    }
                    addOnFailureListener {
                        pbLogin.visibility = View.GONE
                        Toast.makeText(context, "Falha", Toast.LENGTH_SHORT).show() }
                }
            }
        }
        tvNewUser.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.flLogin, UserFormFragment())?.commit()
        }
    }

}