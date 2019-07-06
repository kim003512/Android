package jjackjjack.sopt.com.jjackjjack.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import jjackjjack.sopt.com.jjackjjack.R
import jjackjjack.sopt.com.jjackjjack.activities.berrycharge.BerryChargeActivity
import jjackjjack.sopt.com.jjackjjack.activities.donate.DonateActivity
import jjackjjack.sopt.com.jjackjjack.activities.donaterecord.DonateRecordActivity
import jjackjjack.sopt.com.jjackjjack.activities.mypage.MyPageActivity
import jjackjjack.sopt.com.jjackjjack.activities.rank.RankActivity
import jjackjjack.sopt.com.jjackjjack.interfaces.onDrawer
import jjackjjack.sopt.com.jjackjjack.utillity.Constants
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_activity_main.*
import kotlinx.android.synthetic.main.nav_drawer.*
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity(), onDrawer {

    lateinit var btnFset: Array<ImageView>

    lateinit var btnAset: Array<View>

    lateinit var actSet : Array<Class<out AppCompatActivity>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fragmentAdapter = MainActivityImageSliderAdapter(supportFragmentManager)
        main_activity_slider_pager.adapter = fragmentAdapter

        main_activity_slider_pager.setClipToPadding(false)
        val dpValue = 40
        val d = resources.displayMetrics.density
        val margin = (dpValue * d).toInt()
        main_activity_slider_pager.setPadding(margin, 0, margin, 0)
        main_activity_slider_pager.setPageMargin(margin / 2)

        for (i in 0..5) {
            val fragmentMainActivityImageSlider = FragmentMainActivityImageSlider()
            fragmentAdapter.addImage(fragmentMainActivityImageSlider)
        }
        fragmentAdapter.notifyDataSetChanged()




//        pageIndicatorView.setViewPager(main_activity_slider_pager, true)
//
//        main_activity_slider_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {/*empty*/
//            }
//
//            override fun onPageSelected(position: Int) {
//                pageIndicatorView.selection = position
//            }
//
//            override fun onPageScrollStateChanged(state: Int) {/*empty*/
//            }
//        })

        //dot_indicator_activity_main.setupWithViewPager(main_activity_slider_pager, true)

        drawerUI()
    }

    override fun drawerUI(){

        actSet = arrayOf(
            MainActivity::class.java, DonateRecordActivity::class.java,
            RankActivity::class.java, MyPageActivity::class.java,
            BerryChargeActivity::class.java
        )

        btnFset = arrayOf( //프래그먼트로 가는 버튼
            btn_drawer_f_child, btn_drawer_f_senior, btn_drawer_f_animal, btn_drawer_f_disabled,
            btn_drawer_f_env, btn_drawer_f_emergency
        )

        btnAset = arrayOf(
            btn_drawer_home, btn_drawer_donate_record, btn_drawer_rank,
            btn_drawer_mypage, btn_drawer_berrycharge
        )

        drawerBtnSetting(Constants.ACTIVITY_MAIN)

    }


    override fun drawerBtnSetting(activityType: Int){
        btn_hambuger.setOnClickListener {
            if(!ly_drawer.isDrawerOpen(Gravity.END)){
                ly_drawer.openDrawer(Gravity.END)
            }
        }

        btn_cancel.setOnClickListener {
            if(ly_drawer.isDrawerOpen(Gravity.END)){
                ly_drawer.closeDrawer(Gravity.END)
            }
        }


        for(i in 0 until btnAset.size){
            btnAset[i].setOnClickListener{
                val intent = Intent(this, actSet[i])
                startActivity(intent)
                ly_drawer.closeDrawer(Gravity.END)
                if(activityType == i){
                    finish()
                }
            }
        }

        for(i in 0 until btnFset.size){
            btnFset[i].setOnClickListener {
                startActivity<DonateActivity>("fragment" to i)
                ly_drawer.closeDrawer(Gravity.END)
                if(activityType==Constants.ACTIVITY_DONATE){
                    finish()
                }
            }
        }
    }

    override fun onBackPressed() {
        if(ly_drawer.isDrawerOpen(Gravity.END)){
            ly_drawer.closeDrawer(Gravity.END)
        }
        else{
            super.onBackPressed()
        }
    }
}