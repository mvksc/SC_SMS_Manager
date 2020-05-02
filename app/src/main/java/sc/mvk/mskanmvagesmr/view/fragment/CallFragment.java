package sc.mvk.mskanmvagesmr.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sc.mvk.mskanmvagesmr.R;
import sc.mvk.mskanmvagesmr.databinding.FragmentCallBinding;
import sc.mvk.mskanmvagesmr.permiss.usage.PermissionFragment;

public class CallFragment extends PermissionFragment {
    private FragmentCallBinding binding;

    public CallFragment() {}

    public static CallFragment newInstance() {
        CallFragment fragment = new CallFragment();
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
        binding = FragmentCallBinding.bind(inflater.inflate(R.layout.fragment_call, container, false));
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
