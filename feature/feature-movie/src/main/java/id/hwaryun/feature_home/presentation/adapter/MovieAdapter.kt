package id.hwaryun.feature_home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.hwaryun.core_data.model.viewparam.BoxOfficeViewParam
import id.hwaryun.feature_home.databinding.ItemMoviesBinding

class MovieAdapter(
    private val onClick: (BoxOfficeViewParam) -> Unit
) : ListAdapter<BoxOfficeViewParam, MovieAdapter.MovieViewHolder>(MovieDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MovieViewHolder(private val binding: ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: BoxOfficeViewParam) {
            binding.tvTitle.text = data.title
            Glide.with(itemView.context)
                .load(data.image)
                .into(binding.ivMovie)

            itemView.setOnClickListener {
                onClick(data)
            }
        }
    }
}

object MovieDiffCallback : DiffUtil.ItemCallback<BoxOfficeViewParam>() {
    override fun areItemsTheSame(oldItem: BoxOfficeViewParam, newItem: BoxOfficeViewParam): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: BoxOfficeViewParam, newItem: BoxOfficeViewParam): Boolean {
        return oldItem.id == newItem.id
    }
}