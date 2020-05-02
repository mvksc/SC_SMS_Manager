package sc.mvk.mskanmvagesmr.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sc.mvk.mskanmvagesmr.R;
import sc.mvk.mskanmvagesmr.databinding.FragmentSettingBinding;
import sc.mvk.mskanmvagesmr.permiss.usage.PermissionFragment;

public class SettingFragment extends PermissionFragment {
    private FragmentSettingBinding binding;

    public SettingFragment() {}

    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onPermissionsGranted(int requestCode, int status) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingBinding.bind(inflater.inflate(R.layout.fragment_setting, container, false));
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
