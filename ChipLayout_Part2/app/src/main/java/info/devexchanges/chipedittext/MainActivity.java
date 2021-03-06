package info.devexchanges.chipedittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tokenautocomplete.TokenCompleteTextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TokenCompleteTextView.TokenListener<SimpleContact> {

    private ArrayList<SimpleContact> contacts;
    private FilterAdapter filterAdapter;
    private ContactsCompletionView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setSampleContact();

        autoCompleteTextView = (ContactsCompletionView) findViewById(R.id.autocomplete_textview);

        //Initializing and attaching adapter for AutocompleteTextView
        filterAdapter = new FilterAdapter(this, R.layout.item_contact, contacts);
        autoCompleteTextView.setAdapter(filterAdapter);

        //Set the listener that will be notified of changes in the Tokenlist
        autoCompleteTextView.setTokenListener(this);

        //Set the action to be taken when a Token is clicked
        autoCompleteTextView.setTokenClickStyle(TokenCompleteTextView.TokenClickStyle.Select);

        final TextView inputContent = (TextView) findViewById(R.id.input_content);
        View btnGet = findViewById(R.id.btn_get);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<SimpleContact> tokens = autoCompleteTextView.getObjects();
                StringBuilder content = new StringBuilder();
                for (int i = 0; i < tokens.size(); i++) {
                    content.append(tokens.get(i)).append("; ");
                }
                inputContent.setText(String.format("You choose: %s", content.toString()));
            }
        });
    }

    private void setSampleContact() {
        contacts = new ArrayList<>();
        contacts.add(new SimpleContact(R.drawable.female, "Thanh Ngan", "ngan@gmail.com"));
        contacts.add(new SimpleContact(R.drawable.male, "Quang Minh", "minh@gmail.com"));
        contacts.add(new SimpleContact(R.drawable.male, "Tran Tinh", "thanh_67@gmail.com"));
        contacts.add(new SimpleContact(R.drawable.female, "Phan Hoa", "hoa@gmail.com"));
        contacts.add(new SimpleContact(R.drawable.female, "Pham Trang", "trang@gmail.com"));
        contacts.add(new SimpleContact(R.drawable.male, "Dinh Tuan", "dtuan@gmail.com"));
        contacts.add(new SimpleContact(R.drawable.female, "Kim Chi", "kimchi@gmail.com"));
        contacts.add(new SimpleContact(R.drawable.male, "Quoc Cuong", "cuong@gmail.com"));
        contacts.add(new SimpleContact(R.drawable.female, "Hai Yen", "hai_yen@gmail.com"));
    }

    @Override
    public void onTokenAdded(SimpleContact token) {
        Log.d("Main", "A Token added");
    }

    @Override
    public void onTokenRemoved(SimpleContact token) {
        Log.d("Main", "A Token removed");
    }
}
