package com.adev.android.legomindfuck;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class ShowDialogMessage extends DialogFragment   {

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

        AlertDialog alert = builder.create();
        alert.show();
        return alert;

    }

}
