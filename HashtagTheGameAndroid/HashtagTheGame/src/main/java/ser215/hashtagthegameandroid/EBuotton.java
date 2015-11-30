package ser215.hashtagthegameandroid;
import android.content.*;
import android.view.*;
import android.app.*;
import android.widget.*;
import android.util.*;
/**
 * RUUSIA RUSSIA RUUSIA ROOSIA RUSSIA RUSSIA RUSSIA, in soviet
 * pronouce ebuotton like 'ebutton' cept with a russian accent
 */
public class EBuotton extends Button{
    private int parentField;
    private int referenceTile;
    public EBuotton(Context context,int p,int t){
        super(context);
        parentField = p;
        referenceTile = t;
    }
    public EBuotton(Context context, AttributeSet attrs,int p,int t){
        super(context,attrs);
        parentField = p;
        referenceTile = t;
    }
}
