import greenfoot.*;

public class HitJudgment extends Actor {
    private UFO ufo;

    public HitJudgment(UFO ufo) {
        this.ufo = ufo;

        
        getImage().scale( 15, 15 );
        getImage().setTransparency(0);

    }

    public void act() {
        // UFO がいなければ自分も消す
        if (ufo == null || ufo.getWorld() == null) {
            World w = getWorld();
            if (w != null) w.removeObject(this);
            return;
        }

        // ★ UFO と同じ位置に合わせる（= 同じ動き）
        setLocation(ufo.getX(), ufo.getY());

        // ★ 当たり判定（小惑星に触れたらゲームオーバー）
        if (isTouching(asteroid.class)) {
            ((MyWorld)getWorld()).gameOver();
        }

        // デバッグでテキストを出すなら：
        // getWorld().showText("ATARI", 100, 50);
        // （当たった/当たってないの表示をしたければ適宜切り替え）
    }
}
