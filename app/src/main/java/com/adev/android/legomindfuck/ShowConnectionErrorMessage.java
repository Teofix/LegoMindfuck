package com.adev.android.legomindfuck;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.adev.android.legomindfuck.Activity.ConnectionTestActivity;

public class ShowConnectionErrorMessage extends DialogFragment   {

    private SysMessage mex;

    public void setContent(String title, String description)   {
        mex = new SysMessage();
        mex.setText(title);
        mex.setDescription(description);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(mex.getDescription());
        builder.setTitle(mex.getText());
        builder.setIcon(R.drawable.baseline_warning_black);
        builder.setCancelable(true);

        builder.setNegativeButton("Scusa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Connection settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(getContext(), ConnectionTestActivity.class);
                startActivity(i);
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
        return alert;

    }

}
