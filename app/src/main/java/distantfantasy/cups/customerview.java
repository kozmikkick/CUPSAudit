package distantfantasy.cups;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class customerview extends Fragment{
    double ccount = 0;
    int ctotal = 9;
    long score = 0;
    boolean fail;
    boolean above;
    CheckBox cbox1;
    CheckBox cbox2;
    CheckBox cbox3;
    CheckBox cbox4;
    CheckBox cbox5;
    CheckBox cbox6;
    CheckBox cbox7;
    CheckBox cbox8;
    CheckBox cbox9;
    boolean box1;
    boolean box2;
    boolean box3;
    boolean box4;
    boolean box5;
    boolean box6;
    boolean box7;
    boolean box8;
    boolean box9;

    EditText tfail;
    String pnotesfail;
    String pnotesabove;
    EditText tabove;
    EditText tnote;
    String tnotes;
    CheckBox cabove;
    CheckBox cfail;
    TextView text;
    TextView ccounttext;
    TextView totaltext;
    TableRow crowabove;
    TableRow crowfail;
    scoringabove scoringa = new scoringabove();
    scoringstandard scorings = new scoringstandard();

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    public customerview() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
               View v = inflater.inflate(distantfantasy.cups.R.layout.fragment_customerview, container, false);

        //init Checkboxes
        cbox1 = (CheckBox) v.findViewById(distantfantasy.cups.R.id.cBox1);
        cbox2 = (CheckBox) v.findViewById(distantfantasy.cups.R.id.cBox2);
        cbox3 = (CheckBox) v.findViewById(distantfantasy.cups.R.id.cBox3);
        cbox4 = (CheckBox) v.findViewById(distantfantasy.cups.R.id.cBox4);
        cbox5 = (CheckBox) v.findViewById(distantfantasy.cups.R.id.cBox5);
        cbox6 = (CheckBox) v.findViewById(distantfantasy.cups.R.id.cBox6);
        cbox7 = (CheckBox) v.findViewById(distantfantasy.cups.R.id.cBox7);
        cbox8 = (CheckBox) v.findViewById(distantfantasy.cups.R.id.cBox8);
        cbox9 = (CheckBox) v.findViewById(distantfantasy.cups.R.id.cBox9);
        cabove = (CheckBox) v.findViewById(distantfantasy.cups.R.id.cabove);
        cfail = (CheckBox) v.findViewById(distantfantasy.cups.R.id.cfail);
        text = (TextView) v.findViewById(distantfantasy.cups.R.id.txtgradec);
        crowabove = (TableRow) v.findViewById(distantfantasy.cups.R.id.cabovetable);
        crowabove.setVisibility(View.INVISIBLE);
        crowfail = (TableRow) v.findViewById(distantfantasy.cups.R.id.cfailtable);
        crowfail.setVisibility(View.INVISIBLE);
        ccounttext = (TextView) v.findViewById(distantfantasy.cups.R.id.txtcountc);
        ccounttext.setText(Long.toString(Math.round(ccount)));
        totaltext = (TextView) v.findViewById(distantfantasy.cups.R.id.txttotalc);
        totaltext.setText(" "+(ctotal));
        tfail = (EditText) v.findViewById(distantfantasy.cups.R.id.cnotesfail);
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

        tabove = (EditText) v.findViewById(distantfantasy.cups.R.id.cnoteabove);

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

        tnote = (EditText) v.findViewById(distantfantasy.cups.R.id.cnote);

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







        cbox1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
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
        cbox2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
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
        cbox3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
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
        cbox4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
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
        cbox5.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ccount = ccount + 1;
                    box5=true;
                    ccounttext.setText(Long.toString(Math.round(ccount)));
                } else if (ccount > 0) {
                    ccount = ccount - 1;
                    box5=false;
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
        cbox6.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ccount = ccount + 1;
                    box6=true;
                    ccounttext.setText(Long.toString(Math.round(ccount)));
                } else if (ccount > 0) {
                    ccount = ccount - 1;
                    box6=false;
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
        cbox7.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ccount = ccount + 1;
                    box7=true;
                    ccounttext.setText(Long.toString(Math.round(ccount)));
                } else if (ccount > 0) {
                    ccount = ccount - 1;
                    box7=false;
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
        cbox8.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ccount = ccount + 1;
                    box8=true;
                    ccounttext.setText(Long.toString(Math.round(ccount)));
                } else if (ccount > 0) {
                    ccount = ccount - 1;
                    box8=false;
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
        cbox9.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ccount = ccount + 1;
                    box9=true;
                    ccounttext.setText(Long.toString(Math.round(ccount)));
                } else if (ccount > 0) {
                    ccount = ccount - 1;
                    box9=false;
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
        cabove.setOnCheckedChangeListener(new OnCheckedChangeListener() {
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
        cfail.setOnCheckedChangeListener(new OnCheckedChangeListener() {
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



        return v;
    }
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
    public boolean getcbox5() {return box5;}
    public boolean getcbox6() {return box6;}
    public boolean getcbox7() {return box7;}
    public boolean getcbox8() {return box8;}
    public boolean getcbox9() {return box9;}
    public boolean getcfailbox() {return fail;}
    public boolean getcabovebox() {return above;}

    public String getcfail(){
        return pnotesfail;
    }
    public String getcabove(){
        return pnotesabove;
    }
    public String gettnotes(){
        return tnotes;
    }
    public boolean getabove(){
        return above;
    }
    public boolean getfail(){
        return fail;
    }
    public String getgrade(){return text.getText().toString();}

}