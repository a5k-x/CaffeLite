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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.a5k.caffelite.App
import com.a5k.caffelite.R
import com.a5k.caffelite.databinding.FragmentPointsCaffeListBinding
import com.a5k.caffelite.presentation.CaffeListViewModel
import com.a5k.caffelite.presentation.state.CaffeListState
import com.a5k.caffelite.ui.adapter.MainAdapter
import com.a5k.caffelite.ui.adapter.OnClick
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject


class CaffeListFragment : Fragment() {

    companion object {

        private const val NAME_TITLE = "Кофейни"
        private const val ERROR = "Ошибка загрузки"
        @JvmStatic
        fun newInstance() = CaffeListFragment()
    }

    private var vb: FragmentPointsCaffeListBinding? = null
    lateinit var viewModel: CaffeListViewModel
    private var mainAdapter: MainAdapter? = null
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.app_bar_out_account -> {
                router.replaceScreen(FragmentScreen {AuthFragment.newInstance()})
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = FragmentPointsCaffeListBinding.inflate(inflater, container, false)
        return vb?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = viewModelFactory.create(CaffeListViewModel::class.java)
        initAdapter()
        viewModel.caffeListLiveData.observe(viewLifecycleOwner, ::render)
        viewModel.listCaffe()
    }

    private fun initAdapter() {
        vb?.recyclerContainerPointsCaffe?.run {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            mainAdapter = MainAdapter()
            adapter = mainAdapter
        }
        mainAdapter?.listenerItem(object : OnClick {
            override fun onHandlerClickLister(idCaffe: Int) {
                router.navigateTo(FragmentScreen { MenuFragment.newInstance(idCaffe) })
            }
        })
    }

    private fun render(data: CaffeListState) {
        when (data) {
            is CaffeListState.Success -> {
                mainAdapter?.settingAdapter(data.listCaffe)

            }
            is CaffeListState.Error -> {
                Toast.makeText(requireContext(), ERROR, Toast.LENGTH_SHORT).show()
            }
        }
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
