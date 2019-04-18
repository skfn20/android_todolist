package com.example.shark.pa1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter implements View.OnClickListener{

    private ListBtnClickListener listBtnClickListener;

    public interface ListBtnClickListener{
        void onListBtnClick(View v, ListViewItem item);
    }
    //생성자로부터 전달된 resource id 값을 저장
    int resourceId;
    //생성자로부터 전달된 listBtnClickListener 저장
    private Context context;

    // ListViewBtnAdapter 생성자. 마지막에 ListBtnClickListener 추가.
    ListViewAdapter(Context context, int resource, ArrayList<ListViewItem> list, ListBtnClickListener clickListener) {
        super(context, resource, list) ;

        // resource id 값 복사.
        this.resourceId = resource ;
        this.context = context;
        this.listBtnClickListener = clickListener;
    }
    @Override
    public void onClick(View v) {
        if(this.listBtnClickListener != null) {
            Log.e("id", ""+v.getId());
            final ListViewItem item = (ListViewItem)getItem((int)v.getTag());
            this.listBtnClickListener.onListBtnClick(v, item);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.resourceId, parent, false);
        }

        final TextView jobText = (TextView) convertView.findViewById(R.id.job);
        final TextView deadText = (TextView) convertView.findViewById(R.id.deadline);

        final ListViewItem listViewItem = (ListViewItem) getItem(position);

        jobText.setText(listViewItem.getJob());
        deadText.setText(listViewItem.getDeadline());

        Button edit_button = (Button) convertView.findViewById(R.id.edit_button);
        Button delete_button = (Button) convertView.findViewById(R.id.delete_button);
        Button move_button = (Button) convertView.findViewById(R.id.Move_button);

        edit_button.setTag(position);
        edit_button.setOnClickListener(this);

        delete_button.setTag(position);
        delete_button.getId();
        delete_button.setOnClickListener(this);

        move_button.setTag(position);
        move_button.setOnClickListener(this);

        return convertView;
    }

}
