package team08.project.listviewcustomization;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomUserContactAdapter extends ArrayAdapter<User> {
    private Context context;
    private int layoutToBeInflated;
    private List<User> userList;

    public CustomUserContactAdapter(Context context, int layoutToBeInflated, List<User> userList) {
        super(context, layoutToBeInflated, userList);
        this.context = context;
        this.layoutToBeInflated = layoutToBeInflated;
        this.userList = userList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View row = inflater.inflate(layoutToBeInflated, null);

        ImageView imgUser = row.findViewById(R.id.imgUser);
        TextView txtUsername = row.findViewById(R.id.txtUsername);
        TextView txtPhone = row.findViewById(R.id.txtPhone);

        imgUser.setImageResource(userList.get(position).getAvatar());
        txtUsername.setText(userList.get(position).getName());
        txtPhone.setText(userList.get(position).getPhoneNumber());

        return row;
    }
}
