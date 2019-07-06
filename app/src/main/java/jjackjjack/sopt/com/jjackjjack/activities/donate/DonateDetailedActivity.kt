package jjackjjack.sopt.com.jjackjjack.activities.donate

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.View
import jjackjjack.sopt.com.jjackjjack.R
import jjackjjack.sopt.com.jjackjjack.activities.donate.adapter.DonateDetailedPagerAdapter
import jjackjjack.sopt.com.jjackjjack.activities.donate.fragment.detail.DetailFragmentAdapter
import jjackjjack.sopt.com.jjackjjack.utillity.Constants
import kotlinx.android.synthetic.main.activity_donate_detailed.*
import kotlinx.android.synthetic.main.header_img.*
import kotlinx.android.synthetic.main.li_state.*
import kotlin.properties.Delegates

class DonateDetailedActivity : AppCompatActivity() {

    var fragmentAdapter: DetailFragmentAdapter by Delegates.notNull()

    private fun changeFragment(what:Int){
        fragmentAdapter.setFragment(what)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate_detailed)

        btn_toolbar_back.setOnClickListener {
            finish()
        }

        li_state_berry_num.text = intent.getStringExtra("berry_num")
        li_state_d_day.text = intent.getStringExtra("d_day")
        li_state_percent.text = intent.getStringExtra("percent")
        li_state_progress.progress = intent.getStringExtra("percent").toInt()
        donate_detailed_title.text = intent.getStringExtra("title")
        donate_detailed_association.text = intent.getStringExtra("association")

        donate_detailed_tab.addTab(donate_detailed_tab.newTab().setText("기부스토리"))
        donate_detailed_tab.addTab(donate_detailed_tab.newTab().setText("사용계획"))

        donate_detailed_tab.getTabAt(0)?.select()

        fragmentAdapter = DetailFragmentAdapter(supportFragmentManager, donate_detailed_tab)
        donate_detailed_tab.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    Constants.TAB_DONATE_STORY->{
                        fragmentAdapter.setFragment(Constants.FRAGMENT_DONATE_STORY)
                    }
                    Constants.TAB_USE_PLAN->{
                        fragmentAdapter.setFragment(Constants.FRAGMENT_USE_PLAN)
                    }
                }
            }
        })

//        if(!intent.getBooleanExtra("isDonateHistory", false)){
//            var main_adapter = DonateDetailedPagerAdapter(supportFragmentManager)
//            donate_detailed_pager.adapter = main_adapter
//            donate_detailed_tab.setupWithViewPager(donate_detailed_pager)
//            donate_detailed_tab.getTabAt(0)?.setText("기부스토리")
//            donate_detailed_tab.getTabAt(1)?.setText("사용계획")
//            //donate_step_scroll.visibility = View.GONE
//
//        }
//        else if(intent.getBooleanExtra("isDonateHistory", false)){
//            donate_detailed_pager.visibility = View.GONE
//            donate_detailed_tab.visibility = View.GONE
//            donate_detailed_button_layout.visibility = View.GONE
//           // donate_step_scroll.visibility = View.VISIBLE
//        }



        btn_donate.setOnClickListener {
            val intent = Intent(this, DonatePaymentActivity::class.java)
            startActivity(intent)
        }
    }
}