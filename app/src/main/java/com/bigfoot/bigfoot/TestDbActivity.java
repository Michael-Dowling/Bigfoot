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

public class TestDbActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_db);

        //TextView textView = findViewById(R.id.displayText);

        Button connectBtn = findViewById(R.id.connectButton);
        connectBtn.setOnClickListener(new View.OnClickListener() {
           // @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View v){
                new AsyncTask<Integer, Void, Void>(){
                    @Override
                    protected Void doInBackground(Integer... params) {
                        try {
                            executeSSHcommand();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }.execute(1);
            }
        });
    }

    public void executeSSHcommand(){
        String user = "albert";
        String password = "Fugei0cahcah";
        String host = "130.15.15.4";
        int port=22;
        try{

            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setTimeout(10000);
            session.connect();
            ChannelExec channel = (ChannelExec)session.openChannel("exec");
            channel.setCommand("echo 'hello' > YASSS.txt");
            channel.connect();
            channel.disconnect();
            // show success in UI with a snackbar alternatively use a toast
//            Snackbar.make(getActivity().findViewById(android.R.id.content),
//                    "Success!", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();

           // text.setText("success!");

        }
        catch(JSchException e){
            // show the error in the UI
//            Snackbar.make(findViewById(android.R.id.content),
//                    "Check WIFI or Server! Error : "+e.getMessage(),
//                    Snackbar.LENGTH_LONG)
//                    .setDuration(20000).setAction("Action", null).show();

        }
    }
}

//    public void executeSSLcmds() {
//
//
//
// try {
//            go();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        System.out.println("An example for updating a Row from Mysql Database!");
//        Connection con = null;
//        String driver = "com.mysql.jdbc.Driver";
//        String url = "jdbc:mysql://" + rhost + ":" + lport + "/";
//        String db = "Bigfootdb";
//        String dbUser = "albert";
//        String dbPasswd = "Fugei0cahcah";
//        try {
//            Class.forName(driver);
//            con = DriverManager.getConnection(url + db, dbUser, dbPasswd);
//            try {
//                Statement st = con.createStatement();
//                String sql = "UPDATE MyTableName " +
//                        "SET email = 'ripon.wasim@smile.com' WHERE email='peace@happy.com'";
//
//                int update = st.executeUpdate(sql);
//                if (update >= 1) {
//                    System.out.println("Row is updated.");
//                } else {
//                    System.out.println("Row is not updated.");
//                }
//            } catch (SQLException s) {
//                System.out.println("SQL statement is not executed!");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }



