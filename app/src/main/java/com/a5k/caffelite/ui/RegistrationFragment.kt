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
import com.a5k.caffelite.databinding.FragmentSingOutBinding
import com.a5k.caffelite.domain.entity.User
import com.a5k.caffelite.presentation.RegistrationViewModel
import com.a5k.caffelite.presentation.state.AuthState
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class RegistrationFragment : Fragment() {

    companion object{
        private const val NAME_TITLE = "Регистрация"
        @JvmStatic
        fun newInstance() = RegistrationFragment()
    }

    private var vb: FragmentSingOutBinding? = null
    lateinit var viewModel: RegistrationViewModel
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var router: Router

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = FragmentSingOutBinding.inflate(inflater, container, false)
        return vb?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = viewModelFactory.create(RegistrationViewModel::class.java)
        viewModel.registrationViewModel.observe(viewLifecycleOwner, ::render)
        listenerRegistrationUserField()
    }

    private fun render(data: AuthState) {
        when (data) {
            is AuthState.Success -> {
                Toast.makeText(requireContext(), getString(R.string.SUCCESS_REGISTRATION), Toast.LENGTH_SHORT).show()
                router.navigateTo(FragmentScreen { CaffeListFragment.newInstance() })
            }
            is AuthState.Error -> {
                Toast.makeText(requireContext(), data.e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun listenerRegistrationUserField() {
        val login = vb?.newLogin
        val password = vb?.newPassword
        val repeatPassword = vb?.repeatPassword
        vb?.btnSingOut?.setOnClickListener {
            val user = User(login?.text.toString(), password?.text.toString(), repeatPassword?.text.toString())
            viewModel.registration(user)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.app_bar_out_account -> {
                Log.i("AAA", "CLICK MENU FROM REGISTRATION FRAGMENT")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        requireActivity().title = NAME_TITLE
    }

    override fun onDestroy() {
        vb = null
        super.onDestroy()
    }
}
