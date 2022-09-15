package com.reift.latihan_roomdb.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reift.latihan_roomdb.R
import com.reift.latihan_roomdb.databinding.ActivityMainBinding
import com.reift.latihan_roomdb.domain.model.Note
import com.reift.latihan_roomdb.presentation.helper.SwipeToDelete

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    var currentNoteId: Int? = null
    private lateinit var mAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        viewModel.setAddStatus()

        viewModel.getAllNote()
        viewModel.listNote.observe(this){
            setUpRecyclerView(it)
        }

        setUpView()
    }

    private fun setUpView() {
        binding.apply {
            viewModel.isUpdate.observe(this@MainActivity){
                if(it){
                    btnAddOrUpdate.text = "Update"
                    btnAddOrUpdate.setOnClickListener {
                        if(currentNoteId != null){
                            viewModel.updateNote(Note(currentNoteId!!, edtNote.text.toString()))
                            viewModel.setAddStatus()
                            binding.edtNote.setText("")
                        }
                    }
                } else {
                    btnAddOrUpdate.text = "Add"
                    btnAddOrUpdate.setOnClickListener {
                        viewModel.insertNote(Note(0, edtNote.text.toString()))
                        binding.edtNote.setText("")
                    }
                }
            }
        }
    }

    private fun setUpRecyclerView(list: List<Note>) {
        binding.rvNote.apply {
            mAdapter = NoteAdapter{
                binding.apply {
                    edtNote.setText(it.note)
                    currentNoteId = it.id
                    viewModel.setUpdateStatus()
                }
            }
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
            mAdapter.setData(list)

            swipeToDelete(this)
        }
    }

    private fun swipeToDelete(recyclerView: RecyclerView) {
        val swipeToDeleteCallback = object : SwipeToDelete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val deletedItem = mAdapter.listNote.get(viewHolder.adapterPosition)
                viewModel.deleteNote(deletedItem)
                mAdapter.notifyItemRemoved(viewHolder.adapterPosition)
                Toast.makeText(applicationContext, "Successfully Deleting Note", Toast.LENGTH_SHORT).show()
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

}