package com.pan.calc;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener,NumberedAdapter.OnAnsweredListener{
	private RecyclerView recyclerView;
	private NumberedAdapter numberedAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				calcResult();
			}
		});

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.setDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);

		recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
		recyclerView.addItemDecoration(new MarginDecoration(this));
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
		if(numberedAdapter == null) {
			numberedAdapter = new NumberedAdapter(30,CALC_LEVEL.EASY,this);
		}
		recyclerView.setAdapter(numberedAdapter);
	}

	private void calcResult(){
		if(recyclerView != null && numberedAdapter != null) {
			int correctCount = 0;
			int total = numberedAdapter.getItemCount();
			for(int i=0;i<total;i++){
				Calc calc = numberedAdapter.getLabels().get(i);
				if(calc != null && calc.getAnswer() > 0 ) {
					if(calc.getAnswer() == calc.getResult()){
						correctCount++;
					}
				}
			}
			if(correctCount == total){
				Snackbar.make(recyclerView,"wow,老婆都做对啦~赏一屏么么哒",Snackbar.LENGTH_LONG).show();
			} else if(correctCount > total * 4/5){
				Snackbar.make(recyclerView,"么么哒，答对了80%呢~加油，要100%做对的",Snackbar.LENGTH_LONG).show();
			} else if(correctCount > numberedAdapter.getLabels().size() * 3/5){
				Snackbar.make(recyclerView,"MUA~答对了80%呢~及格,还需继续努力~~~",Snackbar.LENGTH_LONG).show();
			} else if(correctCount == 0) {
				Snackbar.make(recyclerView,"0对?老婆你在逗我呢?",Snackbar.LENGTH_LONG).show();
			} else {
				Snackbar.make(recyclerView,"什么,不及格,看老公下次怎么折腾你",Snackbar.LENGTH_LONG).show();
			}
		}
	}

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
			case R.id.ten_more :
				addMore2RecyclerView(10);
				break;
			case R.id.more_calc :
				final EditText input = new EditText(MainActivity.this);
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.MATCH_PARENT,
						LinearLayout.LayoutParams.MATCH_PARENT);
				input.setLayoutParams(lp);
				input.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
				android.support.v7.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage("请输入要新增的题目数")
						.setView(input)
						.setNegativeButton("取消", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								if(dialog != null) {
									dialog.dismiss();
								}
							}
						})
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								try {
									Editable count = input.getText();
									addMore2RecyclerView(Integer.valueOf(count.toString()));
								}catch (ClassCastException e) {
									Snackbar.make(recyclerView,"老婆,请输入数字！！！",Snackbar.LENGTH_SHORT).show();
								}
							}
						})
						.setTitle("挑战更多")
						.show();

				break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void resetRecyclerView(CALC_LEVEL calcLevel) {
		if(numberedAdapter != null) {
			if(numberedAdapter.getCalcLevel() != calcLevel) {
				numberedAdapter = new NumberedAdapter(30,calcLevel,this);
				recyclerView.setAdapter(numberedAdapter);
			}
		}
	}

	private void addMore2RecyclerView(int count) {
		if(recyclerView != null && numberedAdapter != null) {
			List<Calc> addedCalcList = Utils.generateCalcList(count,numberedAdapter.getLabels(),numberedAdapter.getCalcLevel());
			numberedAdapter.setLabels(addedCalcList);
			numberedAdapter.notifyDataSetChanged();
		}
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
			case R.id.easy :
				resetRecyclerView(CALC_LEVEL.EASY);
				break;
			case R.id.middle :
				resetRecyclerView(CALC_LEVEL.MIDDLE);
				break;
			case R.id.hard :
				resetRecyclerView(CALC_LEVEL.HARD);
				break;
		}
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if(drawer != null) {
			drawer.closeDrawer(GravityCompat.START);
		}
		return true;
	}

	@Override
	public void onAnswered(long answer, int position) {
		if(numberedAdapter != null) {
			Calc calc = numberedAdapter.getLabels().get(position);
			calc.setAnswer(answer);
			numberedAdapter.getLabels().set(position,calc);
		}
	}
}
