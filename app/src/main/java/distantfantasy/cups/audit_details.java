package distantfantasy.cups;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.itextpdf.text.DocumentException;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class audit_details extends Fragment {
    private static final String TAG = audit_details.class.getSimpleName();
    private pdfmanagement PdfManagement;
    Activity activity;
    double ccount = 0;
    String textccount;
    String texttotal;
    int ctotal = 9;
    long score = 0;
    TextView grade;
    TextView count;
    TextView total;
    EditText storenumber;
    EditText auditor;
    Button submit;
    String storenumbersubmit;
    String auditorname;
    String mainauditornname;
    String mainstorenumber;
    EditText anotes;
    String auditornotes;
    public int failcount;
    public int failcountc;
    public int failcountu;
    public int failcountp;
    public int failcounts;
    public int abovecount;
    public int abovecountc;
    public int abovecountu;
    public int abovecountp;
    public int abovecounts;
    Context context;




    double dump;
    int dumpt;
    scoringstandard scoringstd = new scoringstandard();
    scoringabove scoringabv = new scoringabove();

    public audit_details() {
        // Required empty public constructor
    }

    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_audit_details, container, false);


        count = (TextView) v.findViewById(R.id.txttotalcount);
        total = (TextView) v.findViewById(R.id.txttotalpossible);
        grade = (TextView) v.findViewById(R.id.txtgradeu);
        submit = (Button) v.findViewById(R.id.submit);
        auditor = (EditText) v.findViewById(R.id.auditorname);
        auditorname = auditor.getText().toString();
        anotes = (EditText) v.findViewById(R.id.auditornotes);
        auditornotes = anotes.getText().toString();
        storenumber = (EditText) v.findViewById(R.id.storenumber);
        mainauditornname = ((MainActivity) getActivity()).auditorname();
        auditor.setText(mainauditornname);
        auditor.setFocusable(false);
        mainstorenumber = ((MainActivity) getActivity()).storenumber();
        if (mainstorenumber != null) {
            if (!mainstorenumber.equals("000000")) {
                storenumber.setText(mainstorenumber);
                storenumber.setFocusable(false);
                storenumbersubmit = mainstorenumber;
            }
        } else {
            ((MainActivity) getActivity()).logoutUser();
        }
        storenumbersubmit = storenumber.getText().toString();
        abovecount = 0;
        failcount = 0;
        count.setText(Long.toString(Math.round((Double.parseDouble(((MainActivity) getActivity()).countsum())))));
        total.setText(((MainActivity) getActivity()).totalsum());

        score = Math.round((Double.parseDouble(((MainActivity) getActivity()).countsum()) / Integer.parseInt(((MainActivity) getActivity()).totalsum()) * 100));

        if (((MainActivity) getActivity()).getcabove()) {
            abovecountc = 1;
        } else {
            abovecountc = 0;
        }
        if (((MainActivity) getActivity()).getuabove()) {
            abovecountu = 1;
        } else {
            abovecountu = 0;
        }
        if (((MainActivity) getActivity()).getpabove()) {
            abovecountp = 1;
        } else {
            abovecountp = 0;
        }
        if (((MainActivity) getActivity()).getsabove()) {
            abovecounts = 1;
        } else {
            abovecounts = 0;
        }
        abovecount = abovecountc+abovecountu+abovecountp+abovecounts;

        if (((MainActivity) getActivity()).getcfail()) {
            failcountc = 1;
        } else {
            failcountc = 0;
        }
        if (((MainActivity) getActivity()).getufail()) {
            failcountu = 1;
        } else {
            failcountu = 0;
        }
        if (((MainActivity) getActivity()).getpfail()) {
            failcountp = 1;
        } else {
            failcountp = 0;
        }
        if (((MainActivity) getActivity()).getsfail()) {
            failcounts = 1;
        } else {
            failcounts = 0;
        }
        failcount = failcountc+failcountu+failcountp+failcounts;
        Log.d(TAG, "Fail Count: " + failcount);
        Log.i(TAG, "Above Count: " + abovecount);
        if (failcount > 1) {
            grade.setText("F");
        } else if (abovecount < 2) {
            grade.setText(scoringstd.scoringstandard(score));
        } else {
            grade.setText(scoringabv.scoringabove(score));
        }


        auditor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                auditorname = auditor.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        anotes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                auditornotes = anotes.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        storenumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                storenumbersubmit = storenumber.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        v.findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ((MainActivity) getActivity()).createpdf();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                try {
                    ((MainActivity) getActivity()).uploadFile();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                ((MainActivity) getActivity()).CUPSEmail();

            }
        });

        return v;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden && v != null){
            count = (TextView) v.findViewById(R.id.txttotalcount);
            total = (TextView) v.findViewById(R.id.txttotalpossible);
            grade = (TextView) v.findViewById(R.id.txtgradeu);

            abovecount = 0;
            failcount = 0;

            count.setText(Long.toString(Math.round((Double.parseDouble(((MainActivity)getActivity()).countsum())))));
            total.setText(((MainActivity)getActivity()).totalsum());

            score = Math.round((Double.parseDouble(((MainActivity) getActivity()).countsum()) / Integer.parseInt(((MainActivity) getActivity()).totalsum()) * 100));

            if (((MainActivity) getActivity()).getcabove()) {
                abovecountc = 1;
            } else {
                abovecountc = 0;
            }
            if (((MainActivity) getActivity()).getuabove()) {
                abovecountu = 1;
            } else {
                abovecountu = 0;
            }
            if (((MainActivity) getActivity()).getpabove()) {
                abovecountp = 1;
            } else {
                abovecountp = 0;
            }
            if (((MainActivity) getActivity()).getsabove()) {
                abovecounts = 1;
            } else {
                abovecounts = 0;
            }
            abovecount = abovecountc+abovecountu+abovecountp+abovecounts;

            if (((MainActivity) getActivity()).getcfail()) {
                failcountc = 1;
            } else {
                failcountc = 0;
            }
            if (((MainActivity) getActivity()).getufail()) {
                failcountu = 1;
            } else {
                failcountu = 0;
            }
            if (((MainActivity) getActivity()).getpfail()) {
                failcountp = 1;
            } else {
                failcountp = 0;
            }
            if (((MainActivity) getActivity()).getsfail()) {
                failcounts = 1;
            } else {
                failcounts = 0;
            }
            failcount = failcountc+failcountu+failcountp+failcounts;
            Log.d(TAG, "Fail Count: " + failcount);
            Log.i(TAG, "Above Count: " + abovecount);
            if (failcount > 1) {
                grade.setText("F");
            } else if (abovecount < 2) {
                grade.setText(scoringstd.scoringstandard(score));
            } else {
                grade.setText(scoringabv.scoringabove(score));
            }

            Log.d(TAG, "Fail Count: " + failcount);
            Log.i(TAG, "Above Count: " + abovecount);
            if (failcount > 1) {
                grade.setText("F");
            } else if (abovecount < 2) {
                grade.setText(scoringstd.scoringstandard(score));
            } else {
                grade.setText(scoringabv.scoringabove(score));
            }



        }
    }

    public String getstorenumber(){
        return storenumbersubmit;
    }
    public String getauditor(){
        return auditorname;
    }
    public String getauditornotes(){
        return auditornotes;
    }
    public String gettgrade(){return grade.getText().toString();}


}
