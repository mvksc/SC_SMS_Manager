package sc.mvk.mskanmvagesmr.manager;


import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ManagerFragment {
    public void onAddDetachFragment(Context mContext, Fragment fragment, int frameLayoutId, String tag, boolean isDetach){
        FragmentTransaction transaction = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
        transaction.add(frameLayoutId, fragment,tag);
        if (isDetach){
            transaction.detach(fragment);
        }
        transaction.commit();
    }

    public void onDetachAttachFragment(Context mContext, int frameLayoutId, String tag){
        if (!(getTagFragmentIsShow(mContext,frameLayoutId).trim().equals(tag))){
            Fragment fAttach = ((FragmentActivity) mContext).getSupportFragmentManager().findFragmentByTag(tag);
            ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction()
                    //.setCustomAnimations(R.anim.anim_ac_in, R.anim.anim_ac_out)
                    .detach(getFragmentIsShow(mContext,frameLayoutId))
                    .attach(fAttach)
                    .commit();
        }
    }

    private Fragment getFragmentIsShow(Context mContext, int frameLayoutId) {
        FragmentManager fm = ((FragmentActivity) mContext).getSupportFragmentManager();
        Fragment f = fm.findFragmentById(frameLayoutId);
        if (f != null) {
            return f;
        }
        return null;
    }

    private String getTagFragmentIsShow(Context mContext, int frameLayoutId){
        String strTag = "";
        FragmentManager fm = ((FragmentActivity) mContext).getSupportFragmentManager();
        Fragment f = fm.findFragmentById(frameLayoutId);
        if (f != null) {
            strTag = f.getTag();
        }else {
            strTag = "";
        }
        return strTag;
    }
}
