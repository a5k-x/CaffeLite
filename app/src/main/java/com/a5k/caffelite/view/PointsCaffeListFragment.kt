package com.a5k.caffelite.view

import android.accounts.AccountManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.a5k.caffelite.data.AppState
import com.a5k.caffelite.data.model.PointCaffe
import com.a5k.caffelite.databinding.FragmentPointsCaffeListBinding
import com.a5k.caffelite.view.adapter.MainAdapter
import com.a5k.caffelite.view.adapter.OnClickCust
import com.a5k.caffelite.viewmodel.PointsCaffeViewModel


class PointsCaffeListFragment : Fragment() {

    private var vb: FragmentPointsCaffeListBinding? = null
    private val viewModel: PointsCaffeViewModel by lazy {
        PointsCaffeViewModel(AccountManager.get(requireContext()), activity as IMainNavigation)
    }
    private var mainAdapter: MainAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = FragmentPointsCaffeListBinding.inflate(inflater, container, false)
        return vb?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner) { data -> render(data) }
        initAdapter()
        viewModel.getToken()
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
                val listCaffe = data.item as List<PointCaffe>
                mainAdapter?.init(listCaffe)
            }
            is AppState.Error -> {
                if (data.e.message == ERROR_401 || data.e.message ==ERROR_Unauthorized) {
                    (activity as IMainNavigation).toSingInFragment()
                }
                Toast.makeText(requireContext(), ERROR, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initAdapter() {
        vb?.recyclerContainerPointsCaffe?.run {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            mainAdapter = MainAdapter()
            adapter = mainAdapter
        }
        mainAdapter?.listenerItem(object : OnClickCust {
            override fun onHandlerClickLister(idPointCaffe: Int) {
                viewModel.toMenuFragment(idPointCaffe)
            }
        })
    }
companion object{
    private const val NAME_TITLE = "Ближайшие кофейни"
    private const val ERROR = "Ошибка загрузки"
    private const val ERROR_401 = "401"
    private const val ERROR_Unauthorized = "Unauthorized"

}

}
