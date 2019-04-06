package com.cursoandroid.raone.androidretrofit2crud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.cursoandroid.raone.androidretrofit2crud.model.User;
import com.cursoandroid.raone.androidretrofit2crud.remote.APIUtils;
import com.cursoandroid.raone.androidretrofit2crud.remote.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnAddUser;
    Button btnGetUsersList;
    ListView ListView;

    UserService userService;
    List<User> list = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddUser = (Button) findViewById(R.id.btnAddUser);
        btnGetUsersList = (Button) findViewById(R.id.btnGetUsersList);
        ListView = (ListView) findViewById(R.id.ListView);
        userService = APIUtils.getUserService();

        btnGetUsersList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get users list
                getUsersList();
            }
        });

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                intent.putExtra("user_name", "");
                startActivity(intent);
            }
        });

    }

    public void getUsersList(){
        Call<List<User>> call = userService.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    ListView.setAdapter(new UserAdapter(MainActivity.this, R.layout.list_user, list));
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
