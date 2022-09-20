package com.zxy.canvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View


class DevSeekBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var mPaintBg: Paint = Paint()
    private var mPaintSel: Paint = Paint()
    private var mPaintTxt: Paint = Paint()
    private var mPaintHint: Paint = Paint()
    private var mWidth = 360f
    private var mHeight = 132f
    private var dragX = 0f
    var onScrollFinish: OnScrollFinish? = null
    private var progress = 0f
    private var imgBitMap: Bitmap? = null
    private var bitmaph: Int = 0
    private var txtName: String = "空调"
    private var txtHint: String = "亮度23%"
    private var txtTemp: String = "29c"
    private var txtSize: Int = 22

    init {
        mPaintBg.isAntiAlias = true
        mPaintBg.color = Color.parseColor("#cc363636")

        mPaintSel.isAntiAlias = true
        mPaintSel.color = Color.parseColor("#cc556E84")

        mPaintTxt.isAntiAlias = true
        mPaintTxt.color = Color.WHITE
        mPaintTxt.textSize = txtSize.toFloat()


        mPaintHint.isAntiAlias = true
        mPaintHint.color = Color.WHITE
        mPaintHint.textSize = 15f

        var bitmap = BitmapFactory.decodeResource(resources, R.mipmap.iconpark_air_conditioning)
        bitmap?.apply {
            imgBitMap = resizeBitmap(this, 40, 40)
        }
    }

    fun resizeBitmap(bitmap: Bitmap, w: Int, h: Int): Bitmap? {
        val width = bitmap.width
        val height = bitmap.height
        val scaleWidth = w.toFloat() / width
        val scaleHeight = h.toFloat() / height
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        return Bitmap.createBitmap(
            bitmap, 0, 0, width,
            height, matrix, true
        )
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {

            }

            MotionEvent.ACTION_MOVE -> {
                dragX = event.x
                if (dragX > 0 && dragX <= mWidth) {
                    invalidate()
                    progress = (dragX / 3.6).toFloat()
                    Log.e("DevSeekBar", "ACTION_MOVE: ${dragX / 3.6}")
                }

            }

            MotionEvent.ACTION_UP -> {
                onScrollFinish?.apply {
                    onScroll(progress)
                }
            }

            MotionEvent.ACTION_CANCEL -> {

            }

        }
        return true
    }


    fun setValue(txtName: String, txtHint: String, txtTemp: String) {
        this.txtName = txtName
        this.txtHint = txtHint
        this.txtTemp=txtTemp
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            var rectSel = RectF(0f, 0f, dragX, mHeight)
            canvas.drawRect(rectSel, mPaintSel)

            var rectBg = RectF(dragX, 0f, mWidth, mHeight)
            canvas.drawRect(rectBg, mPaintBg)

            imgBitMap?.apply {
                canvas.drawBitmap(this, 25f, (mHeight - 40) / 2f, mPaintBg)
            }

            canvas.drawText(txtName, 83f, (mHeight / 2) - txtSize, mPaintTxt)

            canvas.drawText(txtHint, 83f, (mHeight / 2) + txtSize, mPaintHint)

            canvas.drawText(txtTemp, mWidth-100, (mHeight / 2) - txtSize, mPaintHint)

        }
    }
}

interface OnScrollFinish {
    fun onScroll(progress: Float)
}