package com.example.app_events_around.ui.main.event_detail

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.app_events_around.R
import com.example.app_events_around.common.base.BaseFragment
import com.example.app_events_around.common.base.BaseState
import com.example.app_events_around.common.extensions.concatHttps
import com.example.app_events_around.common.extensions.convertToDateFormat
import com.example.app_events_around.common.extensions.convertToPtBrCurrency
import com.example.app_events_around.common.extensions.showToast
import com.example.app_events_around.databinding.FragmentEventDetailBinding
import kotlinx.android.synthetic.main.fragment_event_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventDetailFragment : BaseFragment<FragmentEventDetailBinding, EventDetailViewModel>() {

    override val layoutId = R.layout.fragment_event_detail
    override val viewModel: EventDetailViewModel by viewModel()

    private val args: EventDetailFragmentArgs by navArgs()

    override fun onCreatedFragment() {
        viewBinding.viewModel = viewModel
        viewModel.loadEventDetail(args.eventId)
        btCheckin.setOnClickListener {
            viewModel.setEventCheckin(args.eventId, "Dev Teste", "teste@teste.com")
        }

        viewModel.stateDetail.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is BaseState.Success -> {
                    Glide.with(this).load(state.data.image.concatHttps()).into(ivImage)
                    tvTitle.text = state.data.title
                    tvDate.text = state.data.date.convertToDateFormat()
                    tvPrice.text = state.data.price.convertToPtBrCurrency()
                    tvDescription.text = state.data.description
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

        viewModel.stateCheckin.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is BaseState.Success -> {
                    this.context?.showToast("Check-in efetuado com sucesso!")
                }
                is BaseState.Failed -> {
                    this.context?.showToast("Ocorreu um erro ao tentar fazer o check-in")
                }
                is BaseState.Loading -> {
                }
                is BaseState.Empty -> {
                }
            }
        })
    }

}
