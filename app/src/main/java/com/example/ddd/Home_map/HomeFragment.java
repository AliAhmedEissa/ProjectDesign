package com.example.ddd.Home_map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.ddd.Base.BaseFragment;
import com.example.ddd.R;

import java.util.ArrayList;

import static com.example.ddd.MainActivity.bottomNavigation;

public class HomeFragment extends BaseFragment {

    CardView Search_btn;
    View view;
    TextView addMore;
    LinearLayout linearLayout;
    Home_Map_VM Vm;
    ArrayList<String> textList ;
    ArrayList<String> numberlist ;
    int num;

    RadioGroup radioGroup;
    RadioButton radioButton;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Vm = new ViewModelProvider(getActivity()).get(Home_Map_VM.class);
        view = inflater.inflate(R.layout.fr_home, container, false);

        init();

        RestoreData();

        return view;
    }

    private void RestoreData() {
        if (Vm.first.getValue() == 0) {
            InflateLayout("", 0,false);
            Vm.first.setValue(1);
        }

        if (Vm.Text.getValue() != null && Vm.num.getValue() != null ) {
            InflateLayout(Vm.Text.getValue().get(0),Integer.parseInt(Vm.num.getValue().get(0)), false);
            for (int i = 1; i < Vm.Text.getValue().size(); i++) {
                InflateLayout(Vm.Text.getValue().get(i),Integer.parseInt(Vm.num.getValue().get(i)), true);
            }
        }
    }

    private void init() {
        Search_btn = view.findViewById(R.id.Search_btn);
        linearLayout = view.findViewById(R.id.linearItem);
        addMore = view.findViewById(R.id.add_more);
        radioGroup = view.findViewById(R.id.radioGroup);

        Search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressDialog("Loading...");
                SaveDataToMutable();
                Vm.CreateOrder();
            }
        });

        Vm.Loading.observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    hideProgressDialog();
                }
            }
        });

        addMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InflateLayout("", 0,true);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                findRadioButton(i);

            }
        });
    }

    public void findRadioButton(int radioId) {
        switch (radioId) {
            case R.id.radioButton_current:
                // make
                break;
            case R.id.radioButton_other:
                Vm.radio.setValue(true);
                goToFragment(new MapFragment());
                bottomNavigation.show(2, false);
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        SaveDataToMutable();
    }

    private void InflateLayout(String data , int numb , Boolean Visability)  {
        final View viewItem = getLayoutInflater().inflate(R.layout.edittextlayout, null, false);
        num = 1;
        EditText editText = viewItem.findViewById(R.id.search);
        ImageView minus = viewItem.findViewById(R.id.minus);
        TextView Textnumber = viewItem.findViewById(R.id.number);
        ImageView plus = viewItem.findViewById(R.id.plus);
        ImageView imageClose = viewItem.findViewById(R.id.remove);

        Textnumber.setText(""+numb);

        if (Visability) {
            imageClose.setVisibility(View.VISIBLE);
        } else {
            imageClose.setVisibility(View.INVISIBLE);
        }

        editText.setText(data);

        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout.removeView(viewItem);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num > 1){
                    num = Integer.parseInt(Textnumber.getText().toString()) - 1;
                    Textnumber.setText("" +num);
                }
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = Integer.parseInt(Textnumber.getText().toString()) + 1;
                Textnumber.setText("" +num);
            }
        });

        linearLayout.addView(viewItem);
    }

    public void SaveDataToMutable(){
        textList = new ArrayList<>();
        numberlist = new ArrayList<>();
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            EditText e = linearLayout.getChildAt(i).findViewById(R.id.search);
            TextView x = linearLayout.getChildAt(i).findViewById(R.id.number);
            textList.add(e.getText().toString());
            numberlist.add(x.getText().toString());
        }
        Vm.stringList = textList;
        Vm.numberList = numberlist;
        Vm.Text.setValue(Vm.stringList);
        Vm.num.setValue(Vm.numberList);
    }

    private void goToFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null);
        transaction.replace(R.id.fragment_container, fragment)
                .commit();
    }

}