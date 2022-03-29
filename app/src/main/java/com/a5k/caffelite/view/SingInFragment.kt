package com.a5k.caffelite.view

import android.accounts.AccountManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.a5k.caffelite.data.AppState
import com.a5k.caffelite.data.model.AuthToken
import com.a5k.caffelite.data.model.User
import com.a5k.caffelite.databinding.FragmentSingInBinding
import com.a5k.caffelite.viewmodel.MainViewModel

class SingInFragment : Fragment() {

    private var vb: FragmentSingInBinding? = null
    private val viewModel: MainViewModel by lazy {
        MainViewModel(AccountManager.get(requireContext()), activity as IMainNavigation)
    }
    private var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = FragmentSingInBinding.inflate(inflater, container, false)
        return vb?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickListenerToRegistery()
        viewModel.getLiveData().observe(viewLifecycleOwner) { data -> render(data) }
        singInListener()

    }

    override fun onResume() {
        super.onResume()
        (activity as IMainNavigation).run {
            setTitleActionBar(NAME_TITLE)
            setHomeUp(false)
        }
    }

    private fun singInListener() {
        val login = vb?.login
        val pass = vb?.password
        vb?.btnSingIn?.setOnClickListener {
            if (login?.text.toString().isEmpty()) {
                login?.error = ERROR_FIELD
                return@setOnClickListener
            }
            if (login?.text.toString().isEmpty()) {
                login?.error = ERROR_FIELD
                return@setOnClickListener
            }
            user = User(login?.text.toString(), pass?.text.toString())
            viewModel.getToken(user!!)
        }
    }

    private fun render(data: AppState) {
        when (data) {
            is AppState.Success -> {
                if (user != null) {
                    val login = user!!.getlogin()
                    val token = (data.item as AuthToken).getToken()
                    viewModel.createUser(login, token)
                    user = null
                }
            }
            is AppState.Error -> {
                Toast.makeText(requireContext(), ERROR, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onClickListenerToRegistery() {
        vb?.toRegistration?.setOnClickListener {
            (activity as IMainNavigation).toSingOutFragment()
        }
    }

    companion object {
        private const val NAME_TITLE = "Авторизация"
        private const val ERROR_FIELD = "Введите данные"
        private const val ERROR = "Ошибка"
    }
}