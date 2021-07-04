package com.pankajjangid.nytimespopulararticles.modules.home

import com.pankajjangid.nytimespopulararticles.R
import com.pankajjangid.nytimespopulararticles.base.BaseRVAdapter
import com.pankajjangid.nytimespopulararticles.databinding.RowItemHomeBinding
import com.pankajjangid.nytimespopulararticles.networking.response.PopularArticleResponse

class HomeAdapter: BaseRVAdapter<PopularArticleResponse.Result, RowItemHomeBinding>() {
    override fun getLayout(): Int= R.layout.row_item_home

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<RowItemHomeBinding>,
        position: Int
    ) {




    }
}