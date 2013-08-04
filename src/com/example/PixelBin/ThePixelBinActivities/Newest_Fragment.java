package com.example.PixelBin.ThePixelBinActivities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockFragment;
import com.example.PixelBin.R;
import com.origamilabs.library.views.StaggeredGridView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import de.neofonie.mobile.app.android.widget.crouton.Crouton;
import de.neofonie.mobile.app.android.widget.crouton.Style;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 8/3/13
 * Time: 4:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class Newest_Fragment extends SherlockFragment {
    View mainView;
    StaggeredGridView gridView;
    TextView rTextView;
    TextView gTextView;
    TextView bTextView;
    StaggeredAdapter staggeredAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)  {
//        getSherlockActivity().setSupportProgressBarIndeterminateVisibility(true);
        return inflater.inflate(R.layout.staggered_image_loaders, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        mainView = getView();
        gridView = (StaggeredGridView) mainView.findViewById(R.id.staggeredGridView_staggeredLoader);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Pictures");
        query.setLimit(10);
        query.orderByDescending("CurrentTimeMillis");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if(e==null) {
                    staggeredAdapter = new StaggeredAdapter(getActivity(),R.layout.loading_image,parseObjects);
                    gridView.setAdapter(staggeredAdapter);
                    //Hooray! data collected
                } else Crouton.makeText(getActivity(), "Unable to Download Data", Style.ALERT).show();
            }
        });
    }


}
