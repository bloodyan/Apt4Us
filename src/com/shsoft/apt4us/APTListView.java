//package com.shsoft.apt4us;
//
//import java.util.Iterator;
//import java.util.List;
//
//import net.htmlparser.jericho.Element;
//import net.htmlparser.jericho.HTMLElementName;
//
//import com.shsoft.apt4us.MainActivity.PlaceholderFragment;
//
//import android.content.Context;
//import android.view.View;
//import android.view.ViewGroup.LayoutParams;
//import android.widget.TableLayout;
//import android.widget.TableRow;
//import android.widget.TextView;
//
//public class APTListView extends View {
//
//	TextView mHouseName;
//	TextView mCompany;
//	TextView mTimeLimit;
//	TextView mAnnounceDay;
//	
//	public APTListView(Context context) {
//		super(context);
////		
////		getSupportFragmentManager().beginTransaction()
////		.add(R.id.container, new PlaceholderFragment()).commit();
////
////		super.onCreate(context.savedInstanceState);
////		setContentView(R.layout.fragment_main);
////
////		if (savedInstanceState == null) {
////			getSupportFragmentManager().beginTransaction()
////					.add(R.id.container, new PlaceholderFragment()).commit();
////		}
////		
////		mHouseName = (TextView)findViewById(R.id.ColumnName1);
////		mHouseName.setText("주택명");
////		mHouseName = (TextView)findViewById(R.id.ColumnName2);
////		mHouseName.setText("업체명");		
////		mTimeLimit = (TextView)findViewById(R.id.ColumnName3);
////		mTimeLimit.setText("청약기간");
////		mAnnounceDay = (TextView)findViewById(R.id.ColumnName4);
////		mAnnounceDay.setText("발표일");
////		
////		
////		
////
////		TableLayout MainTable = (TableLayout)findViewById(R.id.TableLayout_List);
////		
////		
////		PageParser pp = new PageParser();
////		Element ResultElement = pp.getTableElements().get(0);
////		List<Element> TRList = ResultElement.getAllElements(HTMLElementName.TR);
////
////		for(Iterator<Element> it = TRList.iterator();it.hasNext();)
////		{
////			String Company;
////			String TimeLimit;
////			String AnnounceDate;
////			Element element = it.next();
////			List<Element> result = element.getAllElements();
////			String Name = result.get(2).getContent().toString().replace("　", ""); // 집 이름
////			if(result.size() <= 6) 
////			{
////				Company = result.get(3).getContent().toString().replace(" ", ""); // 회사명
////				TimeLimit = result.get(4).getContent().toString().replace(" ", "").replace("　", "").replace("\r", "").replace("\n", "").replace("\t", "").replace("~", "\n~ "); // 기간
////				AnnounceDate = result.get(5).getContent().toString().replace(" ", ""); // 발표일
////			}
////			else
////			{
////				Company = result.get(4).getContent().toString().replace(" ", ""); // 회사명
////				TimeLimit = result.get(5).getContent().toString().replace(" ", "").replace("　", "").replace("\r", "").replace("\n", "").replace("\t", "").replace("~", "\n~ "); // 기간
////				AnnounceDate = result.get(6).getContent().toString().replace(" ", ""); // 발표일
////				
////			}
////			TableRow NewTR = new TableRow(this);
////			NewTR.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
////			TextView Tv1 = new TextView(this);
////			Tv1.setText(Name);
////			Tv1.setLayoutParams(new TableRow.LayoutParams(0,LayoutParams.WRAP_CONTENT, 4));
////			
////			TextView Tv2 = new TextView(this);
////			Tv2.setText(Company);
////			Tv2.setLayoutParams(new TableRow.LayoutParams(0,LayoutParams.WRAP_CONTENT, 2));
////			
////			TextView Tv3 = new TextView(this);
////			Tv3.setText(TimeLimit);
////			Tv3.setLayoutParams(new TableRow.LayoutParams(0,LayoutParams.WRAP_CONTENT, 2));
////			
////			TextView Tv4 = new TextView(this);
////			Tv4.setText(AnnounceDate);
////			Tv4.setLayoutParams(new TableRow.LayoutParams(0,LayoutParams.WRAP_CONTENT, 2));
////			
////			NewTR.addView(Tv1);
////			NewTR.addView(Tv2);
////			NewTR.addView(Tv3);
////			NewTR.addView(Tv4);
////			
////			MainTable.addView(NewTR);
////		}
//	}
//	
//
//}
