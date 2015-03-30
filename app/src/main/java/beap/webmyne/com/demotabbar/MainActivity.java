package beap.webmyne.com.demotabbar;

import android.graphics.Color;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Tab activity that inclide these following tabs functionality
 *
 *  1. Info
 *  2. Music
 *  3. Share
 *  4. Read
 *  5. Record
 *
 */

public class MainActivity extends ActionBarActivity {

    public static int STATE_RECORD = 0;
    public static int STATE_PREVIEW = 1;
    public static int STATE_READ = 2;
    public static int STATE_CHAT = 3;

    private FragmentTabHost mTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTabHost();
        mTabHost.setCurrentTab(0);
        selectTAB(0);


        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                for(int i=0;i<mTabHost.getTabWidget().getTabCount();i++){
                    selectTAB(i);
                }
            }
        });
    }


    private void initTabHost() {

       /* ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(PlayTabActivity.this, "user_pref", 0);
        user = complexPreferences.getObject("current_user", User.class);
*/
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        View view2 = getLayoutInflater().inflate(R.layout.item_tab_music,null,false);
        View view3 = getLayoutInflater().inflate(R.layout.item_tab,null,false);
        View view4 = getLayoutInflater().inflate(R.layout.item_tab_read,null,false);
        View view5 = getLayoutInflater().inflate(R.layout.item_tab_microphone,null,false);
        TextView tv=(TextView)view2.findViewById(R.id.txtTab);

        tv.setTextColor(Color.BLACK);
        TextView tv1=(TextView)view3.findViewById(R.id.txtTab);
        tv1.setTextColor(Color.BLACK);
        TextView tv2=(TextView)view4.findViewById(R.id.txtTab);
        tv2.setTextColor(Color.BLACK);
        TextView tv3=(TextView)view5.findViewById(R.id.txtTab);
        tv3.setTextColor(Color.BLACK);

        //   Bundle bInfo=new Bundle();
        //   bInfo.putString("infoData",playinfo+"");

        Bundle args = new Bundle();
        args.putInt("currentState",STATE_READ);

        mTabHost.addTab(mTabHost.newTabSpec("tab4").setIndicator(view4),
                BlankFragment1.class,args);


        args = new Bundle();
        args.putInt("currentState",STATE_RECORD);

        mTabHost.addTab(mTabHost.newTabSpec("tab5").setIndicator(view5),
                BlankFragment2.class, args);

        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator(view2),
                BlankFragment3.class, null);

        args = new Bundle();
        args.putString("inspirationType","tab");

      /*  if(user.checkTeacherOrAdmin(user.getRoles())){
            mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator(view3),
                    FragmnentInspiration.class, args);
        }else{

        }*/

    }

    private void selectTAB(int i) {

        View v = mTabHost.getTabWidget().getChildAt(i);
        WMImageView iv = (WMImageView)v.findViewById(R.id.imgTab);
        TextView tv=(TextView)v.findViewById(R.id.txtTab);

        if(i==mTabHost.getCurrentTab()){

            iv.selected();
            v.setBackgroundColor(getResources().getColor(R.color.greenTheme));
            tv.setTextColor(Color.WHITE);
        }else{

            iv.normal();
            v.setBackgroundResource(R.drawable.gradient_bg);
            tv.setTextColor(Color.BLACK);
        }
    }


    // end of main class
}
