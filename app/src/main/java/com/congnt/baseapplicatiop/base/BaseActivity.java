package com.congnt.baseapplicatiop.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Base Activity
 * Created by CONG on 9/22/2017.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView<P> {
    private P mPresenter;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        //Create presenter for this view
        mPresenter = onCreatePresenter();

        //Inject View
        unbinder = ButterKnife.bind(this);

        //prepare data
        initData();

        //Prepare layout
        onPrepareLayout();
    }

    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        mPresenter.onDestroyActivity();
        super.onDestroy();
    }

    /**
     * @return resource id for activity
     */
    public abstract int getLayoutID();

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null && view.getWindowToken() != null) {
            InputMethodManager inputMm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
//            inputMm.hideSoftInputFromInputMethod(view.getWindowToken(), 0);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        }
    }

    public void showKeyboard(final EditText editText) {
        editText.requestFocus();
        editText.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager inputMm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            }
        }, 200);
    }

    /**
     * set inputMode view
     *
     * @param inputMode WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
     */
    public void setKeyBoardInputMode(int inputMode) {
        if (getWindow() != null) {
            getWindow().setSoftInputMode(inputMode);
        }
    }

    /**
     * @return current Context
     */
    @Override
    public BaseActivity getViewContext() {
        return BaseActivity.this;
    }

    public void addFrg(int containtId, Fragment frgAdd, boolean addToBackStack, int idAnimEnter, int idAnimExit) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragmentInContaint = getSupportFragmentManager().findFragmentById(containtId);
        ft.hide(fragmentInContaint);
        if (idAnimEnter != 0 && idAnimExit != 0) {
            ft.setCustomAnimations(idAnimEnter, idAnimExit, idAnimEnter, idAnimExit);
        }
        ft.add(containtId, frgAdd);
        if (addToBackStack) {
            ft.addToBackStack(frgAdd.getClass().getSimpleName());
        }
        ft.commit();
    }

    public void replaceFragmentWithAnimation(int containerId, Fragment fragment, int idEnterAnim, int idExitAnim, boolean addToBackStack) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(idEnterAnim, idExitAnim, idEnterAnim, idExitAnim);
        ft.replace(containerId, fragment);
        if (addToBackStack == true) {
            ft.addToBackStack(fragment.getClass().getSimpleName());
        }
        ft.commit();
    }

    public void replaceFragmentWithoutAnimation(int containerId, Fragment fragment, boolean addToBackStack) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(containerId, fragment);
        if (addToBackStack == true) {
            ft.addToBackStack(fragment.getClass().getSimpleName());
        }
        ft.commit();
    }

    public int getCountContentFragment() {
        return getSupportFragmentManager().getBackStackEntryCount();
    }

    public void popbackStack() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }
    }

}
