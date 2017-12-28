package com.example.fblabizcom;

import java.util.Arrays;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class Game extends Activity {
	String[] trueQuestions;
	String[] falseQuestions;
	String[] actualQuestions = new String[10];
	boolean[] answers = new boolean[10];
	boolean[] userResponse = new boolean[10];
	TextView questionBox;
	int[] indexesUsedTrue = new int[10];
	int[] indexesUsedFalse = new int[10];
	int questionIndex= 0;
	boolean loggedIn;
	@Override
	protected void onCreate(Bundle savedInstanceState)  {
		super.onCreate(savedInstanceState);

		Bundle extras = getIntent().getExtras();
		loggedIn = extras.getBoolean("loggedIn");
		setContentView(R.layout.activity_game);
		questionBox = (TextView) findViewById(R.id.questionTextView);
		trueQuestions = getResources().getStringArray(R.array.true_questions);
		falseQuestions = getResources().getStringArray(R.array.false_questions);
		for (int x = 0; x <= 9; x++){
			double trueOrFalse = Math.random();
			if (trueOrFalse > .5){
				int randomQuestion = getRandomIndex(true);
				indexesUsedTrue[x] = randomQuestion;
				actualQuestions[x] = trueQuestions[randomQuestion];
				answers[x] = true;
			} else {
				int randomQuestion = getRandomIndex(false);
				indexesUsedFalse[x] = randomQuestion;
				actualQuestions[x] = falseQuestions[randomQuestion];
				answers[x] = false;
			}
		}
		questionBox.setText(actualQuestions[questionIndex]);
		
	}
	private int getRandomIndex(boolean trueQuestion){
		int index = 0;
		if (trueQuestion){
			index = trueQuestions.length;
			while (index >= trueQuestions.length){
				index = (int)(Math.random() * 100);
				if (Arrays.binarySearch(indexesUsedTrue, index) >= 0){
					index = trueQuestions.length;
				}
			}
		} else {
			index = falseQuestions.length;
			while (index >= falseQuestions.length){
				index = (int)(Math.random() * 100);
				if (Arrays.binarySearch(indexesUsedFalse, index) >= 0){
					index = falseQuestions.length;
				}
			}			
		}
		return index;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}
	public void onBackPressed(){
	}
	public void onClick(View view){
		TextView question = (TextView) view;
		userResponse[questionIndex] = ((String) question.getText()).equalsIgnoreCase(answers[questionIndex] + ""); 
		questionIndex++;
		if (questionIndex < actualQuestions.length){
		questionBox.setText(actualQuestions[questionIndex]);
		} else {
			int activityID = 0x100;
			Intent intent;
			intent = new Intent().setClass(this, Results.class);
			intent.putExtra("name",userResponse);
			intent.putExtra("loggedIn", loggedIn);
			startActivityForResult(intent, activityID);		
		}
	}
	
}
