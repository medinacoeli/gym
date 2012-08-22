/**
 * 
 * This is OpenTraining, an Android application for planning your your fitness training.
 * Copyright (C) 2012 Christian Skubich
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package de.skubware.opentraining;

import java.io.File;

import de.skubware.opentraining.datamanagement.DataManager;
import de.skubware.opentraining.exporter.HTMLExporter;
import de.skubware.opentraining.exporter.WorkoutExporter;

import de.skubware.training_app.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.webkit.WebView;


public class ShowTPActivity extends Activity {
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.show_tp);
		WebView webview = (WebView) findViewById(R.id.tpWebView);
		
		webview.getSettings().setBuiltInZoomControls(true);
		
		//TODO instead of 5 enter real columncount
		final WorkoutExporter exporter = new HTMLExporter( 5, this, webview, DataManager.INSTANCE.getCurrentWorkout());

		Button button_export = (Button) findViewById(R.id.button_export);
		button_export.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {

				//TODO: test this and fix bugs
				File f = exporter.exportWorkoutToFile(DataManager.INSTANCE.getCurrentWorkout());
				
				Toast.makeText(ShowTPActivity.this, "Workout erstellt: " + f.toString(), Toast.LENGTH_LONG).show();			
				
				
				/*Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND); 
				emailIntent.setType("image/jpeg");
				
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {"christian.skubich@googlemail.com"}); 
			    emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,   "Test Subject"); 
			    emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,  "go on read the emails"); 
			    emailIntent.putExtra(Intent.EXTRA_STREAM, f);//Uri.parse(f.toString()));//"file://"+ sPhotoFileName));

			    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
			    */
			}

		});
		

	}	
	


}