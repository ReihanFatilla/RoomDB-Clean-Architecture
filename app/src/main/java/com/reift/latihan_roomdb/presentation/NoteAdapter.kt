package com.reift.latihan_roomdb.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reift.latihan_roomdb.databinding.ItemNoteBinding
import com.reift.latihan_roomdb.domain.model.Note

class NoteAdapter(val clickListener: (Note) -> Unit): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    val listNote = ArrayList<Note>()

    fun setData(list: List<Note>){
        listNote.clear()
        listNote.addAll(list)
    }

    class NoteViewHolder(val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NoteViewHolder(
        ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.binding.tvNote.text = listNote[position].note
        holder.itemView.setOnClickListener { clickListener(listNote[position]) }
    }

    override fun getItemCount() = listNote.size
}