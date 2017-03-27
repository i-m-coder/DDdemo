package com.dddemo.sumanthmadala.androidapp.dddemo.ui.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dddemo.sumanthmadala.androidapp.dddemo.R;
import com.dddemo.sumanthmadala.androidapp.dddemo.ui.fragments.BaseFragment;
import com.dddemo.sumanthmadala.androidapp.dddemo.ui.fragments.RestaurantsFragment;

public class DDmainActivity extends AppCompatActivity {


    private ProgressDialog dialog = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddmain);
        showRestaurants();
    }


    public void showRestaurants(){
        FragmentManager fm = this.getSupportFragmentManager();
        RestaurantsFragment restaurantsFragment = RestaurantsFragment.getInstance(fm);
        fm.beginTransaction().add(R.id.fr_container,restaurantsFragment,restaurantsFragment.TAG)
                .addToBackStack(restaurantsFragment.TAG).commit();

    }


    public void startProgressDialog(final String str, DialogInterface.OnCancelListener listener) {

            try {
                if (dialog != null) {
                    try {
                        dialog.dismiss();
                    } catch (Exception e) {
                    }
                    dialog = null;
                }

                dialog = ProgressDialog.show(this, "", str, true);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(false);
                if(listener != null) {
                    dialog.setOnCancelListener(listener);
                }

            } catch (Exception e) {
            }
        }




    public void endProgressDialog() {
        if (dialog != null) {
            try {
                dialog.dismiss();
            } catch (Exception e) {
            }
            dialog = null;
        }

    }

    @Override
    public void onBackPressed() {
       FragmentManager fm =  this.getSupportFragmentManager();
        if(fm.getBackStackEntryCount()==0){
            this.finish();
        }else {
            int index =  fm.getBackStackEntryCount() - 1;
            FragmentManager.BackStackEntry backEntry = fm.getBackStackEntryAt(index);
            Fragment fragment = fm.findFragmentByTag(backEntry.getName());
            if(fragment instanceof BaseFragment){
                 if(((BaseFragment) fragment).onBackPressed()){
                     return;
                 }
            }
        }
        super.onBackPressed();
    }
}
