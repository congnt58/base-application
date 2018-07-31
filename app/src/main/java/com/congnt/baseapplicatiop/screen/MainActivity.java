package com.congnt.baseapplicatiop.screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.congnt.baseapplicatiop.R;
import com.congnt.baseapplicatiop.base.BaseActivity;
import com.congnt.baseapplicatiop.base.BasePresenter;
import com.congnt.baseapplicatiop.base.BasePresenterImpl;
import com.congnt.baseapplicatiop.base.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<BasePresenter> implements BaseView<BasePresenter> {
    @BindView(R.id.edtTest)
    EditText edtTest;

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public BasePresenter onCreatePresenter() {
        return new BasePresenterImpl<>(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onPrepareLayout() {

    }

    @OnClick(R.id.btnHiden)
    public void clickHiden(){
        hideKeyboard();
    }

    @OnClick(R.id.btnShow)
    public void clickShow(){
        showSoftKeyBoard(edtTest);
    }

    @Override
    public void onRequestError(int errorCode, String message) {

    }

    @Override
    public void showProgress(int notifyId) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onNoConnection() {

    }

    @Override
    public void onNoApiUrlRestoreData() {

    }
}
