package studbud.aether.com.stadbad7;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MapFragment extends Fragment implements  OnMapReadyCallback {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    GoogleMap mMap;
    Button openNavBar,showList;
    Dialog popupInfo ;
    FloatingSearchView floatingSearchView;
    DrawerLayout drawer;
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    public MapFragment() {
        // Required empty public constructor
    }

    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
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
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GoogleMap mMap;
        Button openNavBar,showList;
        Dialog popupInfo ;
        FloatingSearchView floatingSearchView;


          //   SupportMapFragment mapFragment = (SupportMapFragment)getActivity(). getSupportFragmentManager(). findFragmentById(R.id.map);
       // mapFragment.getMapAsync(this);

                drawer =  getView().findViewById(R.id.drawer_layout);
             popupInfo = new Dialog(getActivity().getApplicationContext());
            popupInfo.getWindow().requestFeature(Window.FEATURE_NO_TITLE);


            openNavBar = getView().findViewById(R.id.openNavBar);
            showList = getView().findViewById(R.id.showList);

            floatingSearchView = getView().findViewById(R.id.floating_search_view);
            floatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
                @Override
                public void onSearchTextChanged(String oldQuery, final String newQuery) {

                    //get suggestions based on newQuery

                    //pass them on to the search view
                    //floatingSearchView.swapSuggestions(newSuggestions);
                }
            });
            floatingSearchView.setOnLeftMenuClickListener(new FloatingSearchView.OnLeftMenuClickListener() {
                @Override
                public void onMenuOpened() {
                    Toast.makeText(getActivity().getApplicationContext(), "imo mama hamburger", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onMenuClosed() {

                }
            });
            showList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity().getApplicationContext(), "imo mama hamburger", Toast.LENGTH_SHORT).show();
                    // popupInfo.show();
                }
            });

            openNavBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drawer.openDrawer(Gravity.START);


                }
            });


            floatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
                @Override
                public void onSearchTextChanged(String oldQuery, final String newQuery) {

                    //get suggestions based on newQuery

                    //pass them on to the search view
                    ArrayList<SearchSuggestion> searchSugg = new ArrayList< >();
                    //  floatingSearchView.swapSuggestions(  new ArrayList<SearchSuggestion>()));
                }
            });

        }
    @Override
    public void onMapReady(GoogleMap googleMap) {

        double lat = 37.468319;
        double lon = -122.143936;
        mMap = googleMap;
        MarkerOptions mo = new MarkerOptions();
        mo.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)).alpha(0.8f).position(new LatLng(lat, lon)).snippet("IMOMMA"    );

            mMap.addMarker(mo);
            float zoomLevel = 18f; //This goes up to 21
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lon), zoomLevel));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                showPopupInfo();
                return  true;
            }
        });

    }
    public void HideKeyboard()
    {
        hideKeyboard(getActivity());
    }
    public static void hideKeyboard(Activity activity)
    {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    public String getAddress(Context context, double lat, double lng) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);

            String add = obj.getAddressLine(0);


            return add;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }
    public void showPopupInfo(   )
    {
        popupInfo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupInfo.setContentView(R.layout.info_popup);
        popupInfo.show();

    }





}
