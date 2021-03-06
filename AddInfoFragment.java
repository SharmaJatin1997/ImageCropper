package com.services.services.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.omninos.util_data.CommonUtils;
import com.services.services.ModelClass.RegisterPojo;
import com.services.services.Utils.App;
import com.services.services.Utils.Constants;
import com.services.services.Utils.MyMVVM;
import com.services.services.activity.HomeActivity2;
import com.services.services.R;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


public class AddInfoFragment extends Fragment implements View.OnClickListener {

    View view;
    private RelativeLayout topbar;
    private CircleImageView user_img;
    private EditText et_address, et_email, et_name;
    private Spinner spinner_gender;
    private String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+";
    private String name, email, address, gender, image, selectGender, id;
    private MyMVVM myMVVM;
    String imagePath;
    Uri imageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_info, container, false);
        myMVVM = ViewModelProviders.of(AddInfoFragment.this).get(MyMVVM.class);
        id = App.getSharedpref().getString(Constants.USER_ID);
        findIDs();
        setup();
        return view;
    }

    private void setup() {
        spinner_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gender = spinner_gender.getSelectedItem().toString();
                Toast.makeText(getActivity(), gender, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void findIDs() {
        user_img = view.findViewById(R.id.user_img);
        et_address = view.findViewById(R.id.et_address);
        et_email = view.findViewById(R.id.et_email);
        et_name = view.findViewById(R.id.et_name);
        spinner_gender = view.findViewById(R.id.spinner_gender);
        user_img.setOnClickListener(this);
        view.findViewById(R.id.submitBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submitBtn:
                name = et_name.getText().toString();
                email = et_email.getText().toString();
                address = et_address.getText().toString();
                if (name.isEmpty()) {
                    et_name.setError("please enter the valid name");
                    et_name.requestFocus();
                } else if (email.isEmpty()) {
                    et_email.setError("please enter your email");
                    et_email.requestFocus();
                } else if (!email.matches(emailpattern)) {
                    et_email.setError("please enter valid email");
                    et_email.requestFocus();
                } else if (address.isEmpty()) {
                    et_address.setError("please enter your address");
                    et_address.requestFocus();
                } else {
                    UpdateDetail();
                }
                break;
            case R.id.user_img:
                ImagePicker.Companion.with(AddInfoFragment.this)
                        .crop()
                        .compress(1024)//Crop image(Optional), Check Customization for more option
                        .maxResultSize(500, 500)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        user_img.setImageURI(data.getData());
        imagePath = data.getData().getPath();
        Toast.makeText(getActivity(), imagePath, Toast.LENGTH_SHORT).show();
    }

    private void UpdateDetail() {
        myMVVM.updateUserInfoRegister(getActivity(), CommonUtils.getRequestBodyText(id), CommonUtils.getRequestBodyText(name), CommonUtils.getRequestBodyText(email), CommonUtils.getRequestBodyText(gender), CommonUtils.getRequestBodyText(address), CommonUtils.getImgdData(imagePath, "image")).observe(getViewLifecycleOwner(), new Observer<RegisterPojo>() {
            @Override
            public void onChanged(RegisterPojo registerPojo) {
                if (registerPojo.getSuccess().equalsIgnoreCase("1")) {
                    App.getSharedpref().saveString(Constants.User_Login, registerPojo.getDetails().getImage());
                    App.getSharedpref().saveString(Constants.USER_Login_STATUS, "1");
                    App.getSharedpref().saveModel(Constants.User_Register, registerPojo);
                    Toast.makeText(getActivity(), "info updated successfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getActivity(), HomeActivity2.class);
                    startActivity(i);
                    Toast.makeText(getActivity(), "Login Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "info updated failed..", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}