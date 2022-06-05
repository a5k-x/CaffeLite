package com.a5k.caffelite.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.a5k.caffelite.App
import com.a5k.caffelite.R
import com.a5k.caffelite.databinding.FragmentMenuBinding
import com.a5k.caffelite.domain.entity.MenuCaffe
import com.a5k.caffelite.presentation.MenuViewModel
import com.a5k.caffelite.presentation.state.MenuState
import com.a5k.caffelite.ui.adapter.MenuAdapter
import com.a5k.caffelite.ui.adapter.onClickAddOrder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject


class MenuFragment : Fragment() {

    companion object {

        private const val NAME_TITLE = "Меню"
        private const val ID_CAFFE = "idPointCaffe"
        private const val ERROR = "Ошибка загрузки"
        @JvmStatic
        fun newInstance(idCaffe: Int) = MenuFragment().apply {
            arguments = Bundle().apply {
                putInt(ID_CAFFE, idCaffe)
            }
        }
    }

    private var vb: FragmentMenuBinding? = null
    private var menuAdapter = MenuAdapter()
    private var idCaffe: Int? = null
    lateinit var viewModel: MenuViewModel

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var router: Router
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {
            idCaffe = it.getInt(ID_CAFFE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = FragmentMenuBinding.inflate(inflater, container, false)
        return vb?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = viewModelFactory.create(MenuViewModel::class.java)
        initAdapter()
        viewModel.menuLiveData.observe(viewLifecycleOwner, ::render)
        idCaffe?.let { viewModel.menu(it) }
    }

    private fun render(data: MenuState) {
        when (data) {
            is MenuState.Success -> {
                menuAdapter.settingAdapter(data.listMenu)
            }
            is MenuState.Error -> {
                Toast.makeText(requireContext(), ERROR, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initAdapter() {
        vb?.recyclerContainerMenuList?.run {
            layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
            menuAdapter = MenuAdapter()
            adapter = menuAdapter
        }
        menuAdapter.listenerItem(object : onClickAddOrder {
            override fun addOrder(order: MenuCaffe) {}

            override fun removeOrder(order: MenuCaffe) {}
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.app_bar_out_account -> {
                router.replaceScreen(FragmentScreen {AuthFragment.newInstance()})
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