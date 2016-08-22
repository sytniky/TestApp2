package edu.hillel.testapp2;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by MBCNEWMAIN on 21.08.2016.
 */
public class DescFragment extends Fragment {

    private static final String ARG_POS = "pos";
    private static final String ARG_ID = "id";
    private long mId;
    private int mPos;
    private TextView tv;

    public static DescFragment newInstance(int pos, long id) {

        Bundle args = new Bundle();
        args.putInt(ARG_POS, pos);
        args.putLong(ARG_ID, id);
        DescFragment fragment = new DescFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null) {
            mId = getArguments().getLong(ARG_ID);
            mPos = getArguments().getInt(ARG_POS);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_desc, container, false);
        tv = (TextView)v.findViewById(R.id.tvText);
        changeDesc(mPos, mId);
        return v;
    }

    public void changeDesc(int pos, long id){
        tv.setText(pos + " - " + id + " - " + MainActivity.data[pos]);
    }
}
