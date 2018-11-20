package com.bigfoot.bigfoot;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import android.widget.Button;
import android.widget.TextView;
import java.sql.*;


public class TestDbActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_db);

        TextView textView = findViewById(R.id.displayText);

        Button connectBtn = findViewById(R.id.connectButton);
        connectBtn.setOnClickListener(new View.OnClickListener() {
            // @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View v) {
                new AsyncTask<Integer, Void, Void>() {
                    @Override
                    protected Void doInBackground(Integer... params) {
                        try {
                            executeSQLcmds();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }.execute(1);
            }
        });
    }

    public void executeSSHcommand() {
        String user = "albert";
        String password = "Fugei0cahcah";
        String host = "130.15.15.4";
        int port = 22;
        TextView textView = findViewById(R.id.displayText);
        try {

            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setTimeout(1000);
            session.connect();
            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand("echo 'hello' > Yesssssssss.txt");
            channel.connect();
            channel.disconnect();
            // show success in UI with a snackbar alternatively use a toast
//            Snackbar.make(getActivity().findViewById(android.R.id.content),
//                    "Success!", Snackbar.LENGTH_LONG)
//                    .setAction("Act.ion", null).show();

           // textView.setText("success!");

        } catch (JSchException e) {
            textView.setText(e.toString());
            // textView.setText("Failure");

        }
    }


    public void executeSQLcmds() {
        System.out.println("An example for updating a Row from Mysql Database!");
        Connection con = null;
        String driver = "com.mysql.jdbc.Driver";
        String rhost = "albert.caslab.queensu.ca";
        int lport = 3306;
        String url = "jdbc:mysql://" + rhost + ":" + lport + "/";
        String db = "bigfoot";
        String dbUser = "biguser";
        String dbPasswd = "bigfoot";
        TextView textView = findViewById(R.id.displayText);
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url + db, dbUser, dbPasswd);
            try {
                Statement stmt = con.createStatement();
                String sql = "Select * From BFItem";

                ResultSet rs = stmt.executeQuery(sql);
                String barcodes = "";
                while (rs.next()) {
                    String barcodeNumber = rs.getString("barcodeNumber");
                    barcodes += barcodeNumber;
                    barcodes += " ";
                }
                textView.setText(barcodes);
            } catch (SQLException s) {
                System.out.println("SQL statement is not executed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



