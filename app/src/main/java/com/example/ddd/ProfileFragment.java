package com.example.ddd;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ddd.Base.BaseFragment;
import com.example.ddd.R;
import com.google.android.material.textfield.TextInputLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends BaseFragment implements View.OnClickListener {

    TextView profileCancelText, profileEditText;
    ImageView profileBackground;
    CircleImageView profileImage;
    EditText profileUserNameEdit, profileEmailEdit, profilePhoneEdit;
    Button changePasswordBtn;


    Dialog dialog;

    public ProfileFragment() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_profile, container, false);
        initView(view);
        getActivity().setTitle("Profile");
        profileCancelText.setVisibility(View.GONE);

        return view;
    }

    public void initView(View view) {
        //profileToolbar = view.findViewById(R.id.profileToolbar);
        profileBackground = view.findViewById(R.id.profileBackground);
        profileImage = view.findViewById(R.id.profileImage);
        profileCancelText = view.findViewById(R.id.profileCancelText);
        profileEditText = view.findViewById(R.id.profileEditText);
        //iconback = view.findViewById(R.id.iconBack);
        //userNameText = view.findViewById(R.id.profileUserNameText);
        profileUserNameEdit = view.findViewById(R.id.profileUserNameEdit);
        //emailText = view.findViewById(R.id.profileEmailText);
        profileEmailEdit = view.findViewById(R.id.profileEmailEdit);
        //phoneText = view.findViewById(R.id.profilePhoneText);
        profilePhoneEdit = view.findViewById(R.id.profilePhoneEdit);
        changePasswordBtn = view.findViewById(R.id.changePasswordBtn);
        changePasswordBtn.setOnClickListener(this);
        profileEditText.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.changePasswordBtn){
            dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.change_password_layout);

            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //dialog.getWindow().setGravity(Gravity.CENTER);
            dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
            dialog.setCancelable(false);
            dialog.show();

            Button saveButton = dialog.findViewById(R.id.saveButton);
            Button cancelButton = dialog.findViewById(R.id.cancelButton);

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
        }
        else if (view.getId() == R.id.profileEditText){
            profileCancelText.setVisibility(View.VISIBLE);
            profileEditText.setText("Save");

        }

    }

}