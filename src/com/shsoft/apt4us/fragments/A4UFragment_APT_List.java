package com.shsoft.apt4us.fragments;

import java.util.Iterator;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;

import com.shsoft.apt4us.PageParser;
import com.shsoft.apt4us.R;
import com.shsoft.apt4us.BaseComponents.AptParseListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class A4UFragment_APT_List extends Fragment implements AptParseListener {
	private TextView mHouseNameTV;
	private TextView mCompanyTV;
	private TextView mTimeLimitTV;
	private TextView mAnnounceDayTV;
	private PageParser mPageParser;
	private TableLayout mMainTable;
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_apt_list, container, false);
		
//		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}
		
		mHouseNameTV = (TextView)v.findViewById(R.id.ColumnName1);
		mHouseNameTV.setText("주택");
		mCompanyTV = (TextView)v.findViewById(R.id.ColumnName2);
		mCompanyTV.setText("회사이");		
		mTimeLimitTV = (TextView)v.findViewById(R.id.ColumnName3);
		mTimeLimitTV.setText("신청기");
		mAnnounceDayTV = (TextView)v.findViewById(R.id.ColumnName4);
		mAnnounceDayTV.setText("발표일");
		
		mMainTable = (TableLayout)v.findViewById(R.id.TableLayout_List);
		
		mPageParser = new PageParser();
		mPageParser.setListener(this);
		mPageParser.ParseTableElements();
		return v;
	}

	
	public void LoadHouseInformations(Element resultElement)
	{
		List<Element> TRList = resultElement.getAllElements(HTMLElementName.TR);

		for(Iterator<Element> it = TRList.iterator();it.hasNext();)
		{
			String Company;
			String TimeLimit;
			String AnnounceDate;
			Element element = it.next();
			List<Element> result = element.getAllElements();
			String Name = result.get(2).getContent().toString().replace("��", ""); // �� �̸�
			if(result.size() <= 6) 
			{
				Company = result.get(3).getContent().toString().replace(" ", ""); // ȸ���
				TimeLimit = result.get(4).getContent().toString().replace(" ", "").replace("��", "").replace("\r", "").replace("\n", "").replace("\t", "").replace("~", "\n~ "); // �Ⱓ
				AnnounceDate = result.get(5).getContent().toString().replace(" ", ""); // ��ǥ��
			}
			else
			{
				Company = result.get(4).getContent().toString().replace(" ", ""); // ȸ���
				TimeLimit = result.get(5).getContent().toString().replace(" ", "").replace("��", "").replace("\r", "").replace("\n", "").replace("\t", "").replace("~", "\n~ "); // �Ⱓ
				AnnounceDate = result.get(6).getContent().toString().replace(" ", ""); // ��ǥ��
				
			}
			TableRow NewTR = new TableRow(getActivity());
			NewTR.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			TextView Tv1 = new TextView(getActivity());
			Tv1.setText(Name);
			Tv1.setLayoutParams(new TableRow.LayoutParams(0,LayoutParams.WRAP_CONTENT, 4));
			
			TextView Tv2 = new TextView(getActivity());
			Tv2.setText(Company);
			Tv2.setLayoutParams(new TableRow.LayoutParams(0,LayoutParams.WRAP_CONTENT, 2));
			
			TextView Tv3 = new TextView(getActivity());
			Tv3.setText(TimeLimit);
			Tv3.setLayoutParams(new TableRow.LayoutParams(0,LayoutParams.WRAP_CONTENT, 2));
			
			TextView Tv4 = new TextView(getActivity());
			Tv4.setText(AnnounceDate);
			Tv4.setLayoutParams(new TableRow.LayoutParams(0,LayoutParams.WRAP_CONTENT, 2));
			
			NewTR.addView(Tv1);
			NewTR.addView(Tv2);
			NewTR.addView(Tv3);
			NewTR.addView(Tv4);
			
			mMainTable.addView(NewTR);
		}

	}
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	@Override
	public void onAptParseListener() {
		Element ResultElement = mPageParser.getFirstTableElement();
		LoadHouseInformations(ResultElement);
	}

}
