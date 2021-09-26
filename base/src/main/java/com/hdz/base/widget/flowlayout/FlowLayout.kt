package com.hdz.base.widget.flowlayout

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.hdz.base.R

class FlowLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    lateinit var mInflater: LayoutInflater

    // 最多可展示的View
    lateinit var mRowViews: ArrayList<ArrayList<View>>

    // 当前行的 View
    lateinit var currentViewList: ArrayList<View>

    // 记录每行的行高
    lateinit var lineHeights: ArrayList<Int>

    // 图片，一般情况都不为空
    lateinit var imgExpand: ImageView

    lateinit var recordList: ArrayList<Record>

    /**
     * 是否有图片，只有一种情况没有图片
     * 分析几种状态
     * 1.初次测量，添加 {@link ImageView},测量宽度高度，方便计算展开时的位置和收缩时的位置。
     * 2.记录展开时
     * 3.记录收缩时
     * 4.记录的数量没有填满并超过基础行（只有这种情况下，才会没有图片）
     */
    var mHasImg = false

    /**
     * 是否为初次测量
     */
    var mInitMeasure = true

    //初步测量的时候，未展开的情况下，ImageView应当存在的位置
    var closeExpandIndex = -1

    //展开的时候，ImageView展示的坐标---初步测量的时候，记录超过最大行，当数据超过基础行，但是不超过最大行
    // 的时候这个数一直为 0.所以这个数值并不是指所有的展开时候的位置
    var openExpandIndex = -1

    /**
     * 限制最大的行数
     */
    var limitCount = -1

    var mIsOverBaseLine = false

    var expandable = false

    /**
     * 基础行数
     */
    var baseCount = -1

    /**
     * 是否限制行数
     */
    var isLimit = false

    var onTagClickListener: OnTagClickListener? = null

    companion object {
        val DEFAULT_BASE_LINE = 2
        val DEFAULT_LIMIT_COUNT = 5
    }

    init {
        val array = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout)
        isLimit = array.getBoolean(R.styleable.FlowLayout_isLimit, false)
        baseCount =
            array.getInt(R.styleable.FlowLayout_baseCount, if (isLimit) DEFAULT_BASE_LINE else -1)
        limitCount =
            array.getInt(
                R.styleable.FlowLayout_limitCount,
                if (isLimit) DEFAULT_LIMIT_COUNT else -1
            )
        array.recycle()
    }

    interface OnTagClickListener {
        fun onTagClick(view: View, position: Int)
        fun onExpandClick(expand: Boolean)
    }


    fun setList(recordList: ArrayList<Record>) {
        removeAllViews()
        mRowViews.clear()
        mInitMeasure = true
        closeExpandIndex = 0
        openExpandIndex = 0
        expandable = false
        this.recordList = recordList
        mInflater = LayoutInflater.from(context)
        initChildList(recordList, recordList.size)
        imgExpand = mInflater.inflate(R.layout.base_img_flow_layout, this, false) as ImageView
        addView(imgExpand)
//        imgExpand.setImageResource()
        imgExpand.setOnClickListener { onTagClickListener?.onExpandClick(expandable) }
        requestLayout()
    }

    fun setList(recordList: ArrayList<Record>, hasImg: Boolean) {
        removeAllViews()
        mInitMeasure = false
        this.mHasImg = hasImg;
        this.recordList = recordList
        val index =
            if (!hasImg) recordList.size else if (!expandable) closeExpandIndex else if (openExpandIndex > 0) openExpandIndex else recordList.size
        initChildList(recordList, index)
        if (hasImg) {
            imgExpand = mInflater.inflate(R.layout.base_img_flow_layout, this, false) as ImageView
            addView(imgExpand)
//            val resId: Int = if (expandable) R.mipmap.search_close else R.mipmap.search_open
//            imgExpand.setImageResource(resId)
            imgExpand.setOnClickListener { onTagClickListener?.onExpandClick(expandable) }
        }
        requestLayout()
    }

    fun setExpand(expand: Boolean) {
        removeAllViews()
        mInflater = LayoutInflater.from(context)
        mInitMeasure = false
        mHasImg = true
        this.expandable = expand
        val index =
            if (!expandable) closeExpandIndex else if (openExpandIndex > 0) openExpandIndex else recordList.size
        initChildList(recordList, index)
        imgExpand = mInflater.inflate(R.layout.base_img_flow_layout, this, false) as ImageView
        addView(imgExpand)
//        val resId: Int = if (expandable) R.mipmap.search_close else R.mipmap.search_open
//        imgExpand.setImageResource(resId)
        imgExpand.setOnClickListener { onTagClickListener?.onExpandClick(expandable) }
    }


    fun initChildList(recordList: ArrayList<Record>, index: Int) {
        for (i in 0..index) {
            val record = recordList.get(i)
            val textChild: TextView =
                mInflater.inflate(R.layout.base_text_flow_layout, this, false) as TextView
            textChild.text = record.tag
            textChild.setOnClickListener(object : OnClickListener {
                override fun onClick(v: View?) {
                    onTagClickListener?.onTagClick(textChild, i)
                }
            })
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var top = 0
        var left = 0
        var cl: Int
        var ct: Int
        var cr: Int
        var cb: Int
        for (i in mRowViews.indices) {
            val viewList: List<View> = mRowViews[i]
            for (j in viewList.indices) {
                val child = viewList[j]
                val params = child.layoutParams as MarginLayoutParams
                cl = left + params.leftMargin
                ct = top + params.topMargin
                cr = cl + child.measuredWidth
                cb = ct + child.measuredHeight
                child.layout(cl, ct, cr, cb)
                left = cr + params.rightMargin
            }
            top += lineHeights[i]
            left = 0
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        mRowViews.clear()
        lineHeights.clear()
        currentViewList.clear()
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        val modeH = MeasureSpec.getMode(heightMeasureSpec)
        var curLineWidth = 0
        var curLineHeight = 0
        var curLineCount = 1
        var totalHeight = 0
        val childCount = childCount
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            val childWidth = child.measuredWidth
            val childHeight = child.measuredHeight
            val params = child.layoutParams as MarginLayoutParams
            val leftMargin = params.leftMargin
            val topMargin = params.topMargin
            val rightMargin = params.rightMargin
            val bottomMargin = params.bottomMargin
            var childViewWidth = childWidth + leftMargin + rightMargin
            val childViewHeight = childHeight + topMargin + bottomMargin
            // TODO: 2021-08-26   判断子控件的宽是否大于父控件的宽，当大于父控件的宽度时，子控件、
            //  显示不完整 ，所以需要重新设置宽度，同时子控件的内容应该添加 android:ellipsize="end" 属性
            if (childViewWidth > width) {
                val param = child.layoutParams
                params.width = width - params.leftMargin - params.rightMargin
                child.layoutParams = param
                childViewWidth = params.width + params.leftMargin + params.rightMargin
            }
            // TODO: 2021-08-27   测量基础行队尾添加 ImageView 的情况和限制行队尾添加 ImageView 的情况。
            if (curLineWidth + childViewWidth > width) {
                // TODO: 2021-08-27   1.标注特殊行   基础行、限制行
                if (curLineCount == baseCount && mInitMeasure) {  //基础行并且处于第一次测量阶段
                    val imgChild = getChildAt(childCount - 1) as ImageView
                    measureChild(imgChild, widthMeasureSpec, heightMeasureSpec)
                    val imgParams = child.layoutParams as MarginLayoutParams
                    val imgSpaceWidth =
                        imgChild.measuredWidth + imgParams.leftMargin + imgParams.rightMargin
                    closeExpandIndex = if (imgSpaceWidth + curLineWidth > width) i - 1 else i
                } else if (curLineCount == limitCount && mInitMeasure) {  //限制行并且处于第一次测量阶段
                    val imgChild = getChildAt(childCount - 1) as ImageView
                    measureChild(imgChild, widthMeasureSpec, heightMeasureSpec)
                    val imgParams = child.layoutParams as MarginLayoutParams
                    val imgSpaceWidth =
                        imgChild.measuredWidth + imgParams.leftMargin + imgParams.rightMargin
                    openExpandIndex = if (imgSpaceWidth + curLineWidth > width) i - 1 else i
                }
                // TODO: 2021-08-27   换行
                totalHeight += curLineHeight
                mRowViews.add(currentViewList)
                lineHeights.add(curLineHeight)
                currentViewList = java.util.ArrayList()
                currentViewList.add(child)
                curLineHeight = childViewHeight
                curLineWidth = childViewWidth
                curLineCount++
            } else {
                curLineHeight = Math.max(curLineHeight, childViewHeight)
                curLineWidth += childViewWidth
                currentViewList.add(child)
            }
            if (i == childCount - 1) {
                totalHeight += curLineHeight
                lineHeights.add(curLineHeight)
                mRowViews.add(currentViewList)
            }
        }
        mHasImg =
            mInitMeasure && mRowViews.size > baseCount && mRowViews[baseCount][0] !is ImageView
        mIsOverBaseLine = mHasImg
        // 宽度可以不用考虑 主要考虑高度
        // 宽度可以不用考虑 主要考虑高度
        if (modeH == MeasureSpec.EXACTLY) {
            setMeasuredDimension(width, height)
        } else {
            setMeasuredDimension(width, totalHeight)
        }
    }
}