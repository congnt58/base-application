package com.example.cong_nt.myappbase.progressbar.style;

import android.animation.ValueAnimator;

import com.example.cong_nt.myappbase.progressbar.animation.SpriteAnimatorBuilder;
import com.example.cong_nt.myappbase.progressbar.sprite.CircleSprite;

/**
 * Created by ybq.
 */
public class Pulse extends CircleSprite {

    public Pulse() {
        setScale(0f);
    }

    @Override
    public ValueAnimator onCreateAnimation() {
        float fractions[] = new float[]{0f, 1f};
        return new SpriteAnimatorBuilder(this).
                scale(fractions, 0f, 1f).
                alpha(fractions, 255, 0).
                duration(1000).
                easeInOut(fractions)
                .build();
    }
}
