package com.shsoft.apt4us.fragments;

import com.shsoft.apt4us.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class A4UFragment_APT_Title extends Fragment implements OnClickListener{

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_title, container, false);

		Button ListButton = (Button) v.findViewById(R.id.bt_show_list);
		ListButton.setOnClickListener(this);

		Button AlarmButton = (Button) v.findViewById(R.id.bt_set_alram);
		AlarmButton.setOnClickListener(this);

		Button FilterButton = (Button) v.findViewById(R.id.bt_filter);
		FilterButton.setOnClickListener(this);

		return v;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.bt_show_list:
			LoadA4UListPage();
			break;

		case R.id.bt_set_alram:
			LoadA4UAlarmPage();
			break;
			
		case R.id.bt_filter:
			LoadA4UListPage();
			break;
		}

	}
	
	private void LoadA4UAlarmPage() {
		FragmentTransaction ft = getFragmentManager().beginTransaction();	
		Fragment fragment = new A4UFragment_APT_Alarm();
		ft.hide(this);
		ft.replace(R.id.ll_fragment, fragment);
		ft.addToBackStack(null);
		ft.commit();
	}

	public void LoadA4UListPage()
	{
		FragmentTransaction ft = getFragmentManager().beginTransaction();	
		Fragment fragment = new A4UFragment_APT_List();
		ft.hide(this);
		ft.replace(R.id.ll_fragment, fragment);
		ft.addToBackStack(null);
		ft.commit();
	}
}
