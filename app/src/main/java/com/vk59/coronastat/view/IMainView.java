package com.vk59.coronastat.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.vk59.coronastat.model.CountryData;

public interface IMainView extends MvpView {
    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void loading();

    @StateStrategyType(value = SingleStateStrategy.class)
    void success(CountryData data);

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void failure();
}
