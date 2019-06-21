package com.example.ama.questapp.presentation.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ama.questapp.R
import com.example.ama.questapp.data.db.model.UserTask
import com.example.ama.questapp.data.db.model.pojo.UserTaskWithPattern
import com.example.ama.questapp.presentation.MainActivity
import com.example.ama.questapp.presentation.base.BaseFragment
import com.example.ama.questapp.presentation.base.ViewState
import com.example.ama.questapp.presentation.list.adapter.ActiveQuestsAdapter
import com.example.ama.questapp.presentation.list.mvp.ActiveQuestsPresenter
import com.example.ama.questapp.presentation.list.mvp.ActiveQuestsView
import kotlinx.android.synthetic.main.fragment_active_quests.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class ActiveQuestsFragment : BaseFragment(), ActiveQuestsView {

    @Inject
    @InjectPresenter
    lateinit var presenter: ActiveQuestsPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    companion object {
        fun newInstance() = ActiveQuestsFragment()
    }

    private var adapter: ActiveQuestsAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_active_quests, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUI()
    }

    private fun initUI() {
        rvQuests.layoutManager = LinearLayoutManager(context)
        adapter = ActiveQuestsAdapter { presenter.tryToCompleteQuest(it) }
        add_quest.setOnClickListener {
            (activity as? MainActivity)?.openAddQuestScreen()
        }
        rvQuests.adapter = adapter
    }

    override fun render(viewState: ViewState<List<UserTaskWithPattern>>) {

        viewState.loading?.let { loading(it) }
        viewState.error?.let { error(it) }
        viewState.data?.let { data(it) }
        emptyMessage(viewState.loading == null && viewState.error == null && viewState.data == null)
    }

    override fun tryToCompleteQuest(userTask: UserTask) {
        presenter.tryToCompleteQuest(userTask)
    }

    private fun loading(loading: Boolean) {
        pbLoading.visibility = if (loading) View.VISIBLE else View.GONE
        rvQuests.visibility = if (!loading) View.VISIBLE else View.GONE
    }

    private fun error(show: Boolean) {
        //TODO: error message
        if (show) Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
    }

    private fun data(data: List<UserTaskWithPattern>?) {
        adapter?.submitList(data)
    }

    private fun emptyMessage(show: Boolean) {
        tvEmpty.visibility = if (show) View.VISIBLE else View.GONE
    }
}