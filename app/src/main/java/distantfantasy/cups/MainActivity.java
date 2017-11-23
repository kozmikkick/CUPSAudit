package distantfantasy.cups;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.content.Intent;
import android.app.ProgressDialog;


import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.DocumentException;


import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.HashMap;

import helper.SQLiteHandler;
import helper.SessionManager;
import email.email;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private String SERVER_URL = "http://distantfantasy.com/cupsaudit/upload.php";
    private SessionManager session;
    public File newoutput;
    public String newpdfname;
    public String date;
    public String name;
    public String uploadfilename;
    public String areaemail;
    public String storenumber;
    public String getstorenumber;
    public String storeemail;
    private customerview Customerview;
    private uniforms Uniforms;
    private products Products;
    private speed Speed;
    private audit_details Audit_Details;
    public OutputStream output = null;
    public PdfReader reader = null;
    public PdfStamper stamper = null;
    boolean cbox1=false;
    boolean cbox2=false;
    boolean cbox3=false;
    boolean cbox4=false;
    boolean cbox5=false;
    boolean cbox6=false;
    boolean cbox7=false;
    boolean cbox8=false;
    boolean cbox9=false;
    boolean cfail=false;
    boolean cabove=false;
    boolean ubox1=false;
    boolean ubox2=false;
    boolean ubox3=false;
    boolean ufail=false;
    boolean uabove=false;
    boolean pbox1=false;
    boolean pbox2=false;
    boolean pbox3=false;
    boolean pbox4=false;
    boolean pfail=false;
    boolean pabove=false;
    boolean sbox1=false;
    boolean sbox2=false;
    boolean sbox3=false;
    boolean sbox4=false;
    boolean sbox5=false;
    boolean sbox6=false;
    boolean sbox7=false;
    boolean sfail=false;
    boolean sabove=false;
    private ProgressDialog pDialog;
    public String firstname;
    public String dbname,dbareaemail,dbstorenumber,dbuid;
    private SQLiteHandler db;
    email emailing = new email();








    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        

	// SqLite database handler
	db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

	// Fetching user details from SQLite
	HashMap<String, String> user = db.getUserDetails();

	dbname = user.get("name");
	dbareaemail = user.get("areaemail");
        dbuid = user.get("uid");
        dbstorenumber = user.get("storenumber");

        CreateCUPSFolder();

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);



        setContentView(R.layout.activity_main);
        if (savedInstanceState == null){


        Customerview = new customerview();
        Uniforms = new uniforms();
        Products = new products();
        Speed = new speed();
        Audit_Details = new audit_details();
        }





                    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

           // DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
              //      this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            //drawer.setDrawerListener(toggle);
            //toggle.syncState();

           // NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            //navigationView.setNavigationItemSelectedListener(this);
            BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
            bottomNavigationView.setOnNavigationItemSelectedListener(this);
        FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
        manager.add(R.id.cups_display, Audit_Details);
        manager.hide(Audit_Details);
        manager.add(R.id.cups_display, Speed);
        manager.hide(Speed);
        manager.add(R.id.cups_display, Products);
        manager.hide(Products);
        manager.add(R.id.cups_display, Uniforms);
        manager.hide(Uniforms);
        manager.add(R.id.cups_display, Customerview);
                manager.commit();

    }

        @Override
     //   public void onBackPressed () {
       //     DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
         //   if (drawer.isDrawerOpen(GravityCompat.START)) {
          //      drawer.closeDrawer(GravityCompat.START);
           // } else {
            //    super.onBackPressed();
           // }
       // }

        //@Override
        public boolean onCreateOptionsMenu (Menu menu){
           //  Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
           return true;
        }

        //@Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.logout) {
                logoutUser();
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected (MenuItem item){
            // Handle navigation view item clicks here.
            int id = item.getItemId();





            if (id == R.id.customer_view) {

                FragmentTransaction manager = getSupportFragmentManager()
                        .beginTransaction();

                if (Customerview.isAdded()) {

                    manager.show(Customerview);

                } else {
                    manager.add(R.id.cups_display, Customerview);
                }
                if (Uniforms.isAdded()) {
                    manager.hide(Uniforms);
                }
                if (Products.isAdded()) {
                    manager.hide(Products);
                }
                if (Speed.isAdded()) {
                    manager.hide(Speed);
                }
                if (Audit_Details.isAdded()) {
                    manager.hide(Audit_Details);
                }
                manager.commit();

        } else if (id == R.id.uniforms) {
                FragmentTransaction manager = getSupportFragmentManager()
                        .beginTransaction();
                if (Uniforms.isAdded()) {
                    manager.show(Uniforms);

                } else {
                    manager.add(R.id.cups_display, Uniforms);
                }
                if (Customerview.isAdded()) {
                    manager.hide(Customerview);
                }
                if (Products.isAdded()) {
                    manager.hide(Products);
                }
                if (Speed.isAdded()) {
                    manager.hide(Speed);
                }
                if (Audit_Details.isAdded()) {
                    manager.hide(Audit_Details);
                }
                manager.commit();



        } else if (id == R.id.products) {
                FragmentTransaction manager = getSupportFragmentManager()
                        .beginTransaction();
                if (Products.isAdded()) {
                    manager.show(Products);

                } else {
                    manager.add(R.id.cups_display, Products);
                }
                if (Uniforms.isAdded()) {
                    manager.hide(Uniforms);
                }
                if (Customerview.isAdded()) {
                    manager.hide(Customerview);
                }
                if (Speed.isAdded()) {
                    manager.hide(Speed);
                }
                if (Audit_Details.isAdded()) {
                    manager.hide(Audit_Details);
                }
                manager.commit();


        } else if (id == R.id.speed) {
                FragmentTransaction manager = getSupportFragmentManager()
                        .beginTransaction();
                if (Speed.isAdded()) {
                    manager.show(Speed);

                } else {
                    manager.add(R.id.cups_display, Speed);
                }
                if (Uniforms.isAdded()) {
                    manager.hide(Uniforms);
                }
                if (Products.isAdded()) {
                    manager.hide(Products);
                }
                if (Customerview.isAdded()) {
                    manager.hide(Customerview);
                }
                if (Audit_Details.isAdded()) {
                    manager.hide(Audit_Details);
                }
                manager.commit();


        } else if (id == R.id.audit_details) {
                FragmentTransaction manager = getSupportFragmentManager()
                        .beginTransaction();
                if (Audit_Details.isAdded()) {
                    manager.show(Audit_Details);
                } else {
                    manager.add(R.id.cups_display, Audit_Details);
                }
                if (Uniforms.isAdded()) {
                    manager.hide(Uniforms);
                }
                if (Products.isAdded()) {
                    manager.hide(Products);
                }
                if (Speed.isAdded()) {
                    manager.hide(Speed);
                }
                if (Customerview.isAdded()) {
                    manager.hide(Customerview);
                }
                manager.commit();

        }
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public String countsum(){
        return (Double.toString(Customerview.getcount()+Uniforms.getcount()+Products.getcount()+Speed.getcount()));
    }
    public String auditorname(){
        return dbname;
    }
    public String storenumber(){
        return dbstorenumber;
    }
    public String totalsum(){
        return Integer.toString(Customerview.gettotal()+Uniforms.gettotal()+Products.gettotal()+Speed.gettotal());
    }
    public boolean getcabove(){
        return Customerview.getabove();
    }
    public boolean getuabove(){
        return Uniforms.getabove();
    }
    public boolean getpabove(){
        return Products.getabove();
    }
    public boolean getsabove(){
        return Speed.getabove();
    }
    public boolean getcfail(){
        return Customerview.getfail();
    }
    public boolean getufail(){
        return Uniforms.getfail();
    }
    public boolean getpfail(){
        return Products.getfail();
    }
    public boolean getsfail(){
        return Speed.getfail();
    }

    public String createpdf() throws IOException, DocumentException{
        SimpleDateFormat formatdate = new SimpleDateFormat("MMddyyyy");
        date = formatdate.format(new Date());
        SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm");
        String time = timeformat.format(new Date());

        newpdfname = Audit_Details.getstorenumber() + "_" + date + "_" + Audit_Details.gettgrade() + "_" + dbuid + "_" + dbname + ".pdf";
        newoutput = new File(Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/CUPSAudit/" + newpdfname);

        reader = new PdfReader(getResources().openRawResource(R.raw.cupsaudit));
        stamper = new PdfStamper(reader, new FileOutputStream(newoutput));
        AcroFields acroFields = stamper.getAcroFields();
        acroFields.setField("cgrade", Customerview.getgrade());
        acroFields.setField("ugrade", Uniforms.getgrade());
        acroFields.setField("pgrade", Products.getgrade());
        acroFields.setField("sgrade", Speed.getgrade());
        acroFields.setField("totalgrade", Audit_Details.gettgrade());
        acroFields.setField("storenumber", Audit_Details.getstorenumber());
        acroFields.setField("auditorname", dbname);
        acroFields.setField("auditnotes", Audit_Details.getauditornotes());
        acroFields.setField("date", date);
        acroFields.setField("time", time);
        acroFields.setField("cnote", Customerview.gettnotes());
        acroFields.setField("unote", Uniforms.gettnotes());
        acroFields.setField("pnote", Products.gettnotes());
        acroFields.setField("snote", Speed.gettnotes());



        if(Customerview.getcbox1()==true){
            acroFields.setField("cbox1", "Yes");
        }else{
            acroFields.setField("cbox1", "No");
        }
        if(Customerview.getcbox2()==true){
            acroFields.setField("cbox2", "Yes");
        }else{
            acroFields.setField("cbox2", "No");
        }
        if(Customerview.getcbox3()==true){
            acroFields.setField("cbox3", "Yes");
        }else{
            acroFields.setField("cbox3", "No");
        }
        if(Customerview.getcbox4()==true){
            acroFields.setField("cbox4", "Yes");
        }else{
            acroFields.setField("cbox4", "No");
        }
        if(Customerview.getcbox5()==true){
            acroFields.setField("cbox5", "Yes");
        }else{
            acroFields.setField("cbox5", "No");
        }
        if(Customerview.getcbox6()==true){
            acroFields.setField("cbox6", "Yes");
        }else{
            acroFields.setField("cbox6", "No");
        }
        if(Customerview.getcbox7()==true){
            acroFields.setField("cbox7", "Yes");
        }else{
            acroFields.setField("cbox7", "No");
        }
        if(Customerview.getcbox8()==true){
            acroFields.setField("cbox8", "Yes");
        }else{
            acroFields.setField("cbox8", "No");
        }
        if(Customerview.getcbox9()==true){
            acroFields.setField("cbox9", "Yes");
        }else{
            acroFields.setField("cbox9", "No");
        }
        if(Customerview.getcabovebox()==true){
            acroFields.setField("cabove", "Yes");
            acroFields.setField("cabovenote", Customerview.getcabove());
        }else{
            acroFields.setField("cabove", "No");
            acroFields.setField("cabovenote", "");        }
        if(Customerview.getcfailbox()==true){
            acroFields.setField("cfail", "Yes");
            acroFields.setField("cfailnote", Customerview.getcfail());
        }else{
            acroFields.setField("cfail", "No");
            acroFields.setField("cfailnote","");        }

        if(Uniforms.getcbox1()==true){
            acroFields.setField("ubox1", "Yes");
        }else{
            acroFields.setField("ubox1", "No");
        }
        if(Uniforms.getcbox2()==true){
            acroFields.setField("ubox2", "Yes");
        }else{
            acroFields.setField("ubox2", "No");
        }
        if(Uniforms.getcbox3()==true){
            acroFields.setField("ubox3", "Yes");
        }else{
            acroFields.setField("ubox3", "No");
        }
        if(Uniforms.getcabovebox()==true){
            acroFields.setField("uabove", "Yes");
            acroFields.setField("uabovenote", Uniforms.getcabove());
        }else{
            acroFields.setField("uabove", "No");
            acroFields.setField("uabovenote", "");        }
        if(Uniforms.getcfailbox()==true){
            acroFields.setField("ufail", "Yes");
            acroFields.setField("ufailnote", Uniforms.getcfail());
        }else{
            acroFields.setField("ufail", "No");
            acroFields.setField("ufailnote","");        }

        if(Products.getcbox1()==true){
            acroFields.setField("pbox1", "Yes");
        }else{
            acroFields.setField("pbox1", "No");
        }
        if(Products.getcbox2()==true){
            acroFields.setField("pbox2", "Yes");
        }else{
            acroFields.setField("pbox2", "No");
        }
        if(Products.getcbox3()==true){
            acroFields.setField("pbox3", "Yes");
        }else{
            acroFields.setField("pbox3", "No");
        }
        if(Products.getcbox4()==true){
            acroFields.setField("pbox4", "Yes");
        }else{
            acroFields.setField("pbox4", "No");
        }
        if(Products.getcabovebox()==true){
            acroFields.setField("pabove", "Yes");
            acroFields.setField("pabovenote", Products.getcabove());
        }else{
            acroFields.setField("pabove", "No");
            acroFields.setField("pabovenote", "");
        }
        if(Products.getcfailbox()==true){
            acroFields.setField("pfail", "Yes");
            acroFields.setField("pfailnote", Products.getcfail());
        }else{
            acroFields.setField("pfail", "No");
            acroFields.setField("pfailnote","");
        }

        if(Speed.getcbox1()==true){
            acroFields.setField("sbox1", "Yes");
        }else{
            acroFields.setField("sbox1", "No");
        }
        if(Speed.getcbox2()==true){
            acroFields.setField("sbox2", "Yes");
        }else{
            acroFields.setField("sbox2", "No");
        }
        if(Speed.getcbox3()==true){
            acroFields.setField("sbox3", "Yes");
        }else{
            acroFields.setField("sbox3", "No");
        }
        if(Speed.getcbox4()==true){
            acroFields.setField("sbox4", "Yes");
        }else{
            acroFields.setField("sbox4", "No");
        }
        if(Speed.getcbox5()==true){
            acroFields.setField("sbox5", "Yes");
        }else{
            acroFields.setField("sbox5", "No");
        }
        if(Speed.getcbox6()==true){
            acroFields.setField("sbox6", "Yes");
        }else{
            acroFields.setField("sbox6", "No");
        }
        if(Speed.getcbox7()==true){
            acroFields.setField("sbox7", "Yes");
        }else{
            acroFields.setField("sbox7", "No");
        }
        if(Speed.getcabovebox()==true){
            acroFields.setField("sabove", "Yes");
            acroFields.setField("sabovenote", Speed.getcabove());
        }else{
            acroFields.setField("sabove", "No");
            acroFields.setField("sabovenote", "");
        }
        if(Speed.getcfailbox()==true){
            acroFields.setField("sfail", "Yes");
            acroFields.setField("sfailnote", Speed.getcfail());
        }else{
            acroFields.setField("pfail", "No");
            acroFields.setField("pfailnote","");
        }
        stamper.setFormFlattening(true);
        stamper.close();
        reader.close();


        return "Complete";
    }

    public int uploadFile() throws IOException, DocumentException{

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try{

        } catch (Exception e){
            e.printStackTrace();
        }

        int serverResponseCode = 0;

        HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        String outputfilepath = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/CUPSAudit/" + newpdfname;


        int bytesRead,bytesAvailable,bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;

        if (!newoutput.isFile()){
            return 0;
        }else{
            try{
                FileInputStream fileInputStream = new FileInputStream(newoutput);
                URL url = new URL(SERVER_URL);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);//Allow Inputs
                connection.setDoOutput(true);//Allow Outputs
                connection.setUseCaches(false);//Don't use a cached Copy
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("ENCTYPE", "multipart/form-data");
                connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                connection.setRequestProperty("uploaded_file",outputfilepath);

                //creating new dataoutputstream
                dataOutputStream = new DataOutputStream(connection.getOutputStream());

                //writing bytes to data outputstream
                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                        + newoutput + "\"" + lineEnd);

                dataOutputStream.writeBytes(lineEnd);

                //returns no. of bytes present in fileInputStream
                bytesAvailable = fileInputStream.available();
                //selecting the buffer size as minimum of available bytes or 1 MB
                bufferSize = Math.min(bytesAvailable,maxBufferSize);
                //setting the buffer as byte array of size of bufferSize
                buffer = new byte[bufferSize];

                //reads bytes from FileInputStream(from 0th index of buffer to buffersize)
                bytesRead = fileInputStream.read(buffer,0,bufferSize);

                //loop repeats till bytesRead = -1, i.e., no bytes are left to read
                while (bytesRead > 0){
                    //write the bytes read from inputstream
                    dataOutputStream.write(buffer,0,bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable,maxBufferSize);
                    bytesRead = fileInputStream.read(buffer,0,bufferSize);
                }

                dataOutputStream.writeBytes(lineEnd);
                dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                serverResponseCode = connection.getResponseCode();

                //response code of 200 indicates the server status OK
                if(serverResponseCode == 200){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,"File Upload completed",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                //closing the input and output streams
                fileInputStream.close();
                dataOutputStream.flush();
                dataOutputStream.close();



            } catch (FileNotFoundException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"File Not Found",Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "URL error!", Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Cannot Read/Write File!", Toast.LENGTH_SHORT).show();
            }
            return serverResponseCode;
        }

    }

    public void logoutUser() {
        session.setLogin(false);

	db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener){
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    public boolean CreateCUPSFolder(){

        if(Build.VERSION.SDK_INT >= 23) {

                File folder = new File(Environment.getExternalStorageDirectory() + "/CUPSAudit");
                boolean success = true;
                if (!folder.exists()) {
                    success = folder.mkdir();
                    if (!success) {
                        Toast.makeText(getApplicationContext(), "Error creating /CUPSAudit on External Storage, logging out.  Please check Application Permissions and try again.", Toast.LENGTH_LONG).show();
                        return false;
                    }
                    Toast.makeText(getApplicationContext(), "Created folder /CUPSAudit on External Storage.", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    Toast.makeText(getApplicationContext(), "Folder /CUPSAudit on External Storage Exists.", Toast.LENGTH_SHORT).show();
                    return true;
                }

        }else {

                File folder = new File(Environment.getExternalStorageDirectory() + "/CUPSAudit");
                boolean success = true;
                if (!folder.exists()) {
                    success = folder.mkdir();
                    if (!success) {
                        Toast.makeText(getApplicationContext(), "Error creating /CUPSAudit on External Storage, logging out.  Please check Application Permissions and try again.", Toast.LENGTH_LONG).show();
                        return false;
                    }
                    Toast.makeText(getApplicationContext(), "Created folder /CUPSAudit on External Storage.", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    Toast.makeText(getApplicationContext(), "Folder /CUPSAudit on External Storage Exists.", Toast.LENGTH_SHORT).show();
                    return true;

                }
            }
    }
    public void CUPSEmail (){
        uploadfilename = newpdfname;
        storeemail = "rs"+Audit_Details.getstorenumber()+"@pizzahut.com";
        String subject = Audit_Details.getstorenumber() + "_" + date + "_" + Audit_Details.gettgrade() + "_" + dbuid + "_" + dbname;
        String body = "This is being emailed to you from the CUPSAudit Android Application.  If this email is getting to you in error then please disregard.";
        String sendareaemail = dbareaemail;
        Log.v("Data to Email.Class: ",storeemail+", "+sendareaemail+", "+subject+", "+uploadfilename+", "+body);

        emailing.sendingemail(MainActivity.this,storeemail,sendareaemail,subject,uploadfilename,body);

    }


}

