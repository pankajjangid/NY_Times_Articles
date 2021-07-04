package com.pankajjangid.nytimespopulararticles.modules.home

import com.pankajjangid.nytimespopulararticles.R
import com.pankajjangid.nytimespopulararticles.base.BaseRVAdapter
import com.pankajjangid.nytimespopulararticles.databinding.RowItemHomeBinding
import com.pankajjangid.nytimespopulararticles.networking.response.PopularArticleResponse
import com.pankajjangid.nytimespopulararticles.utils.Logger
import com.pankajjangid.nytimespopulararticles.utils.view_binding.BindingAdapter

class HomeAdapter: BaseRVAdapter<PopularArticleResponse.Result, RowItemHomeBinding>() {

    override fun getLayout(): Int= R.layout.row_item_home

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<RowItemHomeBinding>,
        position: Int
    ) {



        Logger.debug("ADAPTER POSTIONS : $position")
        holder.binding.item = items[position]

        if (!items[position].media.isNullOrEmpty()){
            BindingAdapter.loadCardImage(holder.binding.imageView, items[position].media?.get(0)?.mediaMetadata?.get(0)?.url)

        }else{
            holder.binding.imageView.setImageResource(R.drawable.no_image)
        }

        BindingAdapter.checkNull(holder.binding.tvTitle,items[position].title)
        BindingAdapter.checkNull(holder.binding.tvDesc,items[position].abstract)
        BindingAdapter.checkNull(holder.binding.tvDate,items[position].publishedDate)
        BindingAdapter.checkNull(holder.binding.tvAuthor,items[position].byline)
    }
}