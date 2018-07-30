package com.congnt.baseapplicatiop.base;

/**
 * Created by CONG on 9/22/2017.
 */

public interface BasePresenter {
    BaseView getView();
    BaseActivity getViewContext();
    void onDestroyViewFrg();
    void onDestroyActivity();
}
