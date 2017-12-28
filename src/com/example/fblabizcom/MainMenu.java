package com.example.fblabizcom;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;

public class MainMenu extends FragmentActivity {
	private MainFragment mainFragment;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		if (savedInstanceState == null) {
			mainFragment = new MainFragment();
			getSupportFragmentManager().beginTransaction().add(android.R.id.content, mainFragment).commit();
		} else {
			mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(android.R.id.content);
		}
	}
	public void onPlayClick(View view) {
		int activityID = 0x100;
		Intent intent = new Intent(MainMenu.this, Game.class);
		intent.putExtra("loggedIn", mainFragment.loggedIn);
		startActivityForResult(intent, activityID);
	}

	public void onHelpClick(View view) {
		Intent intent = new Intent(MainMenu.this, Help.class);
		startActivity(intent);

	}
	public void onBackPressed(){
		
	}
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}
}
