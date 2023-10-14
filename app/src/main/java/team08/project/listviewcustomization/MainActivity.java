package team08.project.listviewcustomization;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {
    private final int MAX_ROWS_PER_PAGE = 5;
    private TextView txtChosenUser;
    private Button btnGenerateUsers;
    private EditText edtNumberOfUsers;
    private Spinner spinnerPage;
    private ListView lvUsers;
    private List<User> usersData;
    private List<User> beingDisplayedUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtChosenUser = findViewById(R.id.txtChosenUser);
        btnGenerateUsers = findViewById(R.id.btnGenerateRows);
        edtNumberOfUsers = findViewById(R.id.edtNumberOfUsers);
        spinnerPage = findViewById(R.id.spinnerPage);
        lvUsers = findViewById(R.id.lvUsers);

        generateUsers();

        btnGenerateUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateUsers();
            }
        });

        spinnerPage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loadPage(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                /// TODO: nothing - needed by the interface
            }
        });

        lvUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = "You choose: " + beingDisplayedUsers.get(i).getName();
                txtChosenUser.setText(text);
            }
        });
    }

    private void generateUsers() {
        String strNumberOfRows = edtNumberOfUsers.getText().toString();

        if (!strNumberOfRows.isEmpty()) {
            int numberOfRows = Integer.parseInt(strNumberOfRows);
            usersData = User.getRandomUsers(numberOfRows);

            int numberOfPages = (int) Math.ceil(numberOfRows * 1.0 / MAX_ROWS_PER_PAGE);
            String[] pages = new String[numberOfPages];
            for (int i = 0; i < numberOfPages; i++) {
                pages[i] = "No. " + (i + 1);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, pages);
            spinnerPage.setAdapter(adapter);

            loadPage(0); // After generation, load the first page
            txtChosenUser.setText("You choose: ");
        }
    }

    private void loadPage(int pageNumber) {
        int from = MAX_ROWS_PER_PAGE * pageNumber;
        int to = Math.min(MAX_ROWS_PER_PAGE * (pageNumber + 1), usersData.size());
        beingDisplayedUsers = usersData.subList(from, to);
        CustomUserContactAdapter adapter = new CustomUserContactAdapter(this, R.layout.layout_user_row, beingDisplayedUsers);
        lvUsers.setAdapter(adapter);
    }
}