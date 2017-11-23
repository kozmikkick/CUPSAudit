package distantfantasy.cups;

import android.app.Activity;
import android.os.Environment;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.io.File;

/**
 * Created by kozmikkick on 5/26/17.
 */

public class pdfmanagement extends Activity {

    public OutputStream output = null;
    public PdfReader reader = null;
    public PdfStamper stamper = null;
    private customerview Customerview;
    private uniforms Uniforms;
    private products Products;
    private speed Speed;
    private audit_details Audit_Details;
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


    public pdfmanagement() throws IOException, DocumentException {
        Customerview = new customerview();
        Uniforms = new uniforms();
        Products = new products();
        Speed = new speed();
        Audit_Details = new audit_details();


        java.text.SimpleDateFormat formatdate = new java.text.SimpleDateFormat("MMDDYYYY");
        Date currentdate = java.util.Calendar.getInstance().getTime();
        formatdate.format(currentdate);
        String newpdfname = Audit_Details.getstorenumber() + "-" + formatdate + "-" + Audit_Details.gettgrade() + "-" + Audit_Details.getauditor() + ".pdf";
        File newoutput = new File(Environment.getExternalStorageDirectory() + "CUPSAudit/", newpdfname);


        reader = new PdfReader(getResources().openRawResource(R.raw.cupsaudit));
        stamper = new PdfStamper(reader, new FileOutputStream(newoutput));
        AcroFields acroFields = stamper.getAcroFields();
        cbox1=Customerview.getcbox1();
        if(cbox1=true){
            acroFields.setField("cbox1", "/On");
        }else{
            acroFields.setField("cbox1", "/Off");
        }
        cbox2=Customerview.getcbox2();
        if(cbox2=true){
            acroFields.setField("cbox2", "/On");
        }else{
            acroFields.setField("cbox2", "/Off");
        }
        cbox3=Customerview.getcbox3();
        if(cbox3=true){
            acroFields.setField("cbox3", "/On");
        }else{
            acroFields.setField("cbox3", "/Off");
        }
        cbox4=Customerview.getcbox4();
        if(cbox4=true){
            acroFields.setField("cbox4", "/On");
        }else{
            acroFields.setField("cbox4", "/Off");
        }
        cbox5=Customerview.getcbox5();
        if(cbox5=true){
            acroFields.setField("cbox5", "/On");
        }else{
            acroFields.setField("cbox5", "/Off");
        }
        cbox6=Customerview.getcbox6();
        if(cbox6=true){
            acroFields.setField("cbox6", "/On");
        }else{
            acroFields.setField("cbox6", "/Off");
        }
        cbox7=Customerview.getcbox7();
        if(cbox7=true){
            acroFields.setField("cbox7", "/On");
        }else{
            acroFields.setField("cbox7", "/Off");
        }
        cbox8=Customerview.getcbox8();
        if(cbox8=true){
            acroFields.setField("cbox8", "/On");
        }else{
            acroFields.setField("cbox8", "/Off");
        }
        cbox9=Customerview.getcbox9();
        if(cbox9=true){
            acroFields.setField("cbox9", "/On");
        }else{
            acroFields.setField("cbox9", "/Off");
        }
        cabove=Customerview.getcabovebox();
        if(cabove=true){
            acroFields.setField("cabove", "/On");
            acroFields.setField("cabovenote", Customerview.getcabove());
        }else{
            acroFields.setField("cabove", "/Off");
            acroFields.setField("cabovenote", "");        }
        cfail=Customerview.getcfailbox();
        if(cfail=true){
            acroFields.setField("cfail", "/On");
            acroFields.setField("cfailnote", Customerview.getcfail());
        }else{
            acroFields.setField("cfail", "/Off");
            acroFields.setField("cfailnote","");        }

        ubox1=Uniforms.getcbox1();
        if(ubox1=true){
            acroFields.setField("ubox1", "/On");
        }else{
            acroFields.setField("ubox1", "/Off");
        }
        ubox2=Uniforms.getcbox2();
        if(ubox2=true){
            acroFields.setField("ubox2", "/On");
        }else{
            acroFields.setField("ubox2", "/Off");
        }
        ubox3=Uniforms.getcbox3();
        if(ubox3=true){
            acroFields.setField("ubox3", "/On");
        }else{
            acroFields.setField("ubox3", "/Off");
        }
        uabove=Uniforms.getcabovebox();
        if(uabove=true){
            acroFields.setField("uabove", "/On");
            acroFields.setField("uabovenote", Uniforms.getcabove());
        }else{
            acroFields.setField("uabove", "/Off");
            acroFields.setField("uabovenote", "");        }
        ufail=Uniforms.getcfailbox();
        if(ufail=true){
            acroFields.setField("ufail", "/On");
            acroFields.setField("ufailnote", Uniforms.getcfail());
        }else{
            acroFields.setField("ufail", "/Off");
            acroFields.setField("ufailnote","");        }

        pbox1=Products.getcbox1();
        if(pbox1=true){
            acroFields.setField("pbox1", "/On");
        }else{
            acroFields.setField("pbox1", "/Off");
        }
        pbox2=Products.getcbox2();
        if(pbox2=true){
            acroFields.setField("pbox2", "/On");
        }else{
            acroFields.setField("pbox2", "/Off");
        }
        pbox3=Products.getcbox3();
        if(pbox3=true){
            acroFields.setField("pbox3", "/On");
        }else{
            acroFields.setField("pbox3", "/Off");
        }
        pbox4=Products.getcbox4();
        if(pbox4=true){
            acroFields.setField("pbox4", "/On");
        }else{
            acroFields.setField("pbox4", "/Off");
        }
        pabove=Products.getcabovebox();
        if(pabove=true){
            acroFields.setField("pabove", "/On");
            acroFields.setField("pabovenote", Products.getcabove());
        }else{
            acroFields.setField("pabove", "/Off");
            acroFields.setField("pabovenote", "");
        }
        pfail=Products.getcfailbox();
        if(pfail=true){
            acroFields.setField("pfail", "/On");
            acroFields.setField("pfailnote", Products.getcfail());
        }else{
            acroFields.setField("pfail", "/Off");
            acroFields.setField("pfailnote","");
        }

        sbox1=Speed.getcbox1();
        if(sbox1=true){
            acroFields.setField("sbox1", "/On");
        }else{
            acroFields.setField("sbox1", "/Off");
        }
        sbox2=Speed.getcbox2();
        if(sbox2=true){
            acroFields.setField("sbox2", "/On");
        }else{
            acroFields.setField("sbox2", "/Off");
        }
        sbox3=Speed.getcbox3();
        if(sbox3=true){
            acroFields.setField("sbox3", "/On");
        }else{
            acroFields.setField("sbox3", "/Off");
        }
        sbox4=Speed.getcbox4();
        if(sbox4=true){
            acroFields.setField("sbox4", "/On");
        }else{
            acroFields.setField("sbox4", "/Off");
        }
        sbox5=Speed.getcbox5();
        if(sbox5=true){
            acroFields.setField("sbox5", "/On");
        }else{
            acroFields.setField("sbox5", "/Off");
        }
        sbox6=Speed.getcbox6();
        if(sbox6=true){
            acroFields.setField("sbox6", "/On");
        }else{
            acroFields.setField("sbox6", "/Off");
        }
        sbox7=Speed.getcbox7();
        if(sbox7=true){
            acroFields.setField("sbox7", "/On");
        }else{
            acroFields.setField("sbox7", "/Off");
        }
        sabove=Speed.getcabovebox();
        if(sabove=true){
            acroFields.setField("sabove", "/On");
            acroFields.setField("sabovenote", Speed.getcabove());
        }else{
            acroFields.setField("sabove", "/Off");
            acroFields.setField("sabovenote", "");
        }
        sfail=Speed.getcfailbox();
        if(sfail=true){
            acroFields.setField("sfail", "/On");
            acroFields.setField("sfailnote", Speed.getcfail());
        }else{
            acroFields.setField("pfail", "/Off");
            acroFields.setField("pfailnote","");
        }
        stamper.setFormFlattening(true);
        stamper.close();


        return;


    }


}
