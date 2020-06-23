package com.example.app_events_around.ui.main.event_list

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.app_events_around.R
import com.example.app_events_around.common.base.BaseFragment
import com.example.app_events_around.common.base.BaseState
import com.example.app_events_around.common.extensions.showToast
import com.example.app_events_around.databinding.FragmentEventListBinding
import com.example.app_events_around.domain.model.Event
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EventListFragment : BaseFragment<FragmentEventListBinding, EventListViewModel>() {

    override val layoutId: Int = R.layout.fragment_event_list
    override val viewModel: EventListViewModel by viewModel()

    private val viewAdapter: EventListAdapter by inject {
        parametersOf(object : EventListAdapter.Callback {
            override fun onClickEvent(event: Event) {
                findNavController().navigate(
                    EventListFragmentDirections.actionEventListFragmentToEventDetailFragment(
                        event.id
                    )
                )
            }
        })
    }

    override fun onCreatedFragment() {
        viewBinding.viewModel = viewModel
        viewBinding.recyclerView.adapter = viewAdapter

        viewModel.loadEventList()
        viewModel.stateList.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is BaseState.Success -> {
                    viewAdapter.events = state.data
                    viewAdapter.notifyDataSetChanged()
                }
                is BaseState.Failed -> {
                    this.context?.showToast("Ocorreu um erro ao tentar buscar as informações")
                }
                is BaseState.Loading -> {
                }
                is BaseState.Empty -> {
                }
            }
        })
    }

}
