package com.example.hackeru.dialogfragmentexample;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by hackeru on 20/08/2015.
 */
public class NoticeDialogFragment extends DialogFragment {

    interface NoticeDialogFragmentListener {
        void onNoticePositiveClicked(DialogFragment dialog);
        void onNoticeNegativeClicked(DialogFragment dialog);
    }

    NoticeDialogFragmentListener mCallBack;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallBack = (NoticeDialogFragmentListener)activity;
        }catch (ClassCastException ex) {
            throw new ClassCastException(activity.toString() + " must implement NoticeDialogFragmentListner");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("hello!!!").setPositiveButton("OKOKOK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mCallBack.onNoticePositiveClicked(NoticeDialogFragment.this);
            }
        }).setNegativeButton("NONNONONON", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mCallBack.onNoticeNegativeClicked(NoticeDialogFragment.this);
            }
        });
        return builder.create();
    }
}
