package com.hjy.android.slidingbuttonview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.hjy.android.slidingbuttonview.widget.SlidingButtonView;

public class MainActivity extends AppCompatActivity {
   ListView listView;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      listView = (ListView) findViewById(R.id.listView);
      listView.setAdapter(new SlidingAdapter());
   }
   class SlidingAdapter extends BaseAdapter implements SlidingButtonView.IonSlidingButtonListener{
      private SlidingButtonView mMenu = null;

      @Override
         public int getCount() {
            return 20;
         }

         @Override
         public Object getItem(int position) {
            return null;
         }

         @Override
         public long getItemId(int position) {
            return position;
         }

         @Override
         public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
               convertView = View.inflate(MainActivity.this,R.layout.item_cartlist,null);
            }
            if (menuIsOpen()){closeMenu();}
            convertView.findViewById(R.id.item_cartlist_ll_content).getLayoutParams().width = MainActivity.this.getWindowManager().getDefaultDisplay().getWidth();
            convertView.findViewById(R.id.item_cartlist_ll_content).setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                  if (menuIsOpen()){
                     closeMenu();
                  }
               }
            });
            ((SlidingButtonView)convertView).setSlidingButtonListener(this);
            return convertView;
         }

      @Override
      public void onMenuIsOpen(View view) {
         mMenu = (SlidingButtonView) view;
      }

      @Override
      public void onDownOrMove(SlidingButtonView slidingButtonView) {
         if (menuIsOpen()) {
            if (mMenu != slidingButtonView) {
               closeMenu();
            }
         }
      }
      private boolean menuIsOpen() {
         return mMenu != null;
      }

      private void closeMenu() {
         mMenu.closeMenu();
         mMenu = null;
      }

   }
}
