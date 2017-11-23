package distantfantasy.cups;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class products extends Fragment {
    double ccount = 0;
    int ctotal = 4;
    long score = 0;
    boolean fail=false;
    boolean above=false;
    CheckBox pbox1;
    CheckBox pbox2;
    CheckBox pbox3;
    CheckBox pbox4;
    CheckBox cabove;
    CheckBox cfail;
    boolean box1 = false;
    boolean box2= false;
    boolean box3= false;
    boolean box4= false;
    EditText tnote;
    String tnotes;
    EditText tfail;
    String pnotesfail;
    String pnotesabove;
    EditText tabove;
    TextView text;
    TextView ccounttext;
    TextView totaltext;
    TableRow crowabove;
    TableRow crowfail;
    scoringabove scoringa = new scoringabove();
    scoringstandard scorings = new scoringstandard();


    public products() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_products, container, false);


        //init Checkboxes
        pbox1 = (CheckBox) v.findViewById(R.id.pBox1);
        pbox2 = (CheckBox) v.findViewById(R.id.pBox2);
        pbox3 = (CheckBox) v.findViewById(R.id.pBox3);
        pbox4 = (CheckBox) v.findViewById(R.id.pBox4);
        cabove = (CheckBox) v.findViewById(R.id.pabove);
        cfail = (CheckBox) v.findViewById(R.id.pfail);
        text = (TextView) v.findViewById(R.id.txtgradep);
        crowabove = (TableRow) v.findViewById(R.id.pabovetable);
        crowabove.setVisibility(View.INVISIBLE);
        crowfail = (TableRow) v.findViewById(R.id.pfailtable);
        crowfail.setVisibility(View.INVISIBLE);
        ccounttext = (TextView) v.findViewById(R.id.txtcountp);
        ccounttext.setText(Long.toString(Math.round(ccount)));

        totaltext = (TextView) v.findViewById(R.id.txttotalp);
        totaltext.setText(" "+(ctotal));
        tfail = (EditText) v.findViewById(R.id.pnotesfail);
        if (fail) {
            text.setText("F");
        } else if (above) {
            text.setText(scoringa.scoringabove(score));
        } else {
            text.setText(scorings.scoringstandard(score));
        }

        tfail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pnotesfail = tfail.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tabove = (EditText) v.findViewById(R.id.pnoteabove);

        tabove.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pnotesabove = tabove.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        tnote = (EditText) v.findViewById(distantfantasy.cups.R.id.pnote);

        tnote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tnotes = tnote.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        pbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ccount = ccount + 1;
                    box1=true;

                    ccounttext.setText(Long.toString(Math.round(ccount)));
                } else if (ccount > 0) {
                    ccount = ccount - 1;
                    box1=false;

                    ccounttext.setText(Long.toString(Math.round(ccount)));
                }
                score = Math.round((ccount / ctotal*100));
                if (fail) {
                    text.setText("F");
                } else if (above) {
                    text.setText(scoringa.scoringabove(score));
                } else {
                    text.setText(scorings.scoringstandard(score));
                }

            }



        });
        pbox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ccount = ccount + 1;
                    box2=true;

                    ccounttext.setText(Long.toString(Math.round(ccount)));
                } else if (ccount > 0) {
                    ccount = ccount - 1;
                    box2=false;

                    ccounttext.setText(Long.toString(Math.round(ccount)));
                }
                score = Math.round((ccount / ctotal*100));
                if (fail) {
                    text.setText("F");
                } else if (above) {
                    text.setText(scoringa.scoringabove(score));
                } else {
                    text.setText(scorings.scoringstandard(score));
                }

            }
        });
        pbox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ccount = ccount + 1;
                    box3=true;

                    ccounttext.setText(Long.toString(Math.round(ccount)));
                } else if (ccount > 0) {
                    ccount = ccount - 1;
                    box3=false;

                    ccounttext.setText(Long.toString(Math.round(ccount)));
                }
                score = Math.round((ccount / ctotal*100));
                if (fail) {
                    text.setText("F");
                } else if (above) {
                    text.setText(scoringa.scoringabove(score));
                } else {
                    text.setText(scorings.scoringstandard(score));
                }

            }
        });
        pbox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ccount = ccount + 1;
                    box4=true;

                    ccounttext.setText(Long.toString(Math.round(ccount)));
                } else if (ccount > 0) {
                    ccount = ccount - 1;
                    box4=false;

                    ccounttext.setText(Long.toString(Math.round(ccount)));
                }
                score = Math.round((ccount / ctotal*100));
                if (fail) {
                    text.setText("F");
                } else if (above) {
                    text.setText(scoringa.scoringabove(score));
                } else {
                    text.setText(scorings.scoringstandard(score));
                }

            }
        });
        cabove.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    above=true;
                    crowabove.setVisibility(View.VISIBLE);
                } else {
                    above=false;
                    crowabove.setVisibility(View.INVISIBLE);
                }
                score = Math.round((ccount / ctotal*100));
                if (fail) {
                    text.setText("F");
                } else if (above) {
                    text.setText(scoringa.scoringabove(score));
                } else {
                    text.setText(scorings.scoringstandard(score));
                }

            }
        });
        cfail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    fail=true;
                    crowfail.setVisibility(View.VISIBLE);
                } else {
                    fail=false;
                    crowfail.setVisibility(View.INVISIBLE);
                }
                score = Math.round((ccount / ctotal*100));
                if (fail) {
                    text.setText("F");
                } else if (above) {
                    text.setText(scoringa.scoringabove(score));
                } else {
                    text.setText(scorings.scoringstandard(score));
                }

            }
        });


        return v;    }
    public double getcount(){
        return ccount;
    }
    public int gettotal(){
        return ctotal;
    }
    public boolean getcbox1() {return box1;}
    public boolean getcbox2() {return box2;}
    public boolean getcbox3() {return box3;}
    public boolean getcbox4() {return box4;}
    public boolean getcfailbox() {return fail;}
    public boolean getcabovebox() {return above;}
    public String getgrade(){return text.getText().toString();}
    public String gettnotes(){
        return tnotes;
    }

    public String getcfail(){
        return pnotesfail;
    }
    public String getcabove(){
        return pnotesabove;
    }
    public boolean getabove(){
        return above;
    }
    public boolean getfail(){
        return fail;
    }
}
