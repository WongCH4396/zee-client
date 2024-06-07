package tech.gamesupport.woo.iapi.bean;

public class ObjDiff<T> {

    private T before;
    private T after;

    public static <T> ObjDiff<T> from(T before, T after) {
        ObjDiff<T> objDiff = new ObjDiff<>();
        objDiff.setBefore(before);
        objDiff.setAfter(after);
        return objDiff;
    }

    public T getBefore() {
        return before;
    }

    public void setBefore(T before) {
        this.before = before;
    }

    public T getAfter() {
        return after;
    }

    public void setAfter(T after) {
        this.after = after;
    }
}
