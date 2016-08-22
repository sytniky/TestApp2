package edu.hillel.testapp2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements
        MyListFragment.ListFragmentListener {

    private static final String SAVE_NAME_POS = "selected_pos";
    private int mSelectedPos = 0;
    public static String[] data = {"1", "2", "3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        if (savedInstanceState != null)
            mSelectedPos = savedInstanceState.getInt(SAVE_NAME_POS, 0);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            getFragmentManager().beginTransaction()
                    .replace(R.id.flContainer, new MyListFragment(), "list")
                    .commit();
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.flList, new MyListFragment(), "list")
                    .replace(R.id.flDesc, DescFragment.newInstance(mSelectedPos, mSelectedPos), "desc_fr")
                    .commit();
        }
    }

    @Override
    public void onItemClick(int pos, long id) {
        mSelectedPos = pos;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            getFragmentManager().beginTransaction()
                    .replace(R.id.flContainer, DescFragment.newInstance(pos, id))
                    .addToBackStack("desc_tr")
                    .commit();
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Fragment fr = getFragmentManager().findFragmentByTag("desc_fr");
            if (fr!=null){
                ((DescFragment)fr).changeDesc(pos,id);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVE_NAME_POS, mSelectedPos);
    }

}
