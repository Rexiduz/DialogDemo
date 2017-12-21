package com.dogkillmen.rexiduz.dialogdemo;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String[] CLUBS =
            {"Arsenal", "Chelsea", "Liverpool", "Man City", "Man Utd"};

    String mSelected;
    ArrayList<Integer> mMultiSelected;

    Button mDialogSimple;
    Button mDialogList;
    Button mDialogSingleChoice;
    Button mDialogMultipleChoice;
    Button mDialogCustom;
    Button mDialogCustom2;
    private Button buttonClick;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        mDialogSimple = (Button) findViewById(R.id.button_dialog_simple);
        mDialogList = (Button) findViewById(R.id.button_dialog_list);
        mDialogSingleChoice = (Button) findViewById(R.id.button_dialog_single_choice);
        mDialogMultipleChoice = (Button) findViewById(R.id.button_dialog_multi_choice);
        mDialogCustom = (Button) findViewById(R.id.button_dialog_custom);
        mDialogCustom2 = (Button) findViewById(R.id.button_dialog_custom2);
        buttonClick = (Button) findViewById(R.id.buttonClick);
        Button buttonClick2 = (Button) findViewById(R.id.buttonClick2);


        mDialogSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("รับขนมจีบซาลาเปาเพิ่มมั้ยครับ?");
                builder.setPositiveButton("รับ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(),
                                "ขอบคุณครับ", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("ไม่รับ", null);
                builder.create();

                // สุดท้ายอย่าลืม show() ด้วย
                builder.show();
            }
        });

        mDialogList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select Favorite Team");
                builder.setItems(CLUBS, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selected = CLUBS[which];
                        Toast.makeText(getApplicationContext(), "คุณชอบ " +
                                selected, Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("ไม่ชอบซักทีม", null);
                builder.create();

                // สุดท้ายอย่าลืม show() ด้วย
                builder.show();
            }
        });

        mDialogSingleChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select Favorite Team");
                builder.setSingleChoiceItems(CLUBS, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mSelected = CLUBS[which];
                    }
                });
                builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // เซฟค่าลง database หรือ SharedPreferences.
                        Toast.makeText(getApplicationContext(), "คุณชอบ " +
                                mSelected, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("ไม่ชอบซักทีม", null);
                builder.create();

                // สุดท้ายอย่าลืม show() ด้วย
                builder.show();
            }
        });

        mDialogMultipleChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMultiSelected = new ArrayList<Integer>();

                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select Favorite Team");
                builder.setMultiChoiceItems(CLUBS, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            mMultiSelected.add(which);
                        } else if (mMultiSelected.contains(which)) {
                            mMultiSelected.remove(Integer.valueOf(which));
                        }
                    }
                });

                builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // เซฟค่าลง database หรือ SharedPreferences.
                        StringBuffer buffer = new StringBuffer();
                        for (Integer team : mMultiSelected) {
                            buffer.append(" ");
                            buffer.append(CLUBS[team]);
                        }
                        Toast.makeText(getApplicationContext(), "คุณชอบ" +
                                buffer.toString(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("ไม่ชอบซักทีม", null);
                builder.create();

                // สุดท้ายอย่าลืม show() ด้วย
                builder.show();
            }
        });

        mDialogCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = getLayoutInflater();

                View view = inflater.inflate(R.layout.dialog_custom, null);
                builder.setView(view);

                final EditText username = (EditText) view.findViewById(R.id.username);
                final EditText password = (EditText) view.findViewById(R.id.password);

                builder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Check username password
                        if (username.getText().toString().equals("admin") &&
                                password.getText().toString().equals("admin")) {
                            Toast.makeText(getApplicationContext(), "Login success!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Login Failed!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.create();

                builder.show();
            }
        });

        mDialogCustom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setTitle("Devahoy");
                dialog.setContentView(R.layout.dialog_custom2);

                final EditText username = (EditText) dialog.findViewById(R.id.username);
                final EditText password = (EditText) dialog.findViewById(R.id.password);
                Button buttonCancel = (Button) dialog.findViewById(R.id.button_cancel);
                Button buttonLogin = (Button) dialog.findViewById(R.id.button_login);

                buttonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                buttonLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Check username password
                        if (username.getText().equals("demo@example.com") &&
                                password.getText().equals("demo")) {
                            Toast.makeText(getApplicationContext(), "Login success!",
                                    Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(getApplicationContext(), "Login Failed!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.show();
            }
        });

        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create custom dialog object
                final Dialog dialog = new Dialog(MainActivity.this);
                // Include dialog.xml file
                dialog.setContentView(R.layout.dialog_custom3);
                // Set dialog title
                dialog.setTitle("Custom Dialog");

                // set values for custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.textDialog);
                text.setText("Custom dialog Android example.");
                ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
                image.setImageResource(R.drawable.ic_launcher_background);

                dialog.show();

                Button declineButton = (Button) dialog.findViewById(R.id.declineButton);
                // if decline button is clicked, close the custom dialog
                declineButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close dialog
                        dialog.dismiss();
                    }
                });

            }
        });

        buttonClick2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

                String names[] ={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,names);

                LayoutInflater inflater = getLayoutInflater();
                View convertView = (View)inflater.inflate(R.layout.list_layout, null);
                ListView lv = (ListView) convertView.findViewById(R.id.list_view1);
                lv.setAdapter(adapter);
                alertDialog.setView(convertView);
                alertDialog.setTitle("List");
                final AlertDialog ad = alertDialog.show();

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        //Toast.makeText(mContext, position, Toast.LENGTH_LONG).show();
                        ad.dismiss();
                    }
                });
            }
        });


    }
}