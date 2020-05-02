package sc.mvk.mskanmvagesmr.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;
import sc.mvk.mskanmvagesmr.R;
import sc.mvk.mskanmvagesmr.databinding.ActivityMainBinding;
import sc.mvk.mskanmvagesmr.manager.ManagerFragment;
import sc.mvk.mskanmvagesmr.view.fragment.CallFragment;
import sc.mvk.mskanmvagesmr.view.fragment.SettingFragment;
import sc.mvk.mskanmvagesmr.view.fragment.SmsFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ManagerFragment managerFragment;
    private ActivityMainBinding binding;
    private int KEY_MENU_BOTTOM = 3;
    private boolean doubleBackToExitPressedOnce = false;
    private String TAG_CALL = "TAG_CALL",TAG_SMS = "TAG_SMS",TAG_SETTING = "TAG_SETTING";

    //####################################################################################
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("KEY_MENU_BOTTOM",KEY_MENU_BOTTOM);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        KEY_MENU_BOTTOM = savedInstanceState.getInt("KEY_MENU_BOTTOM");
    }

    //####################################################################################
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        managerFragment = new ManagerFragment();
        if (savedInstanceState == null){
            onAddFragmentFirst();
        }
        onClickView();
        onSelectMenuBottom(KEY_MENU_BOTTOM);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.lnMenuCall){
            onDetachAttachFragment(1);
        }else if (view == binding.lnMenuSms){
            onDetachAttachFragment(2);
        }else if (view == binding.lnMenuSetting){
            onDetachAttachFragment(3);
        }
    }

    @Override
    public void onBackPressed() {
        if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this,getResources().getText(R.string.txt_exit_app), Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
        }
    }

    //####################################################################################
    private void onClickView(){
        binding.lnMenuCall.setOnClickListener(this);
        binding.lnMenuSms.setOnClickListener(this);
        binding.lnMenuSetting.setOnClickListener(this);
    }
    private void onSelectMenuBottom(int index) {
        binding.tvMenuCall.setTextColor(getResources().getColor(R.color.color_title_menu_bottom_u));
        binding.tvMenuSms.setTextColor(getResources().getColor(R.color.color_title_menu_bottom_u));
        binding.tvMenuSetting.setTextColor(getResources().getColor(R.color.color_title_menu_bottom_u));
        binding.imgMenuCall.setColorFilter(getResources().getColor(R.color.color_title_menu_bottom_u));
        binding.imgMenuSms.setColorFilter(getResources().getColor(R.color.color_title_menu_bottom_u));
        binding.imgMenuSetting.setColorFilter(getResources().getColor(R.color.color_title_menu_bottom_u));
        switch (index){
            case 1:
                binding.imgMenuCall.setColorFilter(getResources().getColor(R.color.color_title_menu_bottom_s));
                binding.tvMenuCall.setTextColor(getResources().getColor(R.color.color_title_menu_bottom_s));
                binding.tvTitleToolBar.setText(getResources().getString(R.string.title_bar_call));
                break;
            case 2:
                binding.tvTitleToolBar.setText(getResources().getString(R.string.title_bar_sms));
                binding.imgMenuSms.setColorFilter(getResources().getColor(R.color.color_title_menu_bottom_s));
                binding.tvMenuSms.setTextColor(getResources().getColor(R.color.color_title_menu_bottom_s));
                break;
            case 3:
                binding.tvTitleToolBar.setText(getResources().getString(R.string.title_bar_setting));
                binding.imgMenuSetting.setColorFilter(getResources().getColor(R.color.color_title_menu_bottom_s));
                binding.tvMenuSetting.setTextColor(getResources().getColor(R.color.color_title_menu_bottom_s));
                break;
        }
    }
    private void onAddFragmentFirst(){
        managerFragment.onAddDetachFragment(MainActivity.this, CallFragment.newInstance(),R.id.frameBody,TAG_CALL,true);
        managerFragment.onAddDetachFragment(MainActivity.this, SmsFragment.newInstance(),R.id.frameBody,TAG_SMS,true);
        managerFragment.onAddDetachFragment(MainActivity.this, SettingFragment.newInstance(),R.id.frameBody,TAG_SETTING,false);
    }
    private void onDetachAttachFragment(int index){
        KEY_MENU_BOTTOM = index;
        switch (index){
            case 1:
                managerFragment.onDetachAttachFragment(MainActivity.this, R.id.frameBody,TAG_CALL);
                break;
            case 2:
                managerFragment.onDetachAttachFragment(MainActivity.this, R.id.frameBody,TAG_SMS);
                break;
            case 3:
                managerFragment.onDetachAttachFragment(MainActivity.this, R.id.frameBody,TAG_SETTING);
                break;
        }
        onSelectMenuBottom(index);
    }
}
