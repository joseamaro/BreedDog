package com.joseamaro.breeddog.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.joseamaro.breeddog.R
import com.joseamaro.breeddog.di.component.DaggerDogListFragmentComponent
import com.joseamaro.breeddog.domain.model.Race
import com.joseamaro.breeddog.presentation.adapter.DogListAdapter
import com.joseamaro.breeddog.presentation.viewModel.DogListViewModel
import com.joseamaro.breeddog.utils.Failure
import com.joseamaro.breeddog.utils.ResourceState
import com.joseamaro.breeddog.utils.ViewModelFactory
import com.joseamaro.breeddog.utils.extension.requireNavController
import com.joseamaro.breeddog.utils.extension.showMessage
import com.joseamaro.breeddog.utils.extension.showProgress
import com.joseamaro.breeddog.utils.getViewModel
import kotlinx.android.synthetic.main.fragment_dog_list.*
import javax.inject.Inject

class DogListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: DogListViewModel
    lateinit var dogListAdapter: DogListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependence()
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
        viewModel.getRaceDog()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_dog_list, container, false)

    companion object {
        fun newInstance(): DogListFragment {
            val instance = DogListFragment()
            return instance
        }
    }

    fun handleDogList(status: ResourceState, data: Race?, failure: Failure?) {
        when (status) {
            ResourceState.LOADING -> {
                pbCoupon.showProgress(true, activity)
            }
            ResourceState.SUCCESS -> {
                pbCoupon.showProgress(false, activity)
                data?.let {
                    displayRace(data)
                }
            }
            ResourceState.ERROR -> {
                pbCoupon.showProgress(false, activity)
                when (failure) {
                    Failure.NetworkConnection -> {}

                    Failure.ServerError -> {
                        pbCoupon.showProgress(false, activity)
                        showMessage((failure as Failure.Error).errorMessage, context)

                    }
                }
            }
            else -> {
            }
        }
    }

    fun initAdapter() {
        dogListAdapter = DogListAdapter {
            goToDetailImageDog(it)
        }
        recycler_view.layoutManager = LinearLayoutManager(activity)
        recycler_view.layoutManager = GridLayoutManager(context, 1)
        recycler_view.adapter = dogListAdapter
    }

    fun displayRace(race: Race?) {
        race?.let {
            dogListAdapter.setList(it.message)
        }
    }

    private fun injectDependence() {
        DaggerDogListFragmentComponent.builder().build().inject(this)
    }

    fun initViewModel() {
        viewModel = getViewModel(viewModelFactory)
        viewModel.liveData.observe(this, Observer {
            it?.also {
                handleDogList(it.status, it.data, it.failure)
            }
        })
    }

    private fun goToDetailImageDog(it: String) {
        val b = Bundle()
        b.putString("race", it)
        requireNavController().navigate(R.id.action_dogListFragment_to_dogListDetailsFragment,
                b, NavOptions.Builder()
                .setPopUpTo(R.id.dogListDetailsFragment, true)
                .build())
    }
}