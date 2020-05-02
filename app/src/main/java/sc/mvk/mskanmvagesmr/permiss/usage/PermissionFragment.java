package sc.mvk.mskanmvagesmr.permiss.usage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.core.content.ContextCompat;

import sc.mvk.mskanmvagesmr.permiss.manager.StatedFragment;

/**
 * Created by MDev on 29/3/2561.
 */

public abstract class PermissionFragment extends StatedFragment {
    public static int STATUS_GRANTED = 1,STATUS_NO_GRANTED = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public abstract void onPermissionsGranted(int requestCode,int status);

    public void requestAppPermissions(Context mContext, final String[]requestedPermissions, /*final int stringId,*/ final int requestCode) {
         int permissionCheck = PackageManager.PERMISSION_GRANTED;
        boolean showRequestPermissions = false;
        for(String permission: requestedPermissions) {
            permissionCheck = permissionCheck + ContextCompat.checkSelfPermission(mContext, permission);
            showRequestPermissions = showRequestPermissions || shouldShowRequestPermissionRationale(permission);
        }

        if (permissionCheck!= PackageManager.PERMISSION_GRANTED) {
            if(showRequestPermissions) {//ทำงานครั้งต่อไปเมื่อไม่มีการอนุญาต
                //onPermissionsGranted(requestCode,STATUS_NO_GRANTED);
                requestPermissions(requestedPermissions, requestCode);
            } else {//ทำงานครั้งแรกครั้งเดียว
                //requestPermissions(requestedPermissions, requestCode);
                requestPermissions(requestedPermissions, requestCode);
            }
        } else {//ถ้าได้กดอนุญาติแล้ว
            onPermissionsGranted(requestCode,STATUS_GRANTED);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for(int permission : grantResults) {
            permissionCheck = permissionCheck + permission;
        }

        if( (grantResults.length > 0) && PackageManager.PERMISSION_GRANTED == permissionCheck) {//กดอนุญาต
            onPermissionsGranted(requestCode,STATUS_GRANTED);
        } else {//กดไม่อนุญาต
            onPermissionsGranted(requestCode,STATUS_NO_GRANTED);
            //Display message when contain some Dangerous permission not accept
        }
    }
}
/*
    ************ Usage **************
    1.onActivityResult for Nested Fragment
    In v0.10.0 onward, NestedActivityResultFragment is introduced to fix onActivityResult problem which
    couldn't be called on nested fragment. To use it, you have to override onActivityResult on your Activity and add a line of code:

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ActivityResultBus.getInstance().postQueue(new ActivityResultEvent(requestCode, resultCode, data));
    }

    ########################################################################

    2.And in your fragment, you need to call getActivity().startActivityForResult(...) but not
    startActivityForResult(...) since we need to let all the result sent to Activity. Lastly,
    override onActivityResult in your fragment in the standard way.

    public class MainFragment extends NestedActivityResultFragment {
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            // Add your code here
            Toast.makeText(getActivity(), "Fragment Got it: " + requestCode + ", " + resultCode, Toast.LENGTH_SHORT).show();
        }
    }
*/
