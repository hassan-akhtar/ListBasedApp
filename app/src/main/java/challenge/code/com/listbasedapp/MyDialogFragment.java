package challenge.code.com.listbasedapp;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyDialogFragment extends DialogFragment {

    EditText etFirstName, etLastName;
    Button addBtn;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dialog, container, false);
        getDialog().setTitle(this.getResources().getString(R.string.title_dialog));

        initViews();
        initListeners();

        return rootView;
    }


    private void initViews() {
        etFirstName = (EditText) rootView.findViewById(R.id.etFirstName);
        etLastName = (EditText) rootView.findViewById(R.id.etLastName);
        addBtn = (Button) rootView.findViewById(R.id.addBtn);

    }


    private void initListeners() {
        addBtn.setOnClickListener(mGlobal_OnClickListener);
    }


    // Global listners for all views
    final View.OnClickListener mGlobal_OnClickListener = new View.OnClickListener() {
        public void onClick(final View v) {
            switch (v.getId()) {
                case R.id.addBtn: {
                    if (validateData()) {
                        dismiss();
                        Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.msg_toast_name_added), Toast.LENGTH_LONG).show();
                        MainActivity.items.add(MainActivity.capitalizeFirstLetter(etLastName.getText().toString().trim()) + getActivity().getResources().getString(R.string.str_separator) + MainActivity.capitalizeFirstLetter(etFirstName.getText().toString().trim()));
                        etFirstName.setText(getActivity().getResources().getString(R.string.empty_string));
                        etLastName.setText(getActivity().getResources().getString(R.string.empty_string));
                        MainActivity.recyclerViewAdapter.notifyDataSetChanged();
                    }

                    break;
                }
            }
        }

    };

    private boolean validateData() {
        if (this.getResources().getString(R.string.empty_string).equals(etFirstName.getText().toString().trim())) {
            Toast.makeText(getActivity(), this.getResources().getString(R.string.error_firstname), Toast.LENGTH_LONG).show();
        } else if (this.getResources().getString(R.string.empty_string).equals(etLastName.getText().toString().trim())) {
            Toast.makeText(getActivity(), this.getResources().getString(R.string.error_lastname), Toast.LENGTH_LONG).show();
        } else {
            return true;
        }

        return false;
    }
}
