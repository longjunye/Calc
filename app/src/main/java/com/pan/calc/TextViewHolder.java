package com.pan.calc;

/**
 * Created by ricky.ye on 5/22/16.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TextViewHolder extends RecyclerView.ViewHolder {
	public TextView firstNumber,calcChar,secondNumber;
	public EditText result;
	public TextViewHolder(View itemView) {
		super(itemView);
		firstNumber = (TextView) itemView.findViewById(R.id.first_number);
		calcChar = (TextView) itemView.findViewById(R.id.calc_char);
		secondNumber = (TextView) itemView.findViewById(R.id.second_number);
		result = (EditText) itemView.findViewById(R.id.result);
	}
}
