package example.com.teamc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by ayijk on 2017/02/14.
 */

public class HitPointEvent {
    private static class Event implements Serializable {
        public final String message;
        public final int change;
        public final int iconId;

        public Event(String message, int change, int iconId) {
            this.message = message;
            this.change = change;
            this.iconId = iconId;
        }
    }

    private static Event[] events = new Event[]{
            new Event("送電設備が故障した", -20, R.mipmap.denjiha),
            new Event("隣りに座ってるおばさんの寝相が悪い", -3, R.mipmap.sleep_nezou_warui),
            new Event("通勤ラッシュめっちゃ辛い", -10, R.mipmap.train_manin_business),
            new Event("居眠りで往復してしまった（高幡不動で起きた）", -10, R.mipmap.pose_inemuri),
            new Event("つり革から手を離した瞬間に揺れる", -10, R.mipmap.densya_tsurikawa),
            new Event("点字マットが程よい足ツボマッサージになる", 5, R.mipmap.massage_ashimomi),
            new Event("男なのに痴漢される。", -5, R.mipmap.chikan_enzai),
            new Event("通勤ラッシュで座れた", 10, R.mipmap.train_people),
    };

    public static int eventOccurs(String stationName, AppCompatActivity activity) {
        Event ret = events[new Random().nextInt(events.length)];

        Bundle args = new Bundle();
        args.putSerializable("event", ret);
        args.putString("stationName", stationName);

        EventDialog dialog = new EventDialog();
        dialog.setArguments(args);
        dialog.show(activity.getSupportFragmentManager(), null);

        return ret.change;
    }

    public static class EventDialog extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Bundle args = getArguments();
            String title = args.getString("stationName");
            Event event = (Event) args.getSerializable("event");
            String message = event.message;
            int change = event.change;
            int iconId = event.iconId;

            LinearLayout ll = new LinearLayout(getActivity());
            ll.setOrientation(LinearLayout.VERTICAL);
            TextView tx = new TextView(getActivity());
            tx.setText(message + "\n" + change + "点です！");
            tx.setTextSize(30);
            tx.setTextColor(Color.RED);
            tx.setGravity(Gravity.CENTER_HORIZONTAL);
            ll.addView(tx, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            ImageView img = new ImageView(getActivity());
            img.setImageResource(iconId);
            ll.addView(img);

            Dialog dialog = new AlertDialog.Builder(getActivity())
                    //.setPositiveButton("O.K.", null)
                    //.setNegativeButton("Cancel", null)
                    .setTitle("イベント発生：" + title)
                    //.setMessage(message)
                    .setView(ll)
                    .create();

            return dialog;
        }
    }

}
