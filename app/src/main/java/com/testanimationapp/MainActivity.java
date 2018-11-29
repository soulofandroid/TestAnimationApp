package com.testanimationapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView image,image2,image3,image4,image5,image6,image7;
    Button btStart;
    Button btCancel;
    AnimationSet animationSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image=findViewById(R.id.imageView);
        image2=findViewById(R.id.imageView2);
        image3=findViewById(R.id.imageView3);
        image4=findViewById(R.id.imageView4);
        image5=findViewById(R.id.imageView5);
        image6=findViewById(R.id.imageView6);
        image7=findViewById(R.id.imageView7);
        btStart=findViewById(R.id.bt1);
        btCancel=findViewById(R.id.bt2);

        final Animation alphaAniamtion = new AlphaAnimation(1.0f,0);
        alphaAniamtion.setFillAfter(false);
        alphaAniamtion.setDuration(1500);
        alphaAniamtion.setRepeatCount(-1);

       final ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0.1f, 1, 0.1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setFillBefore(false);
        scaleAnimation.setRepeatCount(-1);

//        final TranslateAnimation translateAnimation=new TranslateAnimation(
//                Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,
//                0.5f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,1.0f);
        final TranslateAnimation translateAnimation=new TranslateAnimation(
                Animation.ABSOLUTE,0,Animation.ABSOLUTE,
                750,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f);
        translateAnimation.setDuration(4000);
        translateAnimation.setFillAfter(true);
        translateAnimation.setFillBefore(true);
        translateAnimation.setRepeatCount(-1);
        // 使用插值器 LinearInterpolator（匀速）、AccelerateInterpolator（加速）、AccelerateDecelerateInterpolator（先加速再减速）、BounceInterpolator（反弹数次后停止）、DecelerateInterpolator（减速）
        translateAnimation.setInterpolator(new AccelerateInterpolator());//设置一个加速的插值器

        
        final RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 1f);

        rotateAnimation.setDuration(1000);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setFillBefore(false);
        rotateAnimation.setRepeatCount(-1);

        createAnimationSet();

        //弹跳动画    插值器实现
        final TranslateAnimation down = new TranslateAnimation(0, 0, 0, 150);//位移动画，从button的上方300像素位置开始
        down.setFillAfter(true);
        down.setInterpolator(new BounceInterpolator());//弹跳动画,要其它效果的当然也可以设置为其它的值
        down.setDuration(2000);//持续时间


        //闪烁动画
        final Animation animation = new AlphaAnimation(1,0.5f);
        animation.setDuration(500);//闪烁时间间隔
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);


        btStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                /** 开始动画 */
                image.startAnimation(alphaAniamtion);
                image2.startAnimation(scaleAnimation);
                image3.startAnimation(translateAnimation);
                image4.startAnimation(rotateAnimation);
//                image5.startAnimation(animationSet);
                image6.startAnimation(down);//设置按钮运行该动画效果
                image7.startAnimation(animation);//设置按钮运行该动画效果
                alphaAniamtion.start();
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /** 结束动画 */
                alphaAniamtion.cancel();
                scaleAnimation.cancel();
                translateAnimation.cancel();
                rotateAnimation.cancel();
                down.cancel();
                animation.cancel();
//                animationSet.cancel();
                image.setAlpha(1.0f);
            }
        });
    }





    /**
     * 创建组合动画
     */
    private void createAnimationSet(){
        final AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 0.5f, 1f, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.ABSOLUTE, 100);    animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(translateAnimation);
        animationSet.setDuration(5000);
        animationSet.setFillAfter(true);//设置动画执行后保持最后状态
        animationSet.setFillBefore(false);//设置动画执行后不回到原来状态
        animationSet.setRepeatCount(-3);//这样设置无作用，所以在回调中重新启动动画
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                image5.startAnimation(animationSet);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        image5.startAnimation(animationSet);
    }


}
