package com.zavijavasoft.yafina.core

import com.arellomobile.mvp.MvpPresenter
import com.zavijavasoft.yafina.model.CurrencyStorage
import com.zavijavasoft.yafina.ui.settings.SettingsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SettingsPresenterImpl @Inject constructor(private val storage: CurrencyStorage) : MvpPresenter<SettingsView>(), SettingsPresenter {

    override fun addCurrency(currency: String) {
        storage.addCurrency(currency)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    updateViewState()
                }
    }

    override fun removeCurrency(currency: String) {
        storage.removeCurrency(currency)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    updateViewState()
                }
    }

    private fun updateViewState() {
        storage.getCurrencyList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it ->
                    viewState.update(it)
                }
    }

}