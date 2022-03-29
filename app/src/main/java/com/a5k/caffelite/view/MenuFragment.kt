package com.a5k.caffelite.view

import android.accounts.AccountManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.a5k.caffelite.data.AppState
import com.a5k.caffelite.data.model.MenuCaffe
import com.a5k.caffelite.databinding.FragmentMenuBinding
import com.a5k.caffelite.view.adapter.MenuAdapter
import com.a5k.caffelite.view.adapter.onClickAddOrder
import com.a5k.caffelite.viewmodel.MenuViewModel


class MenuFragment : Fragment() {

    private var vb: FragmentMenuBinding? = null
    private var menuAdapter: MenuAdapter? = null
    private val viewModel: MenuViewModel by lazy {
        MenuViewModel(AccountManager.get(requireContext()), activity as IMainNavigation)
    }

    private var idPointCaffe: Int? = null
    private var listOrder = mutableSetOf<MenuCaffe>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idPointCaffe = it.getInt(ID_POINT_CAFFE)

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
        initRecycler()
        viewModel.getLiveData().observe(viewLifecycleOwner) { data -> render(data) }

        if (idPointCaffe != null) {
            viewModel.getToken(idPointCaffe!!)
        }
    }

    private fun render(data: AppState) {
        when (data) {
            is AppState.Success -> {
                val dataListMenu = data.item as List<MenuCaffe>
                menuAdapter?.init(dataListMenu)
            }
            is AppState.Error -> {
                Toast.makeText(requireContext(), ERROR, Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun initRecycler() {
        vb?.recyclerContainerMenuList?.run {
            layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
            menuAdapter = MenuAdapter()
            adapter = menuAdapter
        }
        menuAdapter?.listenerItem(object : onClickAddOrder {
            override fun addOrder(item: MenuCaffe) {

            }

            override fun removeOrder(item: MenuCaffe) {

            }

        })
    }

    override fun onResume() {
        super.onResume()
        (activity as IMainNavigation).run {
            setTitleActionBar(NAME_TITLE)
            setHomeUp(true)
        }
    }

    companion object {
        private const val NAME_TITLE = "Меню"
        private const val ID_POINT_CAFFE = "idPointCaffe"
        private const val ERROR = "Ошибка загрузки"
    }
}