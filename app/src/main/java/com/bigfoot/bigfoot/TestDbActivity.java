package com.bigfoot.bigfoot;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestDbActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_db);

        String urlReturnItemFrmBC = "http://albert.caslab.queensu.ca/bigfootService.php";
        String bcPHPvarName = "?barcode=";
        String barcodeFrmScan ="123456789012";


                listView = (ListView) findViewById(R.id.listView);
                downloadJSON(urlReturnItemFrmBC + bcPHPvarName + barcodeFrmScan);
            }

    private void downloadJSON(final String urlWebService) {

        class DownloadJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        DownloadJSON getJSON = new DownloadJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] result = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            result[i] = obj.getString("itemName") + " " + obj.getString("binType") + " " + obj.getString("recyclingType");
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, result);
        listView.setAdapter(arrayAdapter);
    }
//    public void executeSSHcommand() {
//        String user = "albert";
//        String password = "Fugei0cahcah";
//        String host = "130.15.15.4";
//        int port = 22;
//        TextView textView = findViewById(R.id.displayText);
//        try {
//
//            JSch jsch = new JSch();
//            Session session = jsch.getSession(user, host, port);
//            session.setPassword(password);
//            session.setConfig("StrictHostKeyChecking", "no");
//            session.setTimeout(1000);
//            session.connect();
//            ChannelExec channel = (ChannelExec) session.openChannel("exec");
//            channel.setCommand("echo 'hello' > Yesssssssss.txt");
//            channel.connect();
//            channel.disconnect();
//            // show success in UI with a snackbar alternatively use a toast
////            Snackbar.make(getActivity().findViewById(android.R.id.content),
////                    "Success!", Snackbar.LENGTH_LONG)
////                    .setAction("Act.ion", null).show();
//
//           // textView.setText("success!");
//
//        } catch (JSchException e) {
//            textView.setText(e.toString());
//            // textView.setText("Failure");
//
//        }
//    }


//    public void executeSQLcmds() {
//        System.out.println("An example for updating a Row from Mysql Database!");
//        Connection con = null;
//        String driver = "com.mysql.jdbc.Driver";
//        String rhost = "130.15.15.4";
//
//        int lport = 1433;
//        String url = "jdbc:mysql://" + rhost + ":" + lport + "/";
//        String db = "bigfoot";
//        String dbUser = "biguser";
//        String dbPasswd = "bigfoot";
//        textView.setText("set variables");
//
//        try {
//            Class.forName(driver);
//            con = DriverManager.getConnection(url + db, dbUser, dbPasswd);
//            textView.setText("connected");
//            try {
//                Statement stmt = con.createStatement();
//                String sql = "Select * From BFItem";
//
//                ResultSet rs = stmt.executeQuery(sql);
//                String barcodes = "";
//                while (rs.next()) {
//                    String barcodeNumber = rs.getString("barcodeNumber");
//                    barcodes += barcodeNumber;
//                    barcodes += " ";
//                }
//                textView.setText(barcodes);
//            } catch (SQLException s) {
//                System.out.println("SQL statement is not executed!");
//            }
//        } catch (Exception e) {
//           textView.setText(e.toString());
//            Log.e("woo", e.toString());
//        }
//    }


}

