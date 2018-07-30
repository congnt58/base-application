package com.congnt.baseapplicatiop.base;

/**
 * Created by CONG on 9/22/2017.
 */

public interface BaseView<P extends BasePresenter> {

    P getPresenter();

    P onCreatePresenter();

    void initData();

    void onPrepareLayout();

    BaseActivity getViewContext();

    void onRequestError(int errorCode, String message);

    void showProgress(int notifyId);

    void hideProgress();

    void onNoConnection();

    void onNoApiUrlRestoreData();
}
