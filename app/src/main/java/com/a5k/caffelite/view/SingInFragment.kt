package com.a5k.caffelite.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.a5k.caffelite.R
import com.a5k.caffelite.databinding.FragmentSingInBinding

class SingInFragment : Fragment() {

private var vb:FragmentSingInBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       vb = FragmentSingInBinding.inflate(inflater,container,false)
        return vb?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickListenerToRegistery()
    }

    override fun onResume() {
        super.onResume()
        (activity as IMainNavigation).run {
            setTitleActionBar("Авторизация")
                setHomeUp(false)
        }
    }

    private fun onClickListenerToRegistery() {
        vb?.toRegistration?.setOnClickListener {
            (activity as IMainNavigation).toSingOutFragment()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =SingInFragment()
    }
}