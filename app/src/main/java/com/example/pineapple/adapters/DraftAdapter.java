package com.example.pineapple.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pineapple.R;
import com.example.pineapple.beans.Drafts;
import com.example.pineapple.beans.PicCompress;

import java.util.List;

public class DraftAdapter extends BaseAdapter {
    private Context context;
    private List<Drafts> draftsList;
    public DraftAdapter(Context context, List<Drafts> draftsList) {
        this.context = context;
        this.draftsList = draftsList;
    }
    @Override
    public int getCount() {
        return this.draftsList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        DraftAdapter.ViewHolder viewHolder;
        if (convertView == null){
            convertView = View .inflate(this.context, R.layout.item_essays,null);
            viewHolder = new ViewHolder();
            viewHolder.essays_user_name = convertView.findViewById(R.id.essays_user_name);
            viewHolder.essays_title = convertView.findViewById(R.id.essays_title);
            viewHolder.essays_writing = convertView.findViewById(R.id.essays_writing);
            viewHolder.essays_user_head = convertView.findViewById(R.id.essays_user_head);
            viewHolder.essays_image_1 = convertView.findViewById(R.id.essays_image_1);
            viewHolder.essays_image_2 = convertView.findViewById(R.id.essays_image_2);
            viewHolder.essays_image_3 = convertView.findViewById(R.id.essays_image_3);
            convertView.setTag(viewHolder);

        }else{
            viewHolder  = (ViewHolder) convertView.getTag();
        }

        viewHolder.essays_user_name.setText(this.draftsList.get(position).getUsername().trim());
        viewHolder.essays_title.setText(this.draftsList.get(position).getTitle().trim());
        viewHolder.essays_writing.setText(this.draftsList.get(position).getEssay().trim());
        PicCompress picCompress = new PicCompress();
        String userhead = this.draftsList.get(position).getUserhead();
        Bitmap userheadbitmap = picCompress.decodeAndDecompressBase64ToImage(userhead);
        viewHolder.essays_user_head.setImageBitmap(userheadbitmap);
        String photo1 = this.draftsList.get(position).getPhoto1();
        Bitmap photobitmap = picCompress.decodeAndDecompressBase64ToImage(photo1);
        viewHolder.essays_image_1.setImageBitmap(photobitmap);
        String photo2 = this.draftsList.get(position).getPhoto2();
        if (photo2 == null){
            viewHolder.essays_image_2.setImageResource(R.drawable.write);
        }else {
            Bitmap photobitmap2 = picCompress.decodeAndDecompressBase64ToImage(photo2);
            viewHolder.essays_image_2.setImageBitmap(photobitmap2);
        }
        String photo3 = this.draftsList.get(position).getPhoto3();
        if (photo3 == null){
            viewHolder.essays_image_3.setImageResource(R.drawable.write);
        }else {
            Bitmap photobitmap3 = picCompress.decodeAndDecompressBase64ToImage(photo3);
            viewHolder.essays_image_3.setImageBitmap(photobitmap3);
        }
        return convertView;
    }
    private static class ViewHolder{

        TextView essays_user_name;
        TextView essays_title;
        TextView essays_writing;
        ImageView essays_user_head;
        ImageView essays_image_1;
        ImageView essays_image_2;
        ImageView essays_image_3;

    }
}