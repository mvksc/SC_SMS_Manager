package sc.mvk.mskanmvagesmr.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sc.mvk.mskanmvagesmr.R;
import sc.mvk.mskanmvagesmr.databinding.FragmentSmsBinding;
import sc.mvk.mskanmvagesmr.permiss.usage.PermissionFragment;

public class SmsFragment extends PermissionFragment {
    private FragmentSmsBinding binding;

    public SmsFragment() {}

    public static SmsFragment newInstance() {
        SmsFragment fragment = new SmsFragment();
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
        binding = FragmentSmsBinding.bind(inflater.inflate(R.layout.fragment_sms, container, false));
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
