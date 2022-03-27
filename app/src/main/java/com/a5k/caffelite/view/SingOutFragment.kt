package com.a5k.caffelite.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.a5k.caffelite.databinding.FragmentSingOutBinding

class SingOutFragment : Fragment() {

    private var vb:FragmentSingOutBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = FragmentSingOutBinding.inflate(inflater,container, false)
        return vb?.root
    }
    override fun onResume() {
        super.onResume()
        (activity as IMainNavigation).run {
            setTitleActionBar("Регистрация")
            setHomeUp(true)
        }
    }
}
