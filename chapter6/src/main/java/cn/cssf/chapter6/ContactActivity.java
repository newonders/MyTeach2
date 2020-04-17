package cn.cssf.chapter6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        Button button = findViewById(R.id.ctButton);

        adapter = new ContactAdapter(ContactActivity.this,new ArrayList<Contact>());
        LinearLayoutManager layoutManager = new LinearLayoutManager(ContactActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(
                ContactActivity.this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkSelfPermission(Manifest.permission.READ_CONTACTS)
                        == PackageManager.PERMISSION_GRANTED){
                    List<Contact> contactList = readContacts();
                    adapter.setContactList(contactList);
                    adapter.notifyDataSetChanged();
                }else {
                    requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 1);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    List<Contact> contactList = readContacts();
                    adapter.setContactList(contactList);
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(this, "Denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
             default:
        }
    }

    private List<Contact> readContacts() {
        List<Contact> contactList = new ArrayList<>();
        Cursor cursor = null;
        try{
            cursor = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,null,null,null);
            if (cursor != null){
                while (cursor.moveToNext()){
                    String name = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String phone = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER));
                    Contact contact = new Contact();
                    contact.setName(name); contact.setPhone(phone);
                    contactList.add(contact);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return contactList;
    }
}
