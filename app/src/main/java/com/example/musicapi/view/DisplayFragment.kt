package com.example.musicapi.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapi.databinding.DisplayFragmentBinding
import com.example.musicapi.model.MusicResponse
import com.example.musicapi.model.remote.Network
import com.example.musicapi.view.adapter.MusicAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "DisplayFragment"

class DisplayFragment: Fragment() {

    companion object{
        const val EXTRA_TERM = "EXTRA_TERM"

        fun newInstance(
            musicTerm: String
        ) = DisplayFragment().apply {
            arguments = Bundle().apply {
                putString(EXTRA_TERM, musicTerm)
            }
        }
    }

    private lateinit var binding: DisplayFragmentBinding
    private lateinit var musicTerm: String
    private val adapter: MusicAdapter by lazy {
        MusicAdapter(mutableListOf())
    }

    private fun updateAdapter(newDataSet: MusicResponse){
        adapter.updateDataSet(newDataSet.results)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DisplayFragmentBinding.inflate(inflater,
        container,
        false)
        arguments?.let {
            musicTerm = it.getString(EXTRA_TERM)?:""

            getMusicData(musicTerm)
        }
        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.apply {
            songListResult.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            songListResult.adapter = adapter
        }
    }

    private fun getMusicData(musicTerm: String) {

        Network().api
            .getMusicSong(musicTerm).enqueue(
                object : Callback<MusicResponse> {
                    override fun onResponse(
                        call: Call<MusicResponse>,
                        response: Response<MusicResponse>
                    ) {
                        if(response.isSuccessful){
                            response.body()?.let {
                                updateAdapter(it)
                                Log.d(TAG, "onResponse: ${response.message()}")
                            }
                        }else{
                            Log.d(TAG, "onResponse: ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
                        Log.d(TAG, "onFailure: ${t.message}")
                        t.printStackTrace()
                    }
                }
            )
    }

}