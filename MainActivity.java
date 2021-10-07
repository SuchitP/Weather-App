package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    URL url;
    URLConnection urlConnection;
    InputStream inputStream;
    EditText editText;
    //String zip;
    String date;
    String weather;
    String currentString;
    JSONObject jsonObject;
    Boolean isValid;
    TextView date1,date2,date3,date4,date5;
    TextView weather1,weather2,weather3,weather4,weather5;
    Button enter;
    String zip = "";
    ImageView image1, image2, image3, image4, image5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.activity_weather);

        AsyncThread thread = new AsyncThread();
        thread.execute();

        date1 = findViewById(R.id.id_date1);
        weather1 = findViewById(R.id.id_weather1);
        image1 = findViewById(R.id.id_image1);
        date2 = findViewById(R.id.id_date2);
        weather2 = findViewById(R.id.id_weather2);
        image2 = findViewById(R.id.id_image2);
        date3 = findViewById(R.id.id_date3);
        weather3 = findViewById(R.id.id_weather3);
        image3 = findViewById(R.id.id_image3);
        date3 = findViewById(R.id.id_date3);
        date4 = findViewById(R.id.id_date4);
        date5 = findViewById(R.id.id_date5);
        weather3 = findViewById(R.id.id_weather3);
        weather4 = findViewById(R.id.id_weather4);
        weather5 = findViewById(R.id.id_weather5);
        image4 = findViewById(R.id.id_image4);
        image5 = findViewById(R.id.id_image5);

        editText = findViewById(R.id.id_editText);
        enter = findViewById(R.id.id_button);


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                zip=s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                   new AsyncThread().execute(zip);
                } catch (Exception e) {

                }
            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }


    public class AsyncThread extends AsyncTask<String, Void, Void> {
        String string = "";
        @Override
        protected Void doInBackground(String... strings) {
            try {
                //while(string == "\"cod\":\"400\",\"message\":\"invalid zip code" || string.equals("")) {
                    String zip = strings[0];
                    url = new URL("http://api.openweathermap.org/data/2.5/forecast?zip="+zip+"&APPID=150bd0ccbce5d7a57a0fdc2aefaef89e&units=imperial");
                    URLConnection urlConnection = url.openConnection();
                    inputStream = urlConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    currentString = reader.readLine();
                    JSONObject jsonObject = new JSONObject(currentString);
                    Log.d("TAG",jsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").get("temp").toString());
               // }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try{
                JSONObject jsonObject = new JSONObject(currentString);

                String temp;

                temp = "" + jsonObject.getJSONArray("list").getJSONObject(4).getJSONObject("main").get("temp").toString();
                weather1.setText(temp);
                temp = "" + jsonObject.getJSONArray("list").getJSONObject(4).getString("dt_txt").substring(5, 10);
                date1.setText(temp);

                temp = "" + jsonObject.getJSONArray("list").getJSONObject(12).getJSONObject("main").get("temp").toString();
                weather2.setText(temp);
                temp = "" + jsonObject.getJSONArray("list").getJSONObject(12).getString("dt_txt").substring(5, 10);
                date2.setText(temp);

                temp = "" + jsonObject.getJSONArray("list").getJSONObject(20).getJSONObject("main").get("temp").toString();
                weather3.setText(temp);
                temp = "" + jsonObject.getJSONArray("list").getJSONObject(20).getString("dt_txt").substring(5, 10);
                date3.setText(temp);

                temp = "" + jsonObject.getJSONArray("list").getJSONObject(28).getJSONObject("main").get("temp").toString();
                weather4.setText(temp);
                temp = "" + jsonObject.getJSONArray("list").getJSONObject(28).getString("dt_txt").substring(5, 10);
                date4.setText(temp);

                temp = "" + jsonObject.getJSONArray("list").getJSONObject(36).getJSONObject("main").get("temp").toString();
                weather5.setText(temp);
                temp = "" + jsonObject.getJSONArray("list").getJSONObject(36).getString("dt_txt").substring(5, 10);
                date5.setText(temp);



                for(int i = 4; i <= 40; i+=8){
                   String id = jsonObject.getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("icon");

                   if(i == 4) {
                       if (id.equals("01d") || id.equals("01n")) {
                           image1.setImageResource(R.drawable.s01d);
                       } else if (id.equals("02d") || id.equals("02n")) {
                           image1.setImageResource(R.drawable.s02d);
                       } else if (id.equals("03d") || id.equals("03n")) {
                           image1.setImageResource(R.drawable.s03d);
                       } else if (id.equals("04d") || id.equals("04n")) {
                           image1.setImageResource(R.drawable.s04d);
                       } else if (id.equals("09d") || id.equals("09n")) {
                           image1.setImageResource(R.drawable.s09d);
                       }else if (id.equals("10d") || id.equals("10n")) {
                           image1.setImageResource(R.drawable.s10d);
                       }
                   }
                    if(i == 12) {
                        if (id.equals("01d") || id.equals("01n")) {
                            image2.setImageResource(R.drawable.s01d);
                        } else if (id.equals("02d") || id.equals("02n")) {
                            image2.setImageResource(R.drawable.s02d);
                        } else if (id.equals("03d") || id.equals("03n")) {
                            image2.setImageResource(R.drawable.s03d);
                        } else if (id.equals("04d") || id.equals("04n")) {
                            image2.setImageResource(R.drawable.s04d);
                        } else if (id.equals("09d") || id.equals("09n")) {
                            image2.setImageResource(R.drawable.s09d);
                        }else if (id.equals("10d") || id.equals("10n")) {
                            image2.setImageResource(R.drawable.s10d);
                        }
                    }
                    if(i == 20) {
                        if (id.equals("01d") || id.equals("01n")) {
                            image3.setImageResource(R.drawable.s01d);
                        } else if (id.equals("02d") || id.equals("02n")) {
                            image3.setImageResource(R.drawable.s02d);
                        } else if (id.equals("03d") || id.equals("03n")) {
                            image3.setImageResource(R.drawable.s03d);
                        } else if (id.equals("04d") || id.equals("04n")) {
                            image3.setImageResource(R.drawable.s04d);
                        } else if (id.equals("09d") || id.equals("09n")) {
                            image3.setImageResource(R.drawable.s09d);
                        }else if (id.equals("10d") || id.equals("10n")) {
                            image3.setImageResource(R.drawable.s10d);
                        }
                    }
                    if(i == 28) {
                        if (id.equals("01d") || id.equals("01n")) {
                            image4.setImageResource(R.drawable.s01d);
                        } else if (id.equals("02d") || id.equals("02n")) {
                            image4.setImageResource(R.drawable.s02d);
                        } else if (id.equals("03d") || id.equals("03n")) {
                            image4.setImageResource(R.drawable.s03d);
                        } else if (id.equals("04d") || id.equals("04n")) {
                            image4.setImageResource(R.drawable.s04d);
                        } else if (id.equals("09d") || id.equals("09n")) {
                            image4.setImageResource(R.drawable.s09d);
                        }else if (id.equals("10d") || id.equals("10n")) {
                            image4.setImageResource(R.drawable.s10d);
                        }
                    }
                    if(i == 36) {
                        if (id.equals("01d") || id.equals("01n")) {
                            image5.setImageResource(R.drawable.s01d);
                        } else if (id.equals("02d") || id.equals("02n")) {
                            image5.setImageResource(R.drawable.s02d);
                        } else if (id.equals("03d") || id.equals("03n")) {
                            image5.setImageResource(R.drawable.s03d);
                        } else if (id.equals("04d") || id.equals("04n")) {
                            image5.setImageResource(R.drawable.s04d);
                        } else if (id.equals("09d") || id.equals("09n")) {
                            image5.setImageResource(R.drawable.s09d);
                        }
                        else if (id.equals("10d") || id.equals("10n")) {
                            image5.setImageResource(R.drawable.s10d);
                        }
                    }

                }



            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
//https://www.tutorialspoint.com/java/java_url_processing.htm
