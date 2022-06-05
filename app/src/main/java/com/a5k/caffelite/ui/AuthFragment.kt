package com.a5k.caffelite.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.a5k.caffelite.App
import com.a5k.caffelite.R
import com.a5k.caffelite.databinding.FragmentSingInBinding
import com.a5k.caffelite.domain.entity.User
import com.a5k.caffelite.presentation.state.AuthState
import com.a5k.caffelite.presentation.AuthViewModel
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class AuthFragment : Fragment() {

    companion object {

        private const val NAME_TITLE = "Авторизация"
        @JvmStatic
        fun newInstance() = AuthFragment()
    }

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var router: Router
    private var vb: FragmentSingInBinding? = null
    lateinit var viewModel: AuthViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = FragmentSingInBinding.inflate(inflater, container, false)
        return vb?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = viewModelFactory.create(AuthViewModel::class.java)
        openRegistrationFragment()
        viewModel.authLiveData.observe(viewLifecycleOwner, ::render)
        listenerSingIn()
    }

    private fun listenerSingIn() {
        val login = vb?.login
        val password = vb?.password
        vb?.btnSingIn?.setOnClickListener {
            val user = User(login?.text.toString(), password?.text.toString())
            viewModel.authorization(user)
        }
    }

    private fun render(data: AuthState) {
        when (data) {
            is AuthState.Success -> {
                Toast.makeText(requireContext(), "УСПЕШНО", Toast.LENGTH_SHORT).show()
                router.replaceScreen(FragmentScreen { CaffeListFragment.newInstance() })
            }
            is AuthState.Error -> {
                Toast.makeText(requireContext(), data.e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        requireActivity().title = NAME_TITLE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.app_bar_out_account -> {
                Log.i("AAA", "CLICK FROM AUTH FRAGMENT")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openRegistrationFragment() {
        vb?.toRegistration?.setOnClickListener {
            router.navigateTo(FragmentScreen { RegistrationFragment.newInstance() })
        }
    }

    override fun onDestroy() {
        vb = null
        super.onDestroy()
    }
}