package com.example.test3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

public class NameList extends ListActivity {  
     private static final String TAG = "ListViewCheckBoxActivity";  
       
     private List<Item> itemList;  
     private DraftDailyAdapter adapter;  
     private Map<Integer, Boolean> isCheckedMap;  
//     private CheckBox allCheckBox;  
     @Override  
     protected void onCreate(Bundle savedInstanceState) {  
         super.onCreate(savedInstanceState);  
         setContentView(R.layout.namelist);  
//         allCheckBox = (CheckBox)findViewById(R.id.all_check_btn);  
         itemList = new ArrayList<Item>();  
         isCheckedMap = new HashMap<Integer, Boolean>();  
         //初始化数据  
         for(int i=0;i<15;i++){  
             Item item = new Item();  
             item.id=i;
             if(i==0)     item.name = "Abbe Wang|1103";
             else if(i==1)     item.name = "Wilson Wu|1115";
             else if(i==2)     item.name = "Truman Yan|1110";
             else if(i==3)     item.name = "Andrew Gu|1109";
             else if(i==4)     item.name = "Dawn Su|1107";
             else if(i==5)     item.name = "Dara Su|1106";
             else if(i==6)     item.name = "Hilton Hu|1111";
             else if(i==7)     item.name = "Pearl Liu|1102";
             else if(i==8)     item.name = "Laurence Lee|1112";
             else if(i==9)     item.name = "Zach Chou|1108";
             else if(i==10)     item.name = "Gelsey Tang|1101";
             else if(i==11)     item.name = "Stella Yuan|1104";
             else if(i==12)     item.name = "Tanya Yu|1105";
             else if(i==13)     item.name = "Stuart Soong|1113";
             else if(i==14)     item.name = "Roy Ru|1114";             
             itemList.add(item);  
             isCheckedMap.put(i,false);  
         }
         
         
         
         adapter = new DraftDailyAdapter(this,itemList);  
         setListAdapter(adapter);  
//         allCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){   
//             @Override   
//             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {   
//                 Set<Integer> set = isCheckedMap.keySet();  
//                 Iterator<Integer> iterator = set.iterator();    
//                 if(isChecked){   
//                     while(iterator.hasNext()){     
//                         Integer keyId = iterator.next();     
//                         isCheckedMap.put(keyId,true);  
//                     }     
//                 }else{   
//                     while(iterator.hasNext()){     
//                         Integer keyId = iterator.next();    
//                         isCheckedMap.put(keyId,false);  
//                     }    
//                 }  
//                 adapter.notifyDataSetChanged();  
//             }   
//         });   
     }  
           
     class DraftDailyAdapter extends BaseAdapter {  
   
         public List<Item> list;  
         private Context context;  
         LayoutInflater inflater;  
   
         public DraftDailyAdapter(Context context, List<Item> list) {  
             super();  
             this.list = list;  
             this.context = context;  
             inflater = LayoutInflater.from(this.context);  
         }  
         @Override  
         public int getCount() {  
             return list == null ? 0 : list.size();  
         }  
         @Override  
         public Object getItem(int location) {  
             return list.get(location);  
         }  
         @Override  
         public long getItemId(int position) {  
             return position;  
         }  
         @Override    
         public View getView(int position, View convertView, ViewGroup parent) {    
             ViewHolder holder = null;      
             Item item = list.get(position);  
             //Item的位置  
             final int listPosition = position;  
             //这个记录item的id用于操作isCheckedMap来更新CheckBox的状态  
             final int id = item.id;  
             if(convertView == null){  
                 holder = new ViewHolder();  
                 convertView = inflater.inflate(R.layout.nameitem, null);    
                 holder.tvName = (TextView)convertView.findViewById(R.id.stuName);    
//                 holder.deleteButton = (ImageButton)convertView.findViewById(R.id.deleteAttachment);  
                 holder.cBox = (CheckBox)convertView.findViewById(R.id.isCheakBox2);  
                 convertView.setTag(holder);  
             }else{  
                 holder = (ViewHolder) convertView.getTag();  
             }  
             Log.d(TAG, "id="+id);  
             holder.cBox.setChecked(isCheckedMap.get(id));  
             holder.tvName.setText(item.name);   
//             holder.deleteButton.setOnClickListener(new OnClickListener() {  
//                 @Override  
//                 public void onClick(View paramView) {  
//                     //Log.d(TAG, "deletePosition="+listPosition+"");  
//                     //删除list中的数据  
//                     list.remove(listPosition);  
//                     //删除Map中对应选中状态数据  
//                     isCheckedMap.remove(id);  
//                     //通知列表数据修改  
//                     adapter.notifyDataSetChanged();  
//                 }  
//             });  
             holder.cBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){   
                 @Override   
                 public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {   
                     if(isChecked){   
                         isCheckedMap.put(id,true);  
                     }else{   
                         isCheckedMap.put(id,false);  
                     }  
                 }   
             });   
             return convertView;    
         }  
         public final class ViewHolder {      
             public TextView tvName;      
             public ImageButton deleteButton;      
             public CheckBox cBox;      
         }      
     }  
   
     class Item {  
         private Integer id;  
         private String name;  
     }  
       
 }  

