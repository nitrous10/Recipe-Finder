package com.example.cs65_final_project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cs65_final_project.Ingredient;
import com.example.cs65_final_project.R;

import java.util.List;

public class SearchFriendAdapter extends ArrayAdapter<String> {
    public SearchFriendAdapter(@NonNull Context context, @NonNull List<String> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String string = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.friend_search_text_view, parent, false);
        }

        //Get ingredient names
        TextView friendText = convertView.findViewById(R.id.friend_name);
        friendText.setText(string);

        return convertView;
    }

    public void updateData(List<String> data){
        clear();
        addAll(data);
    }
}
