package com.example.hackeru.xmlpaserexample;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button search = (Button)findViewById(R.id.btn_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText word = (EditText)findViewById(R.id.word_to_check);
                String wordStr = word.getText().toString();

                new AccessWebServiceTask().execute(wordStr);
            }
        });
    }

    private class AccessWebServiceTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            return wordDefinition(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            TextView textView = (TextView) findViewById(R.id.word_definition);
            textView.setText(s);
            textView.setMovementMethod(new ScrollingMovementMethod());
        }
    }

    private InputStream openHttpGetConnection(String urlStr) {//open a get Connection and return the inputstream
        InputStream inputStream = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            /*HttpClient httpClient = new DefaultHttpClient();
            HttpResponse httpResponse = httpClient.execute(new HttpGet(url)); //can't be executed on main thread
            inputStream = httpResponse.getEntity().getContent();*/
        }catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    private String wordDefinition(String word) {
        InputStream is = null;
        String definition = "";
        Document doc = null;

        try {
            is = openHttpGetConnection("http://services.aonaware.com/DictService/DictService.asmx/Define?word=" + word);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(is);
        }catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        //doc.getDocumentElement();

        //Retrieve all the <Definition> elements
        NodeList definitionElements = doc.getElementsByTagName("Definition");

        //Iterate though each <Definition> elements
        for(int i=0;i<definitionElements.getLength();i++) {

            Node itemNode = definitionElements.item(i);

            if(itemNode.getNodeType() == Node.ELEMENT_NODE) {

                Element definitionElement = (Element)itemNode;

                NodeList wordDefinitionsElements = definitionElement.getElementsByTagName("WordDefinition");

                NodeList textNodes = (wordDefinitionsElements.item(0)).getChildNodes();

                definition+=((Node)textNodes.item(0)).getNodeValue() + "\n";

            }
        }

        return definition;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
