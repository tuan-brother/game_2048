package com.example.game2048

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.example.game2048.databinding.BlockViewLayoutBinding

/**
 * Created by shen on 17/5/5.
 */

class BlockView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    lateinit var mBlockText: TextView
    lateinit var mCenterBlock: FrameLayout
    lateinit var gameConfig: GameConfig

    var point: Point = Point()
    private var value: Int = 0;

    var needRemoveView: Boolean = false
    var removeTranslation: Float = 0F

    override fun onFinishInflate() {
        super.onFinishInflate()
        init()
    }

    init {
        inflate(context, R.layout.block_view_layout, this)
        mBlockText = this.findViewById(R.id.blockText)
        mCenterBlock = this.findViewById(R.id.centerBlock)
    }

    fun init() {

    }

    fun getValue(): Int {
        return this.value;
    }

    fun setNumber(value: Int) {
        this.value = value
        mBlockText.text = "" + value
//        var color = 0
//        if (Build.VERSION.SDK_INT >= 23) {
//            color = context.getColor(gameConfig.getBlockTextColor(value))
//        } else {
//            context.resources.getColor(gameConfig.getBlockTextColor(value))
//        }
//        mBlockText.setTextColor(color)
//
//        mCenterBlock.setBackgroundResource(gameConfig.getBlockBg(value))
    }

    fun setDataPoint(point: Point) {
        this@BlockView.point = point
    }

    companion object {
        fun create(context: Context, point: Point): BlockView {
            val blockView = LayoutInflater.from(context)
                .inflate(R.layout.block_view_layout, null, false) as BlockView

            blockView.point = point

            return blockView;
        }
    }

}