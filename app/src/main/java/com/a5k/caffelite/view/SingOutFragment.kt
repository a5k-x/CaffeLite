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
import com.a5k.caffelite.databinding.FragmentSingOutBinding
import com.a5k.caffelite.viewmodel.MainViewModel

class SingOutFragment : Fragment() {

    private var vb: FragmentSingOutBinding? = null
    private val viewModel: MainViewModel by lazy {
        MainViewModel(AccountManager.get(requireContext()), activity as IMainNavigation)
    }
    private var user: User? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = FragmentSingOutBinding.inflate(inflater, container, false)
        return vb?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner) { data -> render(data) }
        singInListener()
    }

    override fun onResume() {
        super.onResume()
        (activity as IMainNavigation).run {
            setTitleActionBar(NAME_TITLE)
            setHomeUp(true)
        }
    }

    private fun render(data: AppState) {
        when (data) {
            is AppState.Success -> {
                (activity as IMainNavigation).run {
                    if (user != null) {
                        val login = user!!.getlogin()
                        val token = (data.item as AuthToken).getToken()
                        viewModel.createUser(login, token)
                        user = null
                    }
                }
            }
            is AppState.Error -> {
                Toast.makeText(requireContext(), ERROR, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun singInListener() {
        val login = vb?.newLogin
        val pass = vb?.newPassword
        val repeatPass = vb?.repeatPassword
        vb?.btnSingOut?.setOnClickListener {
            if (login?.text.toString().isEmpty()) {
                login?.error = ERROR_FIELD
                return@setOnClickListener
            }
            if (pass?.text.toString().isEmpty()) {
                pass?.error = ERROR_FIELD
                return@setOnClickListener
            }
            if (repeatPass?.text.toString().isEmpty()) {
                repeatPass?.error = ERROR_FIELD
                return@setOnClickListener
            }
            user = User(login?.text.toString(), pass?.text.toString())
            if (user != null) {
                viewModel.registeryUser(user!!)
            }

        }

    }

    companion object {
        private const val NAME_TITLE = "Регистрация"
        private const val ERROR_FIELD = "Не верные данные"
        private const val ERROR = "Ошибка"
    }

}
