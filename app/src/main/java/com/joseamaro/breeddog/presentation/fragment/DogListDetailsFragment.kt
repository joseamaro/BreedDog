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
import androidx.recyclerview.widget.RecyclerView
import com.joseamaro.breeddog.R
import com.joseamaro.breeddog.di.component.DaggerDogListDetailsFragmentComponent
import com.joseamaro.breeddog.presentation.adapter.DogListDetailsAdapter
import com.joseamaro.breeddog.presentation.viewModel.DogListDetailsViewModel
import com.joseamaro.breeddog.utils.Failure
import com.joseamaro.breeddog.utils.ResourceState
import com.joseamaro.breeddog.utils.ViewModelFactory
import com.joseamaro.breeddog.utils.extension.requireNavController
import com.joseamaro.breeddog.utils.extension.showMessage
import com.joseamaro.breeddog.utils.extension.showProgress
import com.joseamaro.breeddog.utils.getViewModel
import kotlinx.android.synthetic.main.fragment_dog_list_details.*
import javax.inject.Inject

class DogListDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: DogListDetailsViewModel
    lateinit var adapter: DogListDetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependence()
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAdapter()
        val race_name = arguments?.getString("race")
        race_name?.let {
            viewModel.getImageRace(it)
        }
        iv_back.setOnClickListener {
            goBack()
        }
        rc_images.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.loadImageList()
                }
            }
        })
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_dog_list_details, container, false)


    private fun injectDependence() {
        DaggerDogListDetailsFragmentComponent.builder().build().inject(this)
    }

    private fun handleDetailsDog(status: ResourceState, data: List<String>?, failure: Failure?) {

        when (status) {
            ResourceState.LOADING -> {
                pb_details.showProgress(true, activity)
            }
            ResourceState.SUCCESS -> {
                pb_details.showProgress(false, activity)
                data?.let {
                    addImageList(it)
                }
            }
            ResourceState.ERROR -> {
                pb_details.showProgress(false, activity)
                showMessage((failure as Failure.Error).errorMessage, context)
            }
            else -> {
            }
        }
    }

    private fun initViewModel() {
        viewModel = getViewModel(viewModelFactory)
        viewModel.liveData.observe(this, Observer {
            it?.also {
                handleDetailsDog(it.status, it.data, it.failure)
            }
        })
    }

    private fun initAdapter() {
        adapter = DogListDetailsAdapter {}
        rc_images.layoutManager = LinearLayoutManager(activity)
        rc_images.layoutManager = GridLayoutManager(context, 1)
        rc_images.adapter = adapter
    }

    fun addImageList(images: List<String>) {
        adapter.addList(images)
    }

    fun goBack() {
        requireNavController().navigate(R.id.action_dogListDetailsFragment_to_dogListFragment,
                null, NavOptions.Builder()
                .setPopUpTo(R.id.dogListFragment, true)
                .build())
    }
}

