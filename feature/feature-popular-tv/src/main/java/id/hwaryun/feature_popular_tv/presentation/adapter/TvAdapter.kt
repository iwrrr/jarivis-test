package id.hwaryun.feature_popular_tv.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.hwaryun.core_data.model.viewparam.PopularTvViewParam
import id.hwaryun.feature_popular_tv.databinding.ItemTvsBinding

class TvAdapter(
    private val onClick: (PopularTvViewParam) -> Unit
) : ListAdapter<PopularTvViewParam, TvAdapter.MovieViewHolder>(MovieDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemTvsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MovieViewHolder(private val binding: ItemTvsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PopularTvViewParam) {
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

object MovieDiffCallback : DiffUtil.ItemCallback<PopularTvViewParam>() {
    override fun areItemsTheSame(
        oldItem: PopularTvViewParam,
        newItem: PopularTvViewParam
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: PopularTvViewParam,
        newItem: PopularTvViewParam
    ): Boolean {
        return oldItem.id == newItem.id
    }
}