package com.example.photogallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import api.FlickrApi
import com.example.photogallery.databinding.FragmentPhotoGalleryBinding
import kotlinx.coroutines.launch
import retrofit2.Retrofit
//import retrofit2.converter.scalars.ScalarsConverterFactory
import kotlinx.coroutines.flow.collect



//private const val TAG = "PhotoGalleryFragment"

class PhotoGalleryFragment : Fragment()  {
    private var _binding: FragmentPhotoGalleryBinding? = null
    private val binding
    get() = checkNotNull(_binding) {
        "Cannot access binding because it is null. Is the view visible?"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoGalleryBinding.inflate(inflater, container, false)
        binding.photoGrid.layoutManager = GridLayoutManager(context, 3)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val photoGalleryViewModel = ViewModelProvider(this).get(PhotoGalleryViewModel::class.java)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                photoGalleryViewModel.galleryItems.collect { items ->
                    binding.photoGrid.adapter = PhotoListAdapter(items)
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

//https://www.flickr.com/services/api/flickr.interestingness.getList.html
//https://stackoverflow.com/questions/4748283/flickr-api-on-android