package sc.mvk.mskanmvagesmr.permiss.manager;

import android.content.Intent;
import androidx.fragment.app.Fragment;
import com.squareup.otto.Subscribe;
import sc.mvk.mskanmvagesmr.permiss.bus.ActivityResultBus;
import sc.mvk.mskanmvagesmr.permiss.bus.ActivityResultEvent;

public class NestedActivityResultFragment extends Fragment {

    @Override
    public void onStart() {
        super.onStart();
        ActivityResultBus.getInstance().register(mActivityResultSubscriber);
    }

    @Override
    public void onStop() {
        super.onStop();
        ActivityResultBus.getInstance().unregister(mActivityResultSubscriber);
    }

    private Object mActivityResultSubscriber = new Object() {
        @Subscribe
        public void onActivityResultReceived(ActivityResultEvent event) {
            int requestCode = event.getRequestCode();
            int resultCode = event.getResultCode();
            Intent data = event.getData();
            onActivityResult(requestCode, resultCode, data);
        }
    };
}