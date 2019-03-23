package com.framgia.foodapp.screen.Login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.framgia.foodapp.screen.ListOrder.FragmentListBills;
import com.framgia.foodapp.R;
import com.framgia.foodapp.utils.Navigator;

public class FragmentLogin extends Fragment implements View.OnClickListener {
    private EditText mEditTextUserName, mEditTextPassWord;
    private Navigator mNavigator;

    public FragmentLogin() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View view) {
        mNavigator = new Navigator();
        mEditTextUserName = view.findViewById(R.id.editTextUserName);
        mEditTextPassWord = view.findViewById(R.id.editTextPassWord);
        view.findViewById(R.id.buttonDangNhap).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonDangNhap:
                FragmentListBills fragmentListBills = FragmentListBills.newInstance(
                    mEditTextUserName.getText().toString());
                mNavigator.addFragment(getActivity(), fragmentListBills,
                    R.id.frameContainer);
                break;
        }
    }
}
