package com.ekaitz_torregrosa.memoryapp.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.ekaitz_torregrosa.memoryapp.R;

public class UserNameDialog extends AppCompatDialogFragment {
    private EditText editTextUserName;
    private UserNameDialogListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view)
                .setTitle(getResources().getString(R.string.nombreusuario))
                .setPositiveButton(getResources().getString(R.string.aceptarDialog), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String username = editTextUserName.getText().toString();
                        listener.applyText(username);
                    }
                });

        editTextUserName = view.findViewById(R.id.editTextTextUserName);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (UserNameDialogListener) context;
    }

    public interface UserNameDialogListener{
        void applyText(String username);
    }
}
