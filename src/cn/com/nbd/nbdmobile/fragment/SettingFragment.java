package cn.com.nbd.nbdmobile.fragment;

import org.hjh.inject.InjectCore;
import org.hjh.inject.InjectLayout;
import org.hjh.inject.InjectView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import cn.com.nbd.nbdmobile.R;


@InjectLayout(layout = R.layout.setting_fragment)
public class SettingFragment extends BaseFragment{

	
	@InjectView(id = R.id.changePassword,onClick="this")
	private RelativeLayout changePwd;
	
	@InjectView(id = R.id.changeNick,onClick="this")
	private RelativeLayout changeNick;
	
	@InjectView(id = R.id.headImage,onClick="this")
	private RelativeLayout uploadImage;
	
	@InjectView(id = R.id.onlineHelp,onClick="this")
	private RelativeLayout onlineHelp;
	
	@InjectView(id = R.id.business_type,onClick="this")
	private RelativeLayout businessType;
	
	@InjectView(id = R.id.found_service,onClick="this")
	private RelativeLayout foundService;
	
	@InjectView(id = R.id.level_manager,onClick="this")
	private RelativeLayout levelManager;
	
	@InjectView(id = R.id.change_phone,onClick="this")
	private RelativeLayout changePhone;
	
	private View rootLayout;
	
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
		if(rootLayout == null){
			rootLayout = InjectCore.injectObject(this, getActivity(), true);
		}
		
		if(rootLayout.getParent() != null){
			
			ViewGroup parent = (ViewGroup) rootLayout.getParent();  
					
			if (parent != null) {  
			    parent.removeView(rootLayout);  
			 } 
		}
		return rootLayout;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		Intent intent = new Intent();
		switch (v.getId()) {
		
			
		default:
			break;
		}
	}

	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub
		
	}
	
}
