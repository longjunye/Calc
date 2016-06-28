package com.pan.calc;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ricky.ye on 5/22/16.
 */
public class NumberedAdapter extends RecyclerView.Adapter<TextViewHolder> {
	interface OnAnsweredListener {
		void onAnswered(long answer,int position);
	}

	private List<Calc> labels;
	private CALC_LEVEL calcLevel;
	private OnAnsweredListener onAnsweredListener;

	public CALC_LEVEL getCalcLevel() {
		return calcLevel;
	}

	public void setCalcLevel(CALC_LEVEL calcLevel) {
		this.calcLevel = calcLevel;
	}

	public List<Calc> getLabels() {
		return labels;
	}

	public void setLabels(List<Calc> labels) {
		this.labels = labels;
	}

	public NumberedAdapter(int count, CALC_LEVEL calcLevel,OnAnsweredListener onAnsweredListener) {
		labels = new ArrayList(count);
		this.calcLevel = calcLevel;
		this.onAnsweredListener = onAnsweredListener;
		labels = Utils.generateCalcList(count,labels,calcLevel);
	}

	@Override
	public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
		return new TextViewHolder(view);
	}

	@Override
	public void onBindViewHolder(final TextViewHolder holder, final int position) {
		reset(holder);
		Calc calc = labels.get(holder.getAdapterPosition());
		holder.firstNumber.setText(String.valueOf(calc.getFirstNumber()));
		holder.secondNumber.setText(String.valueOf(calc.getSecondNumber()));
		holder.calcChar.setText(calc.getCalcChar().toString());
		holder.result.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus) {
					if(StringUtil.isNotEmpty(holder.result.getText().toString())){
						try {
							long answer = Long.valueOf(holder.result.getText().toString());
							if(onAnsweredListener != null){
								onAnsweredListener.onAnswered(answer,position);
							}
						}catch (ClassCastException e) {
							Log.i("ylj","ClassCastException : " + e.getMessage());
						}

					}
				}
			}
		});
		if (calc.getAnswer() > 0) {
			holder.result.setText(String.valueOf(calc.getAnswer()));
		}
	}

	@Override
	public int getItemCount() {
		return labels.size();
	}

	private void reset(TextViewHolder holder) {
		holder.firstNumber.setText("");
		holder.secondNumber.setText("");
		holder.calcChar.setText("");
		holder.result.setText("");
	}

}
