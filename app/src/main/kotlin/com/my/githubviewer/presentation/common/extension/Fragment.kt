package com.my.githubviewer.presentation.common.extension

import android.content.Context
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.my.githubviewer.presentation.ui.base.BaseActivity
import com.my.githubviewer.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.activity_base_layout.*

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
    beginTransaction().func().commit()

inline fun <reified T : ViewModel> Fragment.viewModel(factory: ViewModelProvider.Factory, body: T.() -> Unit): T {
    val viewModel = ViewModelProvider(this, factory).get(T::class.java)
    viewModel.body()
    return viewModel
}

val BaseFragment<out ViewDataBinding, out ViewModel>.appContext: Context? get() = activity?.applicationContext

val BaseFragment<out ViewDataBinding, out ViewModel>.viewContainer: View get() = (activity as BaseActivity).fragmentContainer