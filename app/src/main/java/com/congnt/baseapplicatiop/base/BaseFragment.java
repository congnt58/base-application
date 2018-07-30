package com.congnt.baseapplicatiop.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by CONG on 10/4/2017.
 */

public abstract class BaseFragment<P extends BasePresenter, A extends BaseActivity> extends Fragment implements BaseView<P> {
    private P mPresenter;
    private View mRootView;
    private boolean isInitialzed = false;
    private Unbinder unbinder;
    private boolean isRunning = false;

    public BaseFragment() {
        // create presenter for this view
        //this.mPresenter = onCreatePresenter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // create presenter for this view
        isRunning = true;
        if (mPresenter == null) {
            mPresenter = onCreatePresenter();
        }
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!isInitialzed) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
            //Inject views
            unbinder = ButterKnife.bind(this, mRootView);
            isInitialzed = true;
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Prepare layout
        onPrepareLayout();
    }

    /**
     * @return layout resource id for activity
     */
    protected abstract int getLayoutId();

    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDestroyViewFrg();
        super.onDestroyView();
    }

    @Override
    public BaseActivity getViewContext() {
        if (getBaseActivity() != null) {
            return getBaseActivity();
        }
        return null;
    }

    protected A getBaseActivity() {
        if (getActivity() != null) {
            return (A) getActivity();
        }
        return null;
    }
    public boolean isRunning(){
        return isRunning;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        isRunning = false;
        if (unbinder != null) {
            unbinder.unbind();
        }
        getBaseActivity().hideKeyboard();
        super.onDestroy();
    }
}
