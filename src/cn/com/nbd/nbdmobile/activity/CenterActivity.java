package cn.com.nbd.nbdmobile.activity;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import org.hjh.inject.InjectCore;
import org.hjh.inject.InjectLayout;
import org.hjh.inject.InjectView;
import org.hjh.view.RoundImageView;

import cn.com.nbd.nbdmobile.R;

/**
 * Created by Dell on 2015/10/26.
 */

@InjectLayout(layout = R.layout.center_layout)
public class CenterActivity extends BaseActivity {

	public static final int REQUEST_CODE_SELECT_PHOTO = 1;

	@InjectView(id = R.id.back_button, onClick = "this")
	private Button backButton;

	@InjectView(id = R.id.top_button, onClick = "this")
	private Button topButton;

	@InjectView(id = R.id.user_photo, onClick = "this")
	private RoundImageView userPhoto;

	private String path;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//	setTranslucentStatus();
		InjectCore.injectUI(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		if (v.getId() == R.id.user_photo) {
			Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(i, REQUEST_CODE_SELECT_PHOTO);
		}
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == REQUEST_CODE_SELECT_PHOTO && resultCode == RESULT_OK) {
			Uri photoUri = data.getData();
			path = getPath(this, photoUri);

			mImageLoader.loadImageFromPath(path, userPhoto, 0);
		}
	}

	public String getPath(Activity activity, Uri uri) {
		String[] projection = {MediaStore.Images.Media.DATA};
		Cursor cursor = activity.managedQuery(uri, projection, null, null, null);
		activity.startManagingCursor(cursor);
		int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}


}
