package com.example.lab_8

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lab_8.databinding.RecycleViewBinding


class PhoneAdapter (private val items: List<Pair<String, String?>>,
                    private val onItemClick: (position: Int) -> Unit):
                    RecyclerView.Adapter<PhoneAdapter.PhoneHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneHolder {
        val binding = RecycleViewBinding.inflate(LayoutInflater.from(parent.context))
        return PhoneHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: PhoneHolder, position: Int) {
        val phone = items[position]
        holder.bind(phone.first, phone.second)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class PhoneHolder(private val binding: RecycleViewBinding,
                      private val onItemClick: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
            init {
                binding.phoneButton.setOnClickListener {
                    onItemClick(adapterPosition)
                }
            }

            fun bind(phoneName: String, imageUrl: String?) {
                Glide.with(binding.imageView)
                    .load(imageUrl)
                    .centerCrop()
                    .placeholder(binding.imageView.drawable)
                    .error(R.drawable.ic_launcher_background)
                    .fallback(R.drawable.ic_launcher_foreground)
                    .into(binding.imageView)
                binding.phoneButton.text = phoneName
        }
    }
}