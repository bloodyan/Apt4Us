package com.shsoft.apt4us;

import java.util.Iterator;
import java.util.List;

import com.shsoft.apt4us.BaseComponents.AptParseListener;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.os.Build;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Segment;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.TextExtractor;

public class MainActivity extends ActionBarActivity {


//	private PageParser mPageParser;
//	private TableLayout mMainTable;
	//private APTListView mAPTListView;
//	private APTFragmentActivity APTFA;
//	private TextView mHouseNameTV;
//	private TextView mCompanyTV;
//	private TextView mTimeLimitTV;
//	private TextView mAnnounceDayTV;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = new Intent(this, APTFragmentActivity.class);
		startActivity(intent);
		
	}

//	public void LoadHouseInformations(Element resultElement)
//	{
//		List<Element> TRList = resultElement.getAllElements(HTMLElementName.TR);
//
//		for(Iterator<Element> it = TRList.iterator();it.hasNext();)
//		{
//			String Company;
//			String TimeLimit;
//			String AnnounceDate;
//			Element element = it.next();
//			List<Element> result = element.getAllElements();
//			String Name = result.get(2).getContent().toString().replace("　", ""); // 집 이름
//			if(result.size() <= 6) 
//			{
//				Company = result.get(3).getContent().toString().replace(" ", ""); // 회사명
//				TimeLimit = result.get(4).getContent().toString().replace(" ", "").replace("　", "").replace("\r", "").replace("\n", "").replace("\t", "").replace("~", "\n~ "); // 기간
//				AnnounceDate = result.get(5).getContent().toString().replace(" ", ""); // 발표일
//			}
//			else
//			{
//				Company = result.get(4).getContent().toString().replace(" ", ""); // 회사명
//				TimeLimit = result.get(5).getContent().toString().replace(" ", "").replace("　", "").replace("\r", "").replace("\n", "").replace("\t", "").replace("~", "\n~ "); // 기간
//				AnnounceDate = result.get(6).getContent().toString().replace(" ", ""); // 발표일
//				
//			}
//			TableRow NewTR = new TableRow(this);
//			NewTR.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
//			TextView Tv1 = new TextView(this);
//			Tv1.setText(Name);
//			Tv1.setLayoutParams(new TableRow.LayoutParams(0,LayoutParams.WRAP_CONTENT, 4));
//			
//			TextView Tv2 = new TextView(this);
//			Tv2.setText(Company);
//			Tv2.setLayoutParams(new TableRow.LayoutParams(0,LayoutParams.WRAP_CONTENT, 2));
//			
//			TextView Tv3 = new TextView(this);
//			Tv3.setText(TimeLimit);
//			Tv3.setLayoutParams(new TableRow.LayoutParams(0,LayoutParams.WRAP_CONTENT, 2));
//			
//			TextView Tv4 = new TextView(this);
//			Tv4.setText(AnnounceDate);
//			Tv4.setLayoutParams(new TableRow.LayoutParams(0,LayoutParams.WRAP_CONTENT, 2));
//			
//			NewTR.addView(Tv1);
//			NewTR.addView(Tv2);
//			NewTR.addView(Tv3);
//			NewTR.addView(Tv4);
//			
//			mMainTable.addView(NewTR);
//		}
//
//	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
//
//	/**
//	 * A placeholder fragment containing a simple view.
//	 */
//	public static class PlaceholderFragment extends Fragment {
//
//		public PlaceholderFragment() {
//		}
//
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container,
//				Bundle savedInstanceState) {
//			View rootView = inflater.inflate(R.layout.fragment_main, container,
//					false);
//			return rootView;
//		}
//	}

//	@Override
//	public void onAptParseListener() {
//		Element ResultElement = mPageParser.getFirstTableElement();
//		LoadHouseInformations(ResultElement);
//	}

}
