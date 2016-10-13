package com.store.storeapps.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.store.storeapps.R;
import com.store.storeapps.activities.HomeActivity;
import com.store.storeapps.customviews.CustomProgressDialog;
import com.store.storeapps.customviews.DialogClass;
import com.store.storeapps.utility.ApiConstants;
import com.store.storeapps.utility.Constants;
import com.store.storeapps.utility.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.LinkedHashMap;


/**
 * Created by Shankar.
 */
public class RegistrationFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = "RegistrationFragment";
    private HomeActivity mParent;
    private View rootView;
    private EditText inputName;
    private RadioButton inputMale;
    private RadioButton inputFemale;
    private EditText inputEmail;
    private EditText inputPassword;
    private RadioGroup gender;
    private EditText et;
    private Button btnRegisterUser;
    private Button btn_sign_up;
    private Calendar cal;
    private int day;
    private int month;
    private int year;
    private String mFrom = "";
    private TextView custom_toast;
    private View toastRoot;
    private View toastRoot2;
    private Toast toast;
    private RadioButton selectRadio;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (HomeActivity) getActivity();
        if (getArguments() != null) {
            mFrom = getArguments().getString("from");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_registration, container, false);
        // Call toast.xml file for toast layout
        toastRoot = inflater.inflate(R.layout.toast, null);
        toastRoot2 = inflater.inflate(R.layout.error_toast, null);
        initUI();
        return rootView;
    }

    private void initUI() {
        inputName = (EditText) rootView.findViewById(R.id.inputName);
        inputEmail = (EditText) rootView.findViewById(R.id.inputEmail);
        inputPassword = (EditText) rootView.findViewById(R.id.inputPassword);
        inputMale = (RadioButton) rootView.findViewById(R.id.inputMale);
        inputFemale = (RadioButton) rootView.findViewById(R.id.inputFemale);
        gender = (RadioGroup) rootView.findViewById(R.id.gender);
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        et = (EditText) rootView.findViewById(R.id.editText);
        btnRegisterUser = (Button) rootView.findViewById(R.id.btnRegisterUser);
        custom_toast = (TextView) toastRoot.findViewById(R.id.errortoast);
        et.setOnClickListener(this);
        selectRadio = (RadioButton)rootView.findViewById(gender
                .getCheckedRadioButtonId());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegisterUser:
                if (isValidFields()) {
                    CreateNewUser(inputName.getText().toString(),inputEmail.getText().toString(),inputPassword.getText().toString(),
                            selectRadio.getText().toString());
//                    (edt_email.getText().toString(), edt_password.getText().toString());
                }
                break;
            case R.id.btn_sign_up:

                break;
        }
    }

    private void CreateNewUser(String name, String emailid, String password, String gender) {
        if (Utility.isNetworkAvailable(getActivity())) {
            new PostRegisternAsyncTask(name, emailid, password, gender).execute();
        } else {
            DialogClass.createDAlertDialog(getActivity(), "The Internet connection appears to be offline.");
        }
    }


    class PostRegisternAsyncTask extends AsyncTask<String, String, String> {
        private CustomProgressDialog mCustomProgressDialog;
        private String name;
        private String password;
        private String emailid;
        private String gender;

        public PostRegisternAsyncTask(String name, String emailid, String password, String gender) {
            mCustomProgressDialog = new CustomProgressDialog(getActivity());
            this.name = name;
            this.emailid = emailid;
            this.password = password;
            this.gender = gender;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mCustomProgressDialog.showProgress(Utility.getResourcesString(getActivity(), R.string.please_wait));

        }

        @Override
        protected String doInBackground(String... params) {
            String result = null;
            try {
                LinkedHashMap<String, String> paramsList = new LinkedHashMap<String, String>();
                paramsList.put("email", emailid);
                paramsList.put("fullname", name);
                paramsList.put("password", password);
                paramsList.put("gender", gender);
                paramsList.put("cartId", HomeActivity.mCartId);
                result = Utility.httpPostRequestToServer(ApiConstants.REGISTER, Utility.getParams(paramsList));
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            try {
                if (response != null) {
                    JSONObject jsonobject = new JSONObject(response);
                    Log.d("Create Response", jsonobject.toString());
//                    if (jsonobject.optString("success").equalsIgnoreCase("1")) {
//                        Utility.setSharedPrefStringData(getActivity(), Constants.USER_ID, jsonobject.optString("ID"));
//                        Utility.setSharedPrefStringData(getActivity(), Constants.USER_LOGIN_COUNT, jsonobject.optString("count"));
//                        Utility.setSharedPrefStringData(getActivity(), Constants.USER_CASH, jsonobject.optString("cash"));
//                        JSONObject userjsonobject = jsonobject.optJSONObject("user");
//                        Utility.setSharedPrefStringData(getActivity(), Constants.USER_NAME, userjsonobject.optString("fullname"));
//                        Utility.setSharedPrefStringData(getActivity(), Constants.USER_EMAIL_ID, userjsonobject.optString("email"));
//                        Utility.setSharedPrefStringData(getActivity(), Constants.USER_FB_ID, userjsonobject.optString("fb_ID"));
//                        HomeActivity.txt_email.setText(userjsonobject.optString("email"));
//                        HomeActivity.txt_user_name.setText(userjsonobject.optString("fullname"));
//                        if (!mFrom.equalsIgnoreCase("cart")) {
//                            mParent.onBackPressed();
//                        }
//                    } else {
//                        Utility.showToastMessage(getActivity(), jsonobject.optString("message"));
//                    }
                }
                mCustomProgressDialog.dismissProgress();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isValidFields() {
        boolean isValidate = false;
        if ((inputEmail.getText().toString().length() < 1) &&
                (inputName.getText().toString().length() < 1) &&
                (inputPassword.getText().toString().length() < 8) &&
                (gender.getCheckedRadioButtonId() == -1)) {
            custom_toast.setText("Please enter fields");
            toast.setView(toastRoot);
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL | Gravity.FILL_HORIZONTAL, 0, 80);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
            return isValidate;
        } else if (inputName.getText().toString().length() < 1) {
            custom_toast.setText("Please enter Name");
            toast.setView(toastRoot);
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL | Gravity.FILL_HORIZONTAL, 0, 80);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
            return isValidate;
        } else if (gender.getCheckedRadioButtonId() == -1) {
            custom_toast.setText("Please select gender");
            toast.setView(toastRoot);
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL | Gravity.FILL_HORIZONTAL, 0, 80);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
            return isValidate;
        } else if ((inputEmail.getText().toString().length() < 1)) {
            custom_toast.setText("Please enter Email id");
            toast.setView(toastRoot);
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL | Gravity.FILL_HORIZONTAL, 0, 80);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
            return isValidate;
        } else if (!inputEmail.getText().toString().trim().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z]+)*(\\.[A-Za-z]{2,})$")) {
            custom_toast.setText("Please Enter Valid Email Id");
            toast.setView(toastRoot);
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL | Gravity.FILL_HORIZONTAL, 0, 80);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
            return isValidate;
        } else if (inputPassword.getText().toString().length() < 1) {
            custom_toast.setText("Please enter password");
            toast.setView(toastRoot);
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL | Gravity.FILL_HORIZONTAL, 0, 80);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
            return isValidate;
        } else if (inputPassword.getText().toString().length() < 8) {
            custom_toast.setText("Password must be 8 characters");
            toast.setView(toastRoot);
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL | Gravity.FILL_HORIZONTAL, 0, 80);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
            return isValidate;
        }else {

        }


        return isValidate;
    }
}