package com.congnt.baseapplicatiop.base;

/**
 * Created by CONG on 10/4/2017.
 */

public class BasePresenterImpl<V extends BaseView> implements BasePresenter {
    private V mView;

    public BasePresenterImpl(V mView) {
        this.mView = mView;
    }

    @Override
    public V getView() {
        return mView;
    }

    @Override
    public BaseActivity getViewContext() {
        return mView.getViewContext();
    }

    @Override
    public void onDestroyViewFrg() {
    }

    @Override
    public void onDestroyActivity() {
    }
}
