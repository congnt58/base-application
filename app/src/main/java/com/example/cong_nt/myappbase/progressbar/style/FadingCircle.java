package com.example.cong_nt.myappbase.progressbar.style;
import android.animation.ValueAnimator;
import android.os.Build;

import com.example.cong_nt.myappbase.progressbar.animation.SpriteAnimatorBuilder;
import com.example.cong_nt.myappbase.progressbar.sprite.CircleSprite;
import com.example.cong_nt.myappbase.progressbar.sprite.CircleLayoutContainer;
import com.example.cong_nt.myappbase.progressbar.sprite.Sprite;

/**
 * Created by ybq.
 */
public class FadingCircle extends CircleLayoutContainer {

    @Override
    public Sprite[] onCreateChild() {
        Dot[] dots = new Dot[12];
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new Dot();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                dots[i].setAnimationDelay(1200 / 12 * i);
            } else {
                dots[i].setAnimationDelay(1200 / 12 * i + -1200);
            }
        }
        return dots;
    }

    private class Dot extends CircleSprite {

        Dot() {
            setAlpha(0);
        }

        @Override
        public ValueAnimator onCreateAnimation() {
            float fractions[] = new float[]{0f, 0.39f, 0.4f, 1f};
            return new SpriteAnimatorBuilder(this).
                    alpha(fractions, 0, 0, 255, 0).
                    duration(1200).
                    easeInOut(fractions).build();
        }
    }
}
