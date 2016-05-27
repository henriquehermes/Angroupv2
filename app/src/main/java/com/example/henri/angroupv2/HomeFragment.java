package com.example.henri.angroupv2;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {
    private Button botao;
    private Button botao1;
    private Button botao2;
    private Button botao3;
    private ImageView profile_pic = null;
    private TextView tv = null;
    private Button logoutButton = null;
    private Profile profile = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment, container, false);
        profile_pic = (ImageView) view.findViewById(R.id.profile_pic);
        tv = (TextView) view.findViewById(R.id.tv_name);
        logoutButton = (Button) view.findViewById(R.id.logout_button);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();

        if (bundle != null) {
            profile = (Profile) bundle.getParcelable(LoginFragment.PARCEL_KEY);
        } else {
            profile = Profile.getCurrentProfile();
        }


        tv.setText("Bem Vindo(a) \n" + profile.getName());

        botao = (Button) getView().findViewById(R.id.btnCalen);
        botao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(getActivity(), CalendarioActivity.class);
                getActivity().startActivity(i);
            }
        });
        botao1 = (Button) getView().findViewById(R.id.btnHora);
        botao1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(getActivity(), HorariosActivity.class);
                getActivity().startActivity(i);
            }
        });
        botao2 = (Button) getView().findViewById(R.id.btnNotas);
        botao2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(getActivity(), NotasActivity.class);
                getActivity().startActivity(i);
            }
        });
        botao3 = (Button) getView().findViewById(R.id.btnTarefa);
        botao3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(getActivity(), TarefaActivity.class);
                getActivity().startActivity(i);
            }
        });


        Picasso.with(getActivity())
                .load(profile.getProfilePictureUri(100, 100).toString())
                .into(profile_pic);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

    }



    private void logout() {
        LoginManager.getInstance().logOut();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainContainer , new com.facebook.login.LoginFragment());
        fragmentTransaction.commit();
    }


}

