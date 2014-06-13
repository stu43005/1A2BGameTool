package edu.hk.csie.u100b219.finalproject;

import edu.hk.csie.u100b219.finalproject.models.Guess;
import edu.hk.csie.u100b219.finalproject.models.GuessNumber;
import edu.hk.csie.u100b219.finalproject.models.Number;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText mNumberText;
	private RadioGroup aRadioGroup;
	private int[] aRadioId = {R.id.aRadio0, R.id.aRadio1, R.id.aRadio2, R.id.aRadio3};
	private RadioGroup bRadioGroup;
	private int[] bRadioId = {R.id.bRadio0, R.id.bRadio1, R.id.bRadio2, R.id.bRadio3};
	private TextView mAmount;

	private ListView mListView;
	private ArrayAdapter<Number> mAdapter;

	private GuessNumber gn;

	private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			mNumberText.setText(String.valueOf(gn.numbers.get(position).getNumber()));
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mNumberText = (EditText) findViewById(R.id.number);
		aRadioGroup = (RadioGroup) findViewById(R.id.aRadioGroup);
		bRadioGroup = (RadioGroup) findViewById(R.id.bRadioGroup);
		mAmount = (TextView) findViewById(R.id.amount);

		gn = new GuessNumber();

		mAdapter = new ArrayAdapter<Number>(this, android.R.layout.simple_list_item_1, gn.numbers);
		mListView = (ListView) findViewById(R.id.listView1);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(mItemClickListener);

		this.updateUI();
	}

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
		if (id == R.id.reset) {
			this.clearAll();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void reset(View v) {
		this.clearAll();
	}

	private void clearAll() {
		mNumberText.setText("");
		gn.clearAll();
		this.updateUI();
	}

	private int getA() {
		int id = aRadioGroup.getCheckedRadioButtonId();
		for (int i = 0; i < aRadioId.length; i++) {
			if (aRadioId[i] == id) {
				return i;
			}
		}
		return 0;
	}

	private int getB() {
		int id = bRadioGroup.getCheckedRadioButtonId();
		for (int i = 0; i < bRadioId.length; i++) {
			if (bRadioId[i] == id) {
				return i;
			}
		}
		return 0;
	}

	public void guess(View v) {
		Guess guess;
		try {
			guess = new Guess(mNumberText.getText().toString(), this.getA(), this.getB());
		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
			return;
		}

		Toast.makeText(this, "²q¤F!", Toast.LENGTH_LONG).show();
		mNumberText.setText("");
		gn.guess(guess);
		this.updateUI();
	}

	private void updateUI() {
		mAdapter.notifyDataSetChanged();
		mAmount.setText(String.format("(%d)", gn.numbers.size()));
	}
}
