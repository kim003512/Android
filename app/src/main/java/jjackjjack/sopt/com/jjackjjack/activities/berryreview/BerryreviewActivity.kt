package jjackjjack.sopt.com.jjackjjack.activities.berryreview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import jjackjjack.sopt.com.jjackjjack.R
import jjackjjack.sopt.com.jjackjjack.data.DonateUsePlanData
import jjackjjack.sopt.com.jjackjjack.list.DonateUsePlanRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_use_berry.*

class BerryreviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rank_berryreview)

        lateinit var donateUsePlanRecyclerViewAdapter: DonateUsePlanRecyclerViewAdapter
        var dataList: ArrayList<DonateUsePlanData> = ArrayList()

        dataList.add(
            DonateUsePlanData(
                "1", "입양지원 활동 및 입양진행", "30.000"
            )
        )
        dataList.add(
            DonateUsePlanData(
                "2", "위급한 유기견 대상 영양제 지원, 예방접종 진행", "19.000"
            )
        )
        dataList.add(
            DonateUsePlanData(
                "3", "약품 및 물품비용", "23.000"
            )
        )
        dataList.add(
            DonateUsePlanData(
                "4", "족발 대자 3개", "120.000"
            )
        )

        donateUsePlanRecyclerViewAdapter = DonateUsePlanRecyclerViewAdapter(this, dataList)
        rv_donate_use_container.adapter = donateUsePlanRecyclerViewAdapter
        rv_donate_use_container.layoutManager = LinearLayoutManager(this)
    }
}