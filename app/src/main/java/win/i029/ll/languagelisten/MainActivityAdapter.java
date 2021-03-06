package win.i029.ll.languagelisten;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lvh on 6/28/17.
 */

public class MainActivityAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private PlayControl mPlayControl;
    private Context mContext;


    public MainActivityAdapter(Context context ) {
        this.mInflater = LayoutInflater.from(context);
        mPlayControl = PlayControl.getInstance();
        mContext = context;
    }


    @Override
    public int getCount() {
        return mPlayControl.getPlaylistCount();
    }

    @Override
    public View getView(final int position, View contentView, ViewGroup parent) {
        ViewHolder holder;

        if (contentView == null) {
            contentView = mInflater.inflate(R.layout.playlist_item, null);
            holder = new ViewHolder();

            holder.title = (TextView) contentView.findViewById(R.id.playlist_ItemTitle);
            holder.remove = (ImageButton) contentView.findViewById(R.id.playlist_Remove);

            contentView.setTag(holder);
        } else {
            holder = (ViewHolder) contentView.getTag();
        }
        PlayControl.PlaylistItem item = mPlayControl.getPlaylistItem(position);

        holder.title.setText(item.getTitle());

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("Tag", " position : " + position);
                new AlertDialog.Builder(mContext).setMessage(R.string.query_delete)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                mPlayControl.delPlaylistItem(position);
                                MainActivityAdapter.this.notifyDataSetChanged();

                            }

                        })
                        .setNegativeButton(android.R.string.cancel, null).show();


            }
        });

        if( mPlayControl.getActiveListPos() == position ) {
            holder.title.setTextColor(0xFF99CC33);
            //holder.remove.setEnabled(false);
        } else {
            holder.title.setTextColor(0xFF000000);
            //holder.remove.setEnabled(true);
        }


        holder.remove.setFocusable(false); // enable ListView OnItemClickListener

        return contentView;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public final class ViewHolder {
        public TextView title;
        public ImageButton remove;
    }


}

