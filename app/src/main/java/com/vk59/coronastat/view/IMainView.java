package com.vk59.coronastat.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface IMainView extends MvpView {


    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void failure();
}
