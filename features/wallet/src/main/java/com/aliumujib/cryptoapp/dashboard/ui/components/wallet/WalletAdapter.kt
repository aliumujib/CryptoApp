package com.aliumujib.cryptoapp.dashboard.ui.components.wallet

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aliumujib.cryptoapp.uicommons.inflate
import com.aliumujib.cryptoapp.dashboard.R
import com.aliumujib.cryptoapp.dashboard.databinding.LayoutWalletItemBinding
import com.aliumujib.cryptoapp.dashboard.presentation.model.WalletUIModel
import com.aliumujib.cryptoapp.uicommons.formatToCurrencyString
import com.aliumujib.cryptoapp.uicommons.recursivelyApplyToChildren

typealias WalletClickListener = (WalletUIModel) -> Unit

class WalletAdapter(private val onClick: WalletClickListener) :
    ListAdapter<WalletUIModel, WalletAdapter.WalletViewHolder>(diffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
        return WalletViewHolder(LayoutWalletItemBinding.bind(parent.inflate(R.layout.layout_wallet_item)))
    }

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

    class WalletViewHolder(private val binding: LayoutWalletItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(wallet: WalletUIModel, onClick: WalletClickListener) {
            binding.run {
                imageView.load(wallet.currency?.colorfulImageUrl.orEmpty())
                coinName.text = wallet.currency?.name
                cryptoAmount.text = "${wallet.coinAmount}${wallet.coinId}"
                wallet.fiatCurrencyCode?.let {
                    fiatAmount.text = wallet.fiatAmount.formatToCurrencyString(it)
                }
                root.recursivelyApplyToChildren {
                    it.setOnClickListener {
                        onClick(wallet)
                    }
                }
            }
        }
    }

    companion object {
        val diffUtilCallback: DiffUtil.ItemCallback<WalletUIModel>
            get() = object : DiffUtil.ItemCallback<WalletUIModel>() {
                override fun areItemsTheSame(
                    oldItem: WalletUIModel,
                    newItem: WalletUIModel
                ): Boolean {
                    return oldItem.coinId == newItem.coinId
                }

                override fun areContentsTheSame(
                    oldItem: WalletUIModel,
                    newItem: WalletUIModel
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}
