package com.zxy.canvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View


class LightView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var TAG="LightView"


    override fun onDraw(canvas: Canvas?) {
        var mPaint=Paint()
        mPaint.isAntiAlias=true
        mPaint.color = Color.BLACK

        val bitmap = BitmapFactory.decodeResource(resources,R.mipmap.scence_dev_light_detail)
        val mBitWidth = bitmap.width
        val mBitHeight = bitmap.height

        val mSrcRect = Rect(0, 0, mBitWidth, mBitHeight)
        val left = width / 2 - mBitWidth / 2
        val top = height / 2 - mBitHeight / 2
        val mDestRect = Rect(left, top, left + mBitWidth, top + mBitHeight)

        Log.e(TAG, "onDraw: left=$left , top=$top , mBitWidth=$mBitWidth , mBitHeight=$mBitHeight , width=$width , height=$height", )

        canvas?.drawBitmap(bitmap, mSrcRect, mDestRect, mPaint)
//        canvas?.drawBitmap(BitmapFactory.decodeResource(resources,R.mipmap.scence_dev_light_detail),0f,0f,mPaint)

        val mRound = RectF(23F,377F ,(mBitWidth-23).toFloat() , 417f)

        canvas?.drawOval(mRound,mPaint)

        canvas?.drawCircle((mBitWidth/2).toFloat(),(383+42).toFloat(),42f,mPaint)

        super.onDraw(canvas)
    }
}