package com.shsoft.apt4us;

import com.shsoft.apt4us.BaseComponents.A4UDefines;
import com.shsoft.apt4us.BaseComponents.AptParseListener;

import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.shsoft.apt4us.BaseComponents.A4UDefines;

import android.os.AsyncTask;
import net.htmlparser.jericho.Attributes;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.TextExtractor;

public class PageParser {

	private Element TableList =null;
	private AptParseListener aptListener;
	public PageParser() {
	}

	public static Element FindElement(String sTargetURL, String sHTMLElement , String sAttributeValue, String sFindValue) {
Source source = null;
		
		try {
			URL url = new URL(sTargetURL);
			source = new Source(url);
			List<Element> list = source.getAllElements(sHTMLElement);

			Element element = null;

			for (int i = 0; i < list.size(); i++) {
				element = list.get(i);
				String classString = element.getAttributeValue(sAttributeValue);
				if(classString != null && classString.equals(sFindValue)){
					return element;
				}
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<Element> ParseTableElements() {

		PageParserWorker ppw = new PageParserWorker();
		ppw.execute("");
		
		if(TableList != null) return TableList.getAllElements();
		else return null;
	}
	private class PageParserWorker extends AsyncTask<String, Integer, Boolean>
	{
		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			aptListener.onAptParseListener();
		}
		@Override
		protected Boolean doInBackground(String... params) {
			TableList = FindElement(A4UDefines.TargetURL,HTMLElementName.TBODY,"class","line");
			return true;
		}
	}
	
	public Element getFirstTableElement()
	{
		return TableList.getAllElements().get(0);
	}

	public void setListener(AptParseListener Listener) {
		aptListener = Listener;
	}
}
