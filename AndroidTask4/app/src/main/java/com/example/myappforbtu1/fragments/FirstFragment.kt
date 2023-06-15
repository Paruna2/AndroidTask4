package com.example.myappforbtu1.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.myappforbtu1.R
import com.example.myappforbtu1.adapters.MyRecyclerViewAdapter
import com.example.myappforbtu1.databinding.FragmentFirstBinding
import com.example.myappforbtu1.models.Fruit

class FirstFragment : Fragment() {

    private lateinit var _binding : FragmentFirstBinding
    private val binding get() = _binding

    lateinit var myAdapter: MyRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myAdapter = MyRecyclerViewAdapter(getData())
        val navigation = findNavController()

        binding.apply {
            myRecyclerView.adapter = myAdapter
            myRecyclerView.layoutManager = LinearLayoutManager(context)

            myAdapter.onClick = {
                val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(it.fruitName!!, it.fruitDescription!!)
                navigation.navigate(action)
            }
        }

    }

    private fun getData(): MutableList<Fruit> {
        var fruitArray = arrayListOf<Fruit>()
        fruitArray.add(Fruit("fruit1","description1"))
        fruitArray.add(Fruit("fruit2","description2"))
        fruitArray.add(Fruit("fruit3","description3"))
        fruitArray.add(Fruit("fruit4","description4"))
        return fruitArray
    }
}