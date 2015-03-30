package com.example.android.cudgel.ui.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.android.cudgel.R;
import com.example.android.cudgel.ui.base.QuestionDetails;
import net.qiujuer.genius.util.Log;
import net.qiujuer.genius.widget.GeniusButton;
import net.qiujuer.genius.widget.GeniusEditText;
import java.util.ArrayList;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class FeedbackActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private GeniusButton btnSend;
    ProgressDialog dialog;
    public static int i;
    private EditText etPassword;
    private EditText etTestid;
    private ListView listview;
    private TextView txtCounter;
    private QuestionDetails ques_d;
    public static ArrayList<QuestionDetails> Ques_det;
    int tempParseSize=0;
    GeniusEditText etName,etEmail,etFeedback;

    final String username = "softeng.krishna@gmail.com";
    final String password = "rinkuamit";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#3D3427"));
        toolbar.setNavigationIcon(R.drawable.icon_back);

              if (toolbar != null) {
            toolbar.setTitle("Feedback");
            setSupportActionBar(toolbar);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initView();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnline()) {
                    processSendMail();
                }
            }
        });
    }


 void processSendMail(){

      String email = "softeng.krishna@gmail.com";
      String subject = "hello";
      String message = "working";
      sendMail(email, subject, message);

 }

    private void sendMail(String email, String subject, String messageBody) {
        Session session = createSessionObject();
        try {
            Message message = createMessage(email, subject, messageBody, session);
            new SendMailTask().execute(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    private Message createMessage(String email, String subject, String messageBody, Session session) throws MessagingException, UnsupportedEncodingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("softeng.krishna@gmail.com", "rinkuamit"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email, email));
        message.setSubject(subject);
        message.setText(messageBody);
        return message;
    }


    private Session createSessionObject() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");


        return Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("softeng.krishna@gmail.com", "rinkuamit");
            }
        });
    }



    private class SendMailTask extends AsyncTask<Message, Void, Void> {
        private ProgressDialog progressDialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(FeedbackActivity.this, "Please wait", "Sending mail", true, false);
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(FeedbackActivity.this,"Feedback Sent Sucessfully :)",Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }


        @Override
        protected Void doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
























    public boolean  isOnline() {
    /*    ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;*/
        return true;
    }

    void initView(){
    btnSend = (GeniusButton)findViewById(R.id.btnSend);
    etName = (GeniusEditText)findViewById(R.id.etName);
    etEmail = (GeniusEditText)findViewById(R.id.etEmail);
    etFeedback = (GeniusEditText)findViewById(R.id.etFeedback);
}

    @Override
    protected void onResume() {
        super.onResume();

    }

    //end of min class
}