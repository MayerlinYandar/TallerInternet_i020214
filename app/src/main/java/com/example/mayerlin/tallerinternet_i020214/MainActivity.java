package com.example.mayerlin.tallerinternet_i020214;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mayerlin.tallerinternet_i020214.Model.User;
import com.example.mayerlin.tallerinternet_i020214.Parser.Json;
import com.example.mayerlin.tallerinternet_i020214.URL.HttpManager;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView textView;
    List<User> userList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
    }
    public void loadData(){
        if (isOnLine()){
            // Hacer llamado a la tarea
            //MyTask task = new MyTask();
            //task.execute("https://jsonplaceholder.typicode.com/posts");

            MyTask task = new MyTask();
            task.execute("http://pastoral.iucesmag.edu.co/practica/listar.php");
        }else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }
    public void processData(){
       //PASA LA LISTA AL VIEW(INTERFA<)
        for (User str : userList){
            textView.append(str.getNombre() + "\n");
            textView.append(str.getCodigo()+"\n");
            textView.append(str.getEdad()+"\n");
            textView.append(str.getCorreo()+"\n");
            textView.append(str.getPass()+"\n");
        }
    }
    public boolean isOnLine(){
        // Hacer llamado al servicio de conectividad utilizando el ConnectivityManager
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Obtener el estado de la conexion a internet en el dispositivo
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // Validar el estado obtenido de la conexion
        if (networkInfo != null){
            return true;
        }else {
            return false;
        }
    }
    public class MyTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {


            String content = null;
            try {
                content = HttpManager.getDataJson(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                userList = Json.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            processData();

            progressBar.setVisibility(View.GONE);
        }
    }

    // *************************************************************************************

    // Tarea para traer los datos de paises
    public class TaskCountry extends AsyncTask<String, String, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String content = null;
            try {
                content = HttpManager.getDataJson(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String string) {
            super.onPostExecute(string);

            try {
                userList = Json.getData(string);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            processData();

            progressBar.setVisibility(View.GONE);
        }
    }
}
