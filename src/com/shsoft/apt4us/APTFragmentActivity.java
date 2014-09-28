package com.shsoft.apt4us;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.shsoft.apt4us.BaseComponents.A4UDefines;
import com.shsoft.apt4us.fragments.A4UFragment_APT_Alarm;
import com.shsoft.apt4us.fragments.A4UFragment_APT_List;
import com.shsoft.apt4us.fragments.A4UFragment_APT_Title;

public class APTFragmentActivity extends FragmentActivity implements OnClickListener {
	
	
	private A4UFragment_APT_Title mFragment_APT_Title = null;
	private A4UFragment_APT_List mFragment_APT_List = null;
	private A4UFragment_APT_Alarm mFragment_APT_Alarm  = null;
	
	private AdView mAdView;
	@Override
	protected void onCreate(Bundle arg) {
		// TODO Auto-generated method stub
		super.onCreate(arg);
		setContentView(R.layout.fragment_main);
		//
		Button mRequestPush = (Button) findViewById(R.id.bt_requestPush);
		mRequestPush.setOnClickListener(this);

		/*
		// ad test
		mAdView = new AdView(this);
        mAdView.setAdUnitId("ca-app-pub-1183484887572395/2248222665");
        mAdView.setAdSize(AdSize.BANNER);
        //mAdView.setAdListener(new ToastAdListener(this));
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.rl_framentmain);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(mAdView, params);
        mAdView.loadAd(new AdRequest.Builder().build());
		// 
		*/
		//Bundle extras = arg.getExtras();
		Bundle Extra = getIntent().getExtras();
		if (Extra != null) {
			if(Extra.containsKey("REFRESH_LIST"))
			{
				int ret = (Integer) Extra.get("FRAGMENT");
				if( ret > -1)
					fragmentReplace(ret);
			}	
		}
		else
			fragmentReplace(A4UDefines.FRAGMENT_TITLE);
	}

	@Override
	public View onCreateView(String name, Context context, AttributeSet attrs) {
		// TODO Auto-generated method stub
		return super.onCreateView(name, context, attrs);
	}

	@Override
	public void onClick(View arg0) {
		int mCurrentFragmentIndex;
		switch (arg0.getId()) {

			case R.id.bt_requestPush:
				requestPush();
				break;
	
			}
	}
	
	public void fragmentReplace(int reqNewFragmentIndex) {

		Fragment newFragment = null;

		Log.d("SHRYU", "fragmentReplace " + reqNewFragmentIndex);

		newFragment = loadFragment(reqNewFragmentIndex);

		// replace fragment
		final FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();

		if(newFragment != null)
			transaction.replace(R.id.ll_fragment, newFragment);

		// Commit the transaction
		transaction.commit();

	}
	
	private Fragment loadFragment(int idx) {
		Fragment newFragment = null;

		switch (idx) {
		case A4UDefines.FRAGMENT_TITLE:
			if(mFragment_APT_Title == null)
				mFragment_APT_Title  = new A4UFragment_APT_Title();
			newFragment = mFragment_APT_Title ;
			break;
		case A4UDefines.FRAGMENT_LIST:
			if(mFragment_APT_List == null)
				mFragment_APT_List  = new A4UFragment_APT_List();
			newFragment = mFragment_APT_List ;
			break;

		default:
			Log.d("SHRYU", "Unhandle case");
			break;
		}

		return newFragment;
	}
	
	boolean requestPush()
	{
		boolean ret = false;
		String requestCode = A4UDefines.PushServerURL + "11";
		
		
		return ret;
	}

}
