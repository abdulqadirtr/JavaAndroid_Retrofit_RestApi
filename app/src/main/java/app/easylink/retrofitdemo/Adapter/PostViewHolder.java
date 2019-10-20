package app.easylink.retrofitdemo.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import app.easylink.retrofitdemo.R;


public class PostViewHolder extends RecyclerView.ViewHolder {
TextView txt_title, txt_content,txt_author;
    public PostViewHolder(@NonNull View itemView) {
        super(itemView);

        txt_title=(TextView)itemView.findViewById(R.id.txt_title);
        txt_content=(TextView)itemView.findViewById(R.id.txt_content);
        txt_author=(TextView)itemView.findViewById(R.id.txt_author);
    }
}
