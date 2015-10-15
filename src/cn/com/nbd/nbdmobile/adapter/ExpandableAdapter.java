//package cn.com.nbd.nbdmobile.adapter;
//
///**
// * Created by Dell on 2015/10/15.
// */
//
//import android.view.Gravity;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AbsListView;
//import android.widget.BaseExpandableListAdapter;
//import android.widget.TextView;
//
//import cn.com.nbd.nbdmobile.R;
//import cn.com.nbd.nbdmobile.activity.NewsPaperActivity;
//
//
//public class ExpandableAdapter extends BaseExpandableListAdapter {
//
//	private NewsPaperActivity expandable;
//
//	public ExpandableAdapter(NewsPaperActivity expandable) {
//		this.expandable = expandable;
//	}
//
//	public int getGroupCount() {
//		// TODO Auto-generated method stub
//		return expandable.getGroupArray().size();
//	}
//
//	public int getChildrenCount(int groupPosition) {
//		// TODO Auto-generated method stub
//		return expandable.getChildArray().get(groupPosition).size();
//	}
//
//	public Object getGroup(int groupPosition) {
//		// TODO Auto-generated method stub
//		return expandable.getGroupArray().get(groupPosition);
//	}
//
//	public Object getChild(int groupPosition, int childPosition) {
//		// TODO Auto-generated method stub
//		return expandable.getChildArray().get(groupPosition).get(childPosition);
//	}
//
//	public long getGroupId(int groupPosition) {
//		// TODO Auto-generated method stub
//		return groupPosition;
//	}
//
//	public long getChildId(int groupPosition, int childPosition) {
//		// TODO Auto-generated method stub
//		return childPosition;
//	}
//
//	public boolean hasStableIds() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	public View getGroupView(int groupPosition, boolean isExpanded,
//							 View convertView, ViewGroup parent) {
//		// TODO Auto-generated method stub
//		//  String string = expandable.getGroupArray().get(groupPosition);
//		//  return getGenericView(string);
//		View view = null;
////  if(convertView == null) {
//		view = expandable.getLayoutInflater().inflate(R.layout.group, null);
//		TextView tv = (TextView) view.findViewById(R.id.groupLabel);
//		String ss = expandable.getGroupArray().get(groupPosition).get("CONTENT_TAG");
//		tv.setText(ss);
////  } else {
////   view = convertView;
////  }
//		return view;
//	}
//
//	public View getChildView(int groupPosition, int childPosition,
//							 boolean isLastChild, View convertView, ViewGroup parent) {
//		// TODO Auto-generated method stub
//		// String string = expandable.getChildArray().get(groupPosition).get(childPosition);
//		// return getGenericView(string);
//		View view = null;
////  if(convertView == null) {
//		view = expandable.getLayoutInflater().inflate(R.layout.group_child, null);
//		TextView tv = (TextView) view.findViewById(R.id.childLabel);
//		String ss = expandable.getChildArray().get(groupPosition).get(childPosition).get("CONTENT_TAG");
//		tv.setText(ss);
////  } else {
////   view = convertView;
////  }
//		return view;
//	}
//
//	public boolean isChildSelectable(int groupPosition, int childPosition) {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	public TextView getGenericView(String string) {
//		// Layout parameters for the ExpandableListView
//		AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
//				ViewGroup.LayoutParams.FILL_PARENT, 64);
//		TextView text = new TextView(expandable);
//		text.setLayoutParams(layoutParams);
//		// Center the text vertically
//		text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
//		// Set the text starting position
//		text.setPadding(36, 0, 0, 0);
//		text.setText(string);
//		return text;
//
//	}
//}