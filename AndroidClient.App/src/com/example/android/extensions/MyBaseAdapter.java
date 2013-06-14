package com.example.android.extensions;

import java.text.SimpleDateFormat;
import java.util.List;
import com.example.android.models.TestMessageModel;
import com.example.example_android_demo.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyBaseAdapter extends BaseAdapter {

	Context context;
	private List<TestMessageModel> testMessages;  
	@SuppressLint("SimpleDateFormat")
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    
	public MyBaseAdapter(Context context,List<TestMessageModel> testMessages){  
		this.context = context;  
        this.testMessages = testMessages;          
    } 
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return (testMessages==null)?0:testMessages.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return testMessages.get(position);  
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	public class ViewHolder{  
        TextView tvwTitle;  
        TextView tvwContent;  
        TextView tvwDate;  
    } 

	public View getView(int position, View convertView, ViewGroup parent) {  
        final TestMessageModel testMessage = (TestMessageModel)getItem(position);  
        ViewHolder viewHolder = null;  
        if(convertView==null){  
            //Log.d("MyBaseAdapter", "新建convertView,position="+position);  
            convertView = LayoutInflater.from(context).inflate(R.layout.refreshandload_itema, null);  
              
            viewHolder = new ViewHolder();  
            viewHolder.tvwTitle = (TextView)convertView.findViewById(R.id.item_title);  
            viewHolder.tvwContent = (TextView)convertView.findViewById(R.id.item_content);  
            viewHolder.tvwDate = (TextView)convertView.findViewById(R.id.item_date);  
                        
            convertView.setTag(viewHolder);  
        }else{  
            viewHolder = (ViewHolder)convertView.getTag();  
            //Log.d("MyBaseAdapter", "旧的convertView,position="+position);  
        }  
          
        viewHolder.tvwTitle.setText(testMessage.getTitle());  
        viewHolder.tvwContent.setText(testMessage.getDetail());  
        viewHolder.tvwDate.setText(dateFormat.format(testMessage.getCreateTime()));   
          
        //对ListView中第1个TextView配置OnClick事件  
        /*viewHolder.tvwTitle.setOnClickListener(new OnClickListener(){  
            @Override  
            public void onClick(View v) {  
                Toast.makeText(context,   
                        "[tvwTitle.setOnClickListener]点击了"+person.name,   
                        Toast.LENGTH_SHORT).show();  
            }  
        }); */
          
        //对ListView中的每一行信息配置OnClick事件  
        /*convertView.setOnClickListener(new OnClickListener(){  
  
            @Override  
            public void onClick(View v) {  
                Toast.makeText(context,   
                        "[convertView.setOnClickListener]点击了"+testMessage.getTitle(),   
                        Toast.LENGTH_SHORT).show();  
            }  
              
        });*/
          
        //对ListView中的每一行信息配置OnLongClick事件  
        /*convertView.setOnLongClickListener(new OnLongClickListener(){  
            @Override  
            public boolean onLongClick(View v) {  
                Toast.makeText(context,   
                        "[convertView.setOnLongClickListener]点击了"+person.name,   
                        Toast.LENGTH_SHORT).show();  
                return true;  
            }  
        });  */
          
        return convertView;  
    }
	
}