package com.dddemo.sumanthmadala.androidapp.dddemo.ui.fragments;

import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dddemo.sumanthmadala.androidapp.dddemo.R;
import com.dddemo.sumanthmadala.androidapp.dddemo.model.Restaurant;
import com.dddemo.sumanthmadala.androidapp.dddemo.net.NetUtils;
import com.dddemo.sumanthmadala.androidapp.dddemo.ui.activities.DDmainActivity;
import com.dddemo.sumanthmadala.androidapp.dddemo.ui.adaptors.RestaurantsAdaptor;
import com.dddemo.sumanthmadala.androidapp.dddemo.util.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RestaurantsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RestaurantsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RestaurantsFragment extends BaseFragment {



    public static final String TAG =  RestaurantsFragment.class.getSimpleName();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
     // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
     @BindView(R.id.restaurants_view)
     RecyclerView restaurantsList;
    @BindView(R.id.empty_list)
    TextView emptyList;
    private Unbinder unbinder;
    private Subscription subscription;

    private OnFragmentInteractionListener mListener;

    public RestaurantsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RestaurantsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RestaurantsFragment newInstance(String param1, String param2) {
        RestaurantsFragment fragment = new RestaurantsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.restaurants, container, false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(mainActivity==null){
            mainActivity =  (DDmainActivity) this.getActivity();
        }
        displayRestaurantsList(Constants.LATITUDE,Constants.LONGITUDE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if(subscription!=null && !subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



    public static RestaurantsFragment getInstance(FragmentManager fm) {
        RestaurantsFragment fragment = (RestaurantsFragment) fm.findFragmentByTag(RestaurantsFragment.TAG);
        if (fragment == null) {
            fragment = new RestaurantsFragment();
        }
        return fragment;
    }

    void displayRestaurantsList(final String lat, final String lng) {
        mainActivity.startProgressDialog("Fetching Restaurants..", null);
        subscription = NetUtils.getInstance()
                .fetchRestaurantsList(lat, lng)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Restaurant>>() {
                    @Override
                    public void onCompleted() {
                        log( "onCompleted"  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        log( "onError"  );
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<Restaurant> items) {
                        log( "onNext"  );

                        if(items!=null && items.size()>0) {
                            emptyList.setVisibility(View.GONE);
                            restaurantsList.setVisibility(View.VISIBLE);
                            populateRestaurantsList(items);
                        }else{
                            restaurantsList.setVisibility(View.GONE);
                            emptyList.setVisibility(View.VISIBLE);
                        }
                        mainActivity.endProgressDialog();
                    }
                });

    }

    private void populateRestaurantsList(List<Restaurant> items){
        RestaurantsAdaptor restaurantsAdaptor = new RestaurantsAdaptor(mainActivity.getApplicationContext(),items);
        LinearLayoutManager llm = new LinearLayoutManager(mainActivity.getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        restaurantsList.setLayoutManager(llm);
        restaurantsList.setHasFixedSize(true);
        restaurantsList.setAdapter(restaurantsAdaptor);

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public boolean onBackPressed() {
        mainActivity.finish();
        return super.onBackPressed();
    }


    private void log(String msg){
        Log.v(TAG,msg);
    }
}
