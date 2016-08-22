package edu.hillel.testapp2;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListActivity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by MBCNEWMAIN on 21.08.2016.
 */
public class MyListFragment extends Fragment implements AdapterView.OnItemClickListener {

    public interface ListFragmentListener{
        void onItemClick(int pos, long id);
    }

    private ListFragmentListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ListFragmentListener)
            mListener = (ListFragmentListener) activity;
    }

    private ArrayAdapter<String> mAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,MainActivity.data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        ListView lv = (ListView)v.findViewById(R.id.lvList);
        lv.setAdapter(mAdapter);
        lv.setOnItemClickListener(this);
        return v;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
        mListener.onItemClick(i, id);
    }
}
