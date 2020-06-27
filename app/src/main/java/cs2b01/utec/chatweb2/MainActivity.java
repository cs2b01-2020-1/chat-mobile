package cs2b01.utec.chatweb2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


    public void onLoginClicked(View view){
        //TODO Implement Login
        //1.  Obtener los datos del Layout
        EditText txtUsername = (EditText)findViewById(R.id.txtUsername);
        EditText txtPassword = (EditText)findViewById(R.id.txtPassword);
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();

        //2.  Construir JSON Message
        // {"username": "luise", "password": "1234"}
        Map<String, String> message = new HashMap<String, String>();
        message.put("username", username); // {"username": "luise"}
        message.put("password", password); // {"username": "luise", "password": "1234"}

        JSONObject jsonMessage = new JSONObject(message);


        //3.  Construir Request Object  --> volley
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                "http://10.0.2.2:8080/authenticate",
                jsonMessage,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //TODO when OK response
                        showMessage("Welcome!");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //TODO when error response
                        showMessage("Sorry :(");
                    }
                }

        );

        //4.  Enviar Request Message al server
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);



    }
}
