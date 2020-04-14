package com.supplier.sl_delivery.ui.fragments.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.supplier.sl_delivery.R
import com.supplier.sl_delivery.ui.activities.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * Created by Aurora on 2020-03-30.
 */
class HomeFragment : BaseFragment() {

    companion object {
        fun getInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPieChart.setUsePercentValues(true)
        mPieChart.description.isEnabled = false
        mPieChart.legend.isEnabled = false
        val entries: ArrayList<PieEntry> = ArrayList()
        entries.add(
            PieEntry(
                85.00f,
                "",
                resources.getDrawable(R.drawable.ic_orders)
            )
        )
        entries.add(
            PieEntry(
                15.00f,
                "",
                resources.getDrawable(R.drawable.ic_orders)
            )
        )

        val colors: ArrayList<Int> = ArrayList()
        for (c in ColorTemplate.MATERIAL_COLORS) colors.add(c)
        for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)

        val dataSet = PieDataSet(entries, "")
        dataSet.colors = colors
        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f;
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter(mPieChart))
        data.setValueTextSize(11f)
        data.setValueTextColor(activity!!.resources.getColor(R.color.general_text_color))
        mPieChart.data = data
        // undo all highlights
        mPieChart.highlightValues(null)
        mPieChart.labelFor = 0
        mPieChart.invalidate()
        /*val circularGauge = AnyChart.circular()
        circularGauge.fill("#fff")
            .stroke(null)
            .padding(0, 0, 0, 0)
            .margin(30, 30, 30, 30)
        circularGauge.startAngle(0)
            .sweepAngle(360)

        val currentValue = 13.8
        circularGauge.data(SingleValueDataSet(arrayOf(currentValue)))

        circularGauge.axis(0)
            .startAngle(-150)
            .radius(80)
            .sweepAngle(300)
            .width(3)
            .ticks("{ type: 'line', length: 4, position: 'outside' }")

        circularGauge.axis(0).labels().position("outside")

        circularGauge.axis(0).scale()
            .minimum(0)
            .maximum(140)

        circularGauge.axis(0).scale()
            .ticks("{interval: 10}")
            .minorTicks("{interval: 10}")

        circularGauge.needle(0)
            .stroke(null)
            .startRadius("6%")
            .endRadius("38%")
            .startWidth("2%")
            .endWidth(0)

        circularGauge.cap()
            .radius("4%")
            .enabled(true)
            .stroke(null)

        circularGauge.label(0)
            .text("<span style=\"font-size: 25\">Wind Speed</span>")
            .useHtml(true)
            .hAlign(HAlign.CENTER)
        circularGauge.label(0)
            .anchor(Anchor.CENTER_TOP)
            .offsetY(100)
            .padding(15, 20, 0, 0)

        circularGauge.label(1)
            .text("<span style=\"font-size: 20\">$currentValue</span>")
            .useHtml(true)
            .hAlign(HAlign.CENTER)
        circularGauge.label(1)
            .anchor(Anchor.CENTER_TOP)
            .offsetY(-100)
            .padding(5, 10, 0, 0)
            .background("{fill: 'none', stroke: '#c1c1c1', corners: 3, cornerType: 'ROUND'}")

        circularGauge.range(
            0,
            """{
    from: 0,
    to: 25,
    position: 'inside',
    fill: 'green 0.5',
    stroke: '1 #000',
    startSize: 6,
    endSize: 6,
    radius: 80,
    zIndex: 1
  }"""
        )

        circularGauge.range(
            1,
            """{
    from: 80,
    to: 140,
    position: 'inside',
    fill: 'red 0.5',
    stroke: '1 #000',
    startSize: 6,
    endSize: 6,
    radius: 80,
    zIndex: 1
  }"""
        )

        mAnyChartView.setChart(circularGauge)*/
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
}
